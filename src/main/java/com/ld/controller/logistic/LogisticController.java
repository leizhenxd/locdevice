package com.ld.controller.logistic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ld.constant.Constant;
import com.ld.logistic.dao.TLogisticParcelDAO;
import com.ld.logistic.dao.TLogisticScanRecordDAO;
import com.ld.logistic.dao.TLogisticUserDAO;
import com.ld.logistic.dao.TLogisticUserExpendDAO;
import com.ld.logistic.domain.TLogisticParcelDO;
import com.ld.logistic.domain.TLogisticScanRecordDO;
import com.ld.logistic.domain.TLogisticUserDO;
import com.ld.logistic.domain.TLogisticUserExpendDO;
import com.ld.logistic.service.TLogisticScanRecordService;
import com.ld.logistic.service.TLogisticUserService;
import com.ld.model.User;
import com.ld.response.BasePageResponse;
import com.ld.response.logistic.DeliverHistory;
import com.ld.response.logistic.ParcelInfo;
import com.ld.response.logistic.ScanRecordHistory;
import com.ld.response.logistic.TUtil;
import com.ld.service.UserService;
import com.ld.util.BaiDuMapUtils;
import com.ld.util.EncryptUtils;

@Controller
@RequestMapping(value="logistic")
public class LogisticController {
	Logger log = LoggerFactory.getLogger(LogisticController.class);

	@Autowired
	TLogisticUserDAO userDao;
	@Autowired
	TLogisticParcelDAO logisticParcelDao;
	@Autowired
	TLogisticScanRecordDAO logisticScanRecordDao;
	@Autowired
	TLogisticUserExpendDAO logisticUserExpendDO;
	@Autowired
	TLogisticUserService tuserService;
	@Autowired
	UserService userService;
	@Autowired
	TLogisticScanRecordService scanRecordService;
	
	@RequestMapping(value = "login", method=RequestMethod.GET)
	public String login() {
		return "logistic/login";
	}
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest request, User u, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		log.info("用户登录请求:{}", JSONObject.toJSONString(u));
		if(u == null){
			return "清输入用户名和密码";
		}
		else if(StringUtils.isEmpty(u.getPhone())){
			return "请输入用户名";
		}
		else if(StringUtils.isEmpty(u.getPassword())){
			return "请输入密码";
		}
		if(u.getPassword().length() > 30 || u.getPhone().length() >99){
			return "用户名或密码太长";
		}
		User user = userService.selectByPhone(u.getPhone());
		if(user == null)
			return "用户名不存在";
		else if(!user.getPassword().equals(EncryptUtils.md5(u.getPassword(), user.getSalt()))){
			return "密码错误";
		}
		user.setWalletId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		request.getSession().setAttribute(Constant.LoginSessionKey, user);
		
		return "success";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.LoginSessionKey);
		return "login";
	}
	
	@RequestMapping(value = "register")
	@ResponseBody
	public Map getAll(TLogisticUserDO user) {
		Map<String, Object> result = new HashMap<String, Object>();
		TLogisticUserDO query = new TLogisticUserDO();
		query.setPhone(user.getPhone());
		long num = userDao.selectCountDynamic(query);
		if(num > 0) {
			result.put("result", false);
			result.put("msg", "User has exist!");
			return result;
		}
		
		user.setRole(1);
		user.setNormalAddress("shanghai");
		user.setWalletId(UUID.randomUUID().toString().replaceAll("-", "").substring(1, 24));
		long row = userDao.insert(user);
		
		
		result.put("result", row > 0?true : false);
		result.put("msg", row > 0?"Register success." : "Register fail.");
		result.put("data", user);
		
		return result;
	}
	
	@RequestMapping(value = "login", method=RequestMethod.POST)
	@ResponseBody
	public Map login(TLogisticUserDO user) {
		Map<String, Object> result = new HashMap<String, Object>();
		TLogisticUserDO query = new TLogisticUserDO();
		query.setPhone(user.getPhone());
		List<TLogisticUserDO> num = userDao.selectDynamic(query);
		if(CollectionUtils.isEmpty(num)) {
			result.put("result", false);
			result.put("msg", "User not exist!");
			return result;
		}
		else if(!user.getPassword().equals(num.get(0).getPassword())) {
			result.put("result", false);
			result.put("msg", "Password error!");
			return result;
		}
		
		TLogisticUserExpendDO expendQuery = new TLogisticUserExpendDO();
		expendQuery.setUserId(Long.valueOf(num.get(0).getId()));
		List<TLogisticUserExpendDO> expendList = logisticUserExpendDO.selectDynamic(expendQuery);
		TLogisticUserExpendDO expend = CollectionUtils.isEmpty(expendList)?null:expendList.get(0);
		if(expend != null) {
			num.get(0).setLat(expend.getLat());
			num.get(0).setLng(expend.getLng());
		}
		
		result.put("result", true);
		result.put("msg", "success");
		result.put("data", num.get(0));
		
		return result;
	}
	
	@RequestMapping(value = "scan")
	@ResponseBody
	public Map scan(@RequestParam(required=true,value="userId")Long userId, String code, String lat, String lng, String address, String city) {
		log.info("scan:{}", code);
		Map<String, Object> result = new HashMap<String, Object>();
		TLogisticUserDO user = userDao.selectById(userId);
		TLogisticUserExpendDO expendQuery = new TLogisticUserExpendDO();
		expendQuery.setUserId(userId);
		List<TLogisticUserExpendDO> expendList = logisticUserExpendDO.selectDynamic(expendQuery);
		TLogisticUserExpendDO expend = CollectionUtils.isEmpty(expendList)?null:expendList.get(0);
		expendQuery.setUserId(userId);
		if(user == null) {
			result.put("result", false);
			result.put("msg", "Fail");
			return result;
		}
		TLogisticParcelDO parcel = null;
		TLogisticParcelDO query = new TLogisticParcelDO();
		query.setCode(code);
		List<TLogisticParcelDO> dos = logisticParcelDao.selectDynamic(query);
		if(!CollectionUtils.isEmpty(dos)) parcel = dos.get(0);
		
		if(parcel == null && user.getRole() != 3) {
			result.put("result", false);
			result.put("msg", "This ID was not found in the chain！");
			return result;
		}
		if(user.getRole() == 1) {
			result.put("result", false);
			result.put("msg", "This ID was not found in the chain！");
			return result;
		}
		if(user.getRole() == 3) {
			if(parcel != null) {
				result.put("result", false);
				result.put("msg", "This ID is already been used！");
				return result;
			}
			parcel = new TLogisticParcelDO();
			parcel.setBeginCity(city);
			parcel.setEndCity("----");
			parcel.setCode(code);
			parcel.setFromLat("");
			parcel.setName("Test Parcel Name");
			parcel.setStatus(1);
			parcel.setUserId(16);
			parcel.setNode(1);
			logisticParcelDao.insert(parcel);
			
			TLogisticScanRecordDO record = new TLogisticScanRecordDO();
			record.setHashCode(UUID.randomUUID().toString().replaceAll("-", "").substring(1, 32));
			record.setParcelId(parcel.getId());
			record.setAddress(address);
			record.setLat(lat);
			record.setLng(lng);
			record.setUserId(userId);
			record.setStatus(1);
			record.setCreateTime(new Date());
			logisticScanRecordDao.insert(record);
			
			record.setCode(parcel.getCode());
			result.put("result", true);
			result.put("msg", "success");
			result.put("data", record);
			
			return result;
		}
		
		TLogisticScanRecordDO recordQuery = new TLogisticScanRecordDO();
		recordQuery.setUserId(userId);
		recordQuery.setParcelId(parcel.getId());
		if(!CollectionUtils.isEmpty(logisticScanRecordDao.selectDynamic(recordQuery))) {
			result.put("result", false);
			result.put("msg", "You had scaned this ID.");
			return result;
		}
		TLogisticScanRecordDO scanRecordQuery = new TLogisticScanRecordDO();
		scanRecordQuery.setParcelId(parcel.getId());
		scanRecordQuery.setStatus(2);
		long haveDelivered = logisticScanRecordDao.selectCountDynamic(recordQuery);
		if(haveDelivered > 0) {
			result.put("result", false);
			result.put("msg", "This ID have delivered.");
			return result;
		}
		TLogisticScanRecordDO record = new TLogisticScanRecordDO();
		if(user.getRole() == 2 && user.getPhone().startsWith("sales")) {
			record.setHashCode(UUID.randomUUID().toString().replaceAll("-", "").substring(1, 32));
			record.setParcelId(parcel.getId());
			record.setStatus(2);
			record.setUserId(userId);
			record.setAddress(address);
			record.setLat(lat);
			record.setLng(lng);
			parcel.setStatus(2);
			parcel.setEndCity(city);
			parcel.setUpdateTime(new Date());
			logisticParcelDao.update(parcel);
		}
		else if(user.getRole() == 2) {
			record.setHashCode(UUID.randomUUID().toString().replaceAll("-", "").substring(1, 32));
			record.setParcelId(parcel.getId());
			record.setAddress(address);
			record.setLat(lat);
			record.setLng(lng);
			record.setUserId(userId);
			record.setStatus(1);
			record.setCreateTime(new Date());
			parcel.setNode(parcel.getNode()+1);
			parcel.setUpdateTime(new Date());
			logisticParcelDao.update(parcel);
		}
		
		logisticScanRecordDao.insert(record);
		record.setCode(parcel.getCode());
		result.put("result", true);
		result.put("msg", "success");
		result.put("data", record);
		
		return result;
	}
	
	@RequestMapping(value = "getDeliverHistory")
	@ResponseBody
	public Map getDeliverHistory(Long userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<DeliverHistory> list = logisticScanRecordDao.queryDeliverHistory(userId);
		
		result.put("result", true);
		result.put("msg", "success");
		result.put("data", list);
		
		return result;
	}
	
	@RequestMapping(value = "getParcelInfoById",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Map getParcelInfoById(String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", true);
		result.put("msg", "success");
		
		ParcelInfo parcelInfo = new ParcelInfo();
		
		TLogisticParcelDO parcel = null;
		TLogisticParcelDO query = new TLogisticParcelDO();
		query.setCode(code);
		List<TLogisticParcelDO> dos = logisticParcelDao.selectDynamic(query);
		if(!CollectionUtils.isEmpty(dos)) parcel = dos.get(0);
		
		if(parcel == null) {
			result.put("data", null);
			return result;
		}
		BeanUtils.copyProperties(parcel, parcelInfo);
		
		List<TLogisticScanRecordDO> records = logisticScanRecordDao.queryParcelInfoById(parcel.getId());
		
		parcelInfo.setRecords(records);
		
		result.put("data", parcelInfo);
		
		return result;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletResponse response, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping(value = "/account")
	public ModelAndView account(HttpServletResponse response, TLogisticUserDO user) {
		ModelAndView mv = new ModelAndView("/logistic/account");
		return mv;
	}
	@RequestMapping(value = "/history")
	public ModelAndView history(HttpServletResponse response, TLogisticUserDO user) {
		ModelAndView mv = new ModelAndView("/logistic/history");
		return mv;
	}
	@RequestMapping(value = "/network")
	public ModelAndView network(HttpServletResponse response, TLogisticUserDO user) {
		ModelAndView mv = new ModelAndView("/logistic/network");
		return mv;
	}
	@RequestMapping(value = "/blockchain")
	public ModelAndView blockchain(HttpServletResponse response, TLogisticUserDO user) {
		ModelAndView mv = new ModelAndView("/logistic/blockchain");
		return mv;
	}
	@RequestMapping(value = "/getAllUser")
	@ResponseBody
	public BasePageResponse<TLogisticUserDO> getAllUser(HttpServletRequest request, TLogisticUserDO user) {
		return tuserService.getPageList(user);
	}
	@RequestMapping(value = "/getAllHistory")
	@ResponseBody
	public BasePageResponse<ScanRecordHistory> getAllHistory(HttpServletRequest request) {
		return scanRecordService.getPageList(new TLogisticScanRecordDO());
	}
	@RequestMapping("helloworld")
	@ResponseBody
	public String helloworld() {
		return "hello world";
	}
	@RequestMapping("getAddress")
	@ResponseBody
	public Object getAddress(String lat, String lng) {
		log.info("获取地理位置参数:{},{}", lat, lng);
		Object city = BaiDuMapUtils.toCity(lat, lng);
		log.info("结果:{}", JSONObject.toJSONString(city));
		return city;
	}
	
}
