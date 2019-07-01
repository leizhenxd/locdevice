package com.ld.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ld.dao.DeviceInfoDAO;
import com.ld.model.DeviceInfo;

@Component(value="deviceInfoDAO")
public class MybatisDeviceInfoDAO extends MybatisBaseDAO implements DeviceInfoDAO {
	public Long insert(DeviceInfo deviceInfoDO) {
		int i = getSqlSession().insert("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_insert", deviceInfoDO);
		if (i > 0) {
			return Long.valueOf(deviceInfoDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(DeviceInfo deviceInfoDO) {
		return getSqlSession().update("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_updateById", deviceInfoDO);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(DeviceInfo deviceInfoDO) {
		return getSqlSession().update("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_update_dynamic", deviceInfoDO);
	}

	@Override
	public DeviceInfo selectById(Long id) {
		return getSqlSession().selectOne("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(DeviceInfo deviceInfoDO) {
		return getSqlSession().selectOne("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_select_dynamic_count", deviceInfoDO);
	}

	@Override
	public List<DeviceInfo> selectDynamic(DeviceInfo deviceInfoDO) {
		return getSqlSession().selectList("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_select_dynamic", deviceInfoDO);
	}

	@Override
	public List<DeviceInfo> selectDynamicPageQuery(DeviceInfo deviceInfoDO) {
		return getSqlSession().selectList("com.ld.model.DeviceInfoMapper.MybatisDeviceInfoDAO_select_dynamic_page_query", deviceInfoDO);
	}

	@Override
	public List<DeviceInfo> selectByAreaAndDeviceTypes(Long areaId, List<Integer> deviceTypes) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("areaId", areaId);
		params.put("deviceTypes", deviceTypes);
		
		return getSqlSession().selectList("com.ld.model.DeviceInfoMapper.selectByAreaAndDeviceTypes", params);
	}

}
