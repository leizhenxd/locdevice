package com.ld.logistic.service;

import java.util.List;

import com.ld.logistic.domain.TLogisticParcelDO;
import com.ld.response.BasePageResponse;
 /**
 *  Service
 * @author haisheng.long 2018-03-24 14:53:12
 */
public interface TLogisticParcelService {

	/**
	 * 插入  
	 * @param tLogisticParcelDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	Long insert(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据TLogisticParcelDO对象更新 
	 * @param tLogisticParcelDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	int update(TLogisticParcelDO tLogisticParcelDO,boolean isAllField) ;

//	/**
//	 * 根据ID更新 全部字段
//	 * @param tLogisticParcelDO
//	 * @return 更新行数
//	 * @
//	 * @author longhaisheng 2018-03-24 14:53:12
//	 */
//	int updateById(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 物理删除行
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	int deleteById(Long id) ;

//	/**
//	 * 动态更新 部分字段
//	 * @param tLogisticParcelDO
//	 * @return 更新行数
//	 * @
//	 * @author longhaisheng 2018-03-24 14:53:12
//	 */
//	int updateDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticParcelDO
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	TLogisticParcelDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticParcelDO
	 * @return 记录数
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	Long selectCountDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 动态返回  列表
	 * @param tLogisticParcelDO
	 * @return List<TLogisticParcelDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	List<TLogisticParcelDO> selectDynamic(TLogisticParcelDO tLogisticParcelDO) ;

	/**
	 * 动态返回  分页列表
	 * @param tLogisticParcelDO
	 * @return Page<TLogisticParcelDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	BasePageResponse<TLogisticParcelDO> queryPageListByTLogisticParcelDO(TLogisticParcelDO tLogisticParcelDO);

	/**
	 * 动态返回  分页列表
	 * @param tLogisticParcelDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<TLogisticParcelDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:12
	 */
	BasePageResponse<TLogisticParcelDO> queryPageListByTLogisticParcelDOAndStartPageSize(TLogisticParcelDO tLogisticParcelDO,int startPage,int pageSize);

}
