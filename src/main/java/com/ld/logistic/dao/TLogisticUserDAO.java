package com.ld.logistic.dao;

import java.util.List;

import com.ld.logistic.domain.TLogisticUserDO;


 /**
 *  DAO
 * @author haisheng.Long 2018-03-11 09:28:17
 */
public interface TLogisticUserDAO {

	/**
	 * 插入  
	 * @param tLogisticUserDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long insert(TLogisticUserDO tLogisticUserDO) ;

	/**
	 * 根据ID更新 全部属性
	 * @param tLogisticUserDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer update(TLogisticUserDO tLogisticUserDO) ;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 删除行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer deleteById(Long id) ;

	/**
	 * 动态更新 部分属性，包括全部
	 * @param tLogisticUserDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer updateDynamic(TLogisticUserDO tLogisticUserDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticUserDO
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	TLogisticUserDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticUserDO
	 * @return 记录条数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long selectCountDynamic(TLogisticUserDO tLogisticUserDO) ;

	/**
	 * 根据   动态返回  列表
	 * @param tLogisticUserDO
	 * @return List<TLogisticUserDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<TLogisticUserDO> selectDynamic(TLogisticUserDO tLogisticUserDO) ;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param tLogisticUserDO start,pageSize属性必须指定
	 * @return List<TLogisticUserDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<TLogisticUserDO> selectDynamicPageQuery(TLogisticUserDO tLogisticUserDO) ;
}
