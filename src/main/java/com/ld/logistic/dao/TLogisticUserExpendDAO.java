package com.ld.logistic.dao;

import java.util.List;

import com.ld.logistic.domain.TLogisticUserExpendDO;


 /**
 *  DAO
 * @author haisheng.Long 2018-03-11 09:28:17
 */
public interface TLogisticUserExpendDAO {

	/**
	 * 插入  
	 * @param tLogisticUserExpendDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long insert(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据ID更新 全部属性
	 * @param tLogisticUserExpendDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer update(TLogisticUserExpendDO tLogisticUserExpendDO) ;

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
	 * @param tLogisticUserExpendDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer updateDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticUserExpendDO
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	TLogisticUserExpendDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticUserExpendDO
	 * @return 记录条数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long selectCountDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据   动态返回  列表
	 * @param tLogisticUserExpendDO
	 * @return List<TLogisticUserExpendDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<TLogisticUserExpendDO> selectDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param tLogisticUserExpendDO start,pageSize属性必须指定
	 * @return List<TLogisticUserExpendDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<TLogisticUserExpendDO> selectDynamicPageQuery(TLogisticUserExpendDO tLogisticUserExpendDO) ;
}
