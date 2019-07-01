package com.ld.dao;

import java.util.List;

import com.ld.model.DeviceInfo;


 /**
 * 设备信息 DAO
 * @author haisheng.Long 2017-06-15 19:53:22
 */
public interface DeviceInfoDAO {

	/**
	 * 插入  设备信息
	 * @param deviceInfoDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	Long insert(DeviceInfo deviceInfoDO);

	/**
	 * 根据ID更新 设备信息全部属性
	 * @param deviceInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	Integer update(DeviceInfo deviceInfoDO);

	/**
	 * 根据ID删除 设备信息
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	Integer deleteById(Long id);

	/**
	 * 动态更新 设备信息部分属性，包括全部
	 * @param deviceInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	Integer updateDynamic(DeviceInfo deviceInfoDO);

	/**
	 * 根据ID查询 一个 设备信息
	 * @param id
	 * @return DeviceInfoDO
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	DeviceInfo selectById(Long id);

	/**
	 * 根据  设备信息 动态返回记录数
	 * @param deviceInfoDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	Long selectCountDynamic(DeviceInfo deviceInfoDO);

	/**
	 * 根据  设备信息 动态返回 设备信息 列表
	 * @param deviceInfoDO
	 * @return List<DeviceInfoDO>
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	List<DeviceInfo> selectDynamic(DeviceInfo deviceInfoDO);

	/**
	 * 根据  设备信息 动态返回 设备信息 Limit 列表
	 * @param deviceInfoDO start,pageSize属性必须指定
	 * @return List<DeviceInfoDO>
	 * @throws DAOException
	 * @author longhaisheng 2017-06-15 19:53:22
	 */
	List<DeviceInfo> selectDynamicPageQuery(DeviceInfo deviceInfoDO);

	List<DeviceInfo> selectByAreaAndDeviceTypes(Long areaId, List<Integer> deviceTypes);
}
