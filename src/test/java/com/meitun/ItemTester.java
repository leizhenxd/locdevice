package com.meitun;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.ld.dao.FunctionDAO;
import com.ld.dao.RoleDAO;
import com.ld.dao.RoleFunctionDAO;
import com.ld.dao.UserDAO;
import com.ld.logistic.dao.TLogisticUserDAO;
import com.ld.logistic.domain.TLogisticUserDO;
import com.ld.model.Function;
import com.ld.model.Role;
import com.ld.model.RoleFunction;
import com.ld.model.User;
import com.ld.service.DeviceInfoService;
import com.ld.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml"})
public class ItemTester {
	@Autowired
	UserDAO userDAO;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	FunctionDAO functionDAO;
	@Autowired
	RoleFunctionDAO roleFunctionDAO;
	@Autowired
	UserService userService;
	@Autowired
	DeviceInfoService deviceInfoService;
	
	@Autowired
	TLogisticUserDAO logisticUserDao;
	
	@Test
	public void testUserDAO(){
		System.out.println(userDAO.selectDynamicPageQuery(new User()));
		System.out.println(roleDAO.selectDynamicPageQuery(new Role()));
		System.out.println(functionDAO.selectDynamicPageQuery(new Function()));
		System.out.println(roleFunctionDAO.selectDynamicPageQuery(new RoleFunction()));
		print(functionDAO.selectAllFunctionByRole(0l));
	}
	
	@Test
	public void testPermission() {
		User user = userDAO.selectById(1);
		print(userService.canVisit(user, "index.htm"));
		print(userService.selectAllFunctionByRole(0l));
	}
	
	@Test
	public void testselectByAreaAndDeviceTypes() {
		Long areaId = 1l;
		List<Integer> deviceTypes = CollectionUtils.arrayToList(new Integer[]{1,2});
		print(deviceInfoService.selectByAreaAndDeviceTypes(areaId, deviceTypes));
	}
	@Test
	public void testStatistic() {
		print(logisticUserDao.selectCountDynamic(new TLogisticUserDO()));
	}
	private void print(Object object) {
		System.out.println(JSONObject.toJSONString(object));
	}
}
