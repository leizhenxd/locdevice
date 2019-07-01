package com.ld.logistic.dao;

import java.util.List;

import com.ld.logistic.domain.TLogisticParcelDO;


 /**
 *  DAO
 * @author haisheng.Long 2018-03-11 09:28:16
 */
public interface TLogisticParcelDAO {

	/**
	 * 插入  
	 * @param tLogisticParcelDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	Long insert(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据ID更新 全部属性
	 * @param tLogisticParcelDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	Integer update(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 删除行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	Integer deleteById(Long id) ;

	/**
	 * 动态更新 部分属性，包括全部
	 * @param tLogisticParcelDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	Integer updateDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticParcelDO
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	TLogisticParcelDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticParcelDO
	 * @return 记录条数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	Long selectCountDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据   动态返回  列表
	 * @param tLogisticParcelDO
	 * @return List<TLogisticParcelDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	List<TLogisticParcelDO> selectDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param tLogisticParcelDO start,pageSize属性必须指定
	 * @return List<TLogisticParcelDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:16
	 */
	List<TLogisticParcelDO> selectDynamicPageQuery(TLogisticParcelDO tLogisticParcelDO) ;
}
