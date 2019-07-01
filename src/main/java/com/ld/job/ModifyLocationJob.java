package com.ld.job;

import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ld.dao.AreaDAO;
import com.ld.model.Area;
import com.ld.model.DeviceInfo;
import com.ld.service.DeviceInfoService;
import com.ld.util.GPSUtils;

@Component
public class ModifyLocationJob {

	Logger log = LoggerFactory.getLogger(ModifyLocationJob.class);
	
	@Autowired
	DeviceInfoService deviceInfoService;
	
	@Autowired
	AreaDAO areaDao;
	
	@Scheduled(cron="0 30 * * * ?")
	public void execute() {
		log.info("job...");
		List<DeviceInfo> all = deviceInfoService.selectDynamic(new DeviceInfo());
		if(!CollectionUtils.isEmpty(all)) {
			for(DeviceInfo device : all) {
				if(!StringUtils.isEmpty(device.getDeviceOriginId())) {
					Map<String, String> address = GPSUtils.getDeviceLocation(device.getDeviceOriginId());
					if(!address.get("province").equals(device.getProvince())
							|| !address.get("city").equals(device.getCity())) {
						Area query = new Area();
						query.setName(address.get("province"));
						List<Area> areas = areaDao.selectDynamic(query);
						Area area = null;
						if(!CollectionUtils.isEmpty(areas)) {
							area = areas.get(0);
						}
						if(area != null) {
							device.setAreaId(area.getId());
							device.setAreaName(area.getName());
							
						}
						device.setLat(address.get("lat"));
						device.setLng(address.get("lng"));
						device.setProvince(address.get("province"));
						device.setCity(address.get("city"));
						deviceInfoService.update(device, true);
						log.info("device:{} location modified.", device.getDeviceOriginId());
					}
				}
			}
		}
	}
}
