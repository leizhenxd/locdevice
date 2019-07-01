package com.ld.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ld.dao.AreaDAO;
import com.ld.model.Area;
import com.ld.model.DeviceInfo;
import com.ld.model.Function;
import com.ld.response.BasePageResponse;
import com.ld.response.dto.DeviceInfoDto;
import com.ld.response.dto.MaintainInfoDto;
import com.ld.service.DeviceInfoService;
import com.ld.util.CheckMobileUtils;

@Controller
public class DeviceInfoController {
	Logger log = LoggerFactory.getLogger(DeviceInfoController.class);
	@Autowired
	DeviceInfoService deviceInfoService;
	@Autowired
	AreaDAO areaDAO;

	@RequestMapping(value = "/device/view")
	public ModelAndView view(HttpServletResponse response, Long areaId/** 区域 */, Integer[] devicescb /** 设备类型 */) {
		
		ModelAndView mv = new ModelAndView("/device/view");
		
		Area areaInfo = areaDAO.selectById(areaId);
		
		if(devicescb == null || devicescb.length == 0) {
			mv.addObject("devices", new ArrayList<DeviceInfoDto>());
			mv.addObject("area", areaInfo);
			
			return mv;
		}
		
		List<DeviceInfo> devices = deviceInfoService.selectByAreaAndDeviceTypes(areaId, CollectionUtils.arrayToList(devicescb));
		List<DeviceInfoDto> result = new ArrayList<DeviceInfoDto>();
		
		if(!CollectionUtils.isEmpty(devices)) {
			for(DeviceInfo device : devices) {
				DeviceInfoDto dto = new DeviceInfoDto();
				BeanUtils.copyProperties(device, dto);
				result.add(dto);
			}
		}
		
		mv.addObject("devices", result);
		mv.addObject("area", areaInfo);
		
		return mv;
	}
	
	@RequestMapping(value = "/device/query")
	@ResponseBody
	public List<DeviceInfoDto> query(HttpServletResponse response, Long areaId/** 区域 */, Integer[] devicescb /** 设备类型 */, String city) {
		List<DeviceInfoDto> result = new ArrayList<DeviceInfoDto>();
		
		if(city != null) {
			Area query = new Area();
			query.setName(city);
			List<Area> area = areaDAO.selectDynamic(query);
			if(CollectionUtils.isEmpty(area)) {
				log.info("{}找不到对应的area", city);
//				return result;
			}
//			areaId = area.get(0).getId();
		}
		areaId = null;
		List<DeviceInfo> devices = deviceInfoService.selectByAreaAndDeviceTypes(areaId, CollectionUtils.arrayToList(devicescb));
		
		if(!CollectionUtils.isEmpty(devices)) {
			for(DeviceInfo device : devices) {
				DeviceInfoDto dto = new DeviceInfoDto();
				BeanUtils.copyProperties(device, dto);
				result.add(dto);
			}
		}
		return result;
	}
	@RequestMapping(value = "/device/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, DeviceInfo deviceInfo) {
		if(CheckMobileUtils.isPhone(request.getHeader( "USER-AGENT" ).toLowerCase())) {
			return new ModelAndView("/m/devicelist");
		}
		else {
			return new ModelAndView("/device/list");
		}
	}
	@RequestMapping(value = "/device/getMaintainInfo")
	public ModelAndView getMaintainInfo(HttpServletRequest request, HttpServletResponse response, DeviceInfo deviceInfo) {
		if(CheckMobileUtils.isPhone(request.getHeader( "USER-AGENT" ).toLowerCase())) {
			return new ModelAndView("/m/maintaininfo");
		}
		else {
			return new ModelAndView("/device/list");
		}
	}
	@RequestMapping(value = "/device/ajax/getMaintainInfo")
	@ResponseBody
	public BasePageResponse<MaintainInfoDto> getAjaxMaintainInfo(HttpServletRequest request, HttpServletResponse response, DeviceInfo deviceInfo) {
		if(CheckMobileUtils.isPhone(request.getHeader( "USER-AGENT" ).toLowerCase())) {
			BasePageResponse<MaintainInfoDto> result = new BasePageResponse<MaintainInfoDto>();
			MaintainInfoDto dto = new MaintainInfoDto();
			dto.setCreateTime(new Date());
			dto.setId(1);
			dto.setCreateUser("晓光");
			dto.setDeviceId(1);
			dto.setImei("672502000422461");
			MaintainInfoDto dto1 = new MaintainInfoDto();
			dto1.setId(2);
			dto1.setCreateTime(new Date());
			dto1.setCreateUser("晓光");
			dto1.setDeviceId(1);
			dto1.setImei("672502000422462");
			result.setPageSize(50);
			result.setRecords(1l);
			result.setRows(Arrays.asList(dto,dto1));
			return result;
		}
		else {
			return null;
		}
	}
	@RequestMapping(value = "/device/updateStatus")
	@ResponseBody
	public void updateStatus(DeviceInfo deviceInfo) {
		deviceInfoService.update(deviceInfo, false);
	}
	@RequestMapping(value = "/device/getAll")
	@ResponseBody
	public BasePageResponse<DeviceInfoDto> getAll(HttpServletResponse response, DeviceInfo deviceInfo) {
		return deviceInfoService.queryPageListByDeviceInfoDO(deviceInfo);
	}
}
