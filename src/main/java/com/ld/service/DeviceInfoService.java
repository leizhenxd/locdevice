package com.ld.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ld.dao.DeviceInfoDAO;
import com.ld.model.DeviceInfo;
import com.ld.response.BasePageResponse;
import com.ld.response.dto.DeviceInfoDto;

@Service(value="deviceInfoService")
public class DeviceInfoService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DeviceInfoDAO deviceInfoDAO;

	
	public Long insert(DeviceInfo deviceInfoDO) {
		
			return deviceInfoDAO.insert(deviceInfoDO);
	}

//	
//	public int updateById(DeviceInfo deviceInfoDO) {
//		
//			return (Integer) deviceInfoDAO.updateById(deviceInfoDO);
//		
//			
//            
//		}
//	}

	
	public int update(DeviceInfo deviceInfoDO,boolean isAllField) {
		
			if(isAllField){
				return (Integer) deviceInfoDAO.update(deviceInfoDO);
			}else{
				return (Integer) deviceInfoDAO.updateDynamic(deviceInfoDO);
			}
	}

	
	public int deleteById(Long id) {
		
			return (Integer) deviceInfoDAO.deleteById(id);
		
	}

//	
//	public int updateDynamic(DeviceInfo deviceInfoDO) {
//		
//			return (Integer) deviceInfoDAO.updateDynamic(deviceInfoDO);
//		
//			
//            
//		}
//	}

	
	public DeviceInfo selectById(Long id) {
		
			return deviceInfoDAO.selectById(id);
	}

	
	public Long selectCountDynamic(DeviceInfo deviceInfoDO) {
		
			return deviceInfoDAO.selectCountDynamic(deviceInfoDO);
	}

	
	public List<DeviceInfo> selectDynamic(DeviceInfo deviceInfoDO) {
			return deviceInfoDAO.selectDynamic(deviceInfoDO);
	}
	

	private List<DeviceInfo> selectDynamicPageQuery(DeviceInfo deviceInfoDO) {
		
			return deviceInfoDAO.selectDynamicPageQuery(deviceInfoDO);
	}

	public BasePageResponse<DeviceInfoDto> queryPageListByDeviceInfoDO(DeviceInfo deviceInfoDO) {
		if (deviceInfoDO != null) {
			Long totalCount = this.selectCountDynamic(deviceInfoDO);
			List<DeviceInfo> resultList = this.selectDynamicPageQuery(deviceInfoDO);
			
			List<DeviceInfoDto> result = new ArrayList<DeviceInfoDto>();
			
			if(!CollectionUtils.isEmpty(resultList)) {
				for(DeviceInfo device : resultList) {
					DeviceInfoDto dto = new DeviceInfoDto();
					BeanUtils.copyProperties(device, dto);
					result.add(dto);
				}
			}
			
			BasePageResponse<DeviceInfoDto> response = new BasePageResponse<DeviceInfoDto>();
			
			response.setRecords(totalCount);
			response.setPageSize(deviceInfoDO.getRows());
			response.setRows(result);
			return response;
		}
		return new BasePageResponse<DeviceInfoDto>();
	}

	public List<DeviceInfo> selectByAreaAndDeviceTypes(Long areaId, List<Integer> deviceTypes) {
		return deviceInfoDAO.selectByAreaAndDeviceTypes(areaId, deviceTypes);
	}
}
