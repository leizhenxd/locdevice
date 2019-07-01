package com.ld.logistic.service;

import java.util.List;

import com.ld.logistic.domain.TLogisticUserExpendDO;
import com.ld.response.BasePageResponse;
 /**
 *  Service
 * @author haisheng.long 2018-03-24 14:53:13
 */
public interface TLogisticUserExpendService {

	/**
	 * 插入  
	 * @param tLogisticUserExpendDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	Long insert(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据TLogisticUserExpendDO对象更新 
	 * @param tLogisticUserExpendDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	int update(TLogisticUserExpendDO tLogisticUserExpendDO,boolean isAllField) ;

//	/**
//	 * 根据ID更新 全部字段
//	 * @param tLogisticUserExpendDO
//	 * @return 更新行数
//	 * @
//	 * @author longhaisheng 2018-03-24 14:53:13
//	 */
//	int updateById(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 物理删除行
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	int deleteById(Long id) ;

//	/**
//	 * 动态更新 部分字段
//	 * @param tLogisticUserExpendDO
//	 * @return 更新行数
//	 * @
//	 * @author longhaisheng 2018-03-24 14:53:13
//	 */
//	int updateDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticUserExpendDO
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	TLogisticUserExpendDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticUserExpendDO
	 * @return 记录数
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	Long selectCountDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 动态返回  列表
	 * @param tLogisticUserExpendDO
	 * @return List<TLogisticUserExpendDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	List<TLogisticUserExpendDO> selectDynamic(TLogisticUserExpendDO tLogisticUserExpendDO) ;

	/**
	 * 动态返回  分页列表
	 * @param tLogisticUserExpendDO
	 * @return BasePageResponse<TLogisticUserExpendDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	BasePageResponse<TLogisticUserExpendDO> queryBasePageResponseListByTLogisticUserExpendDO(TLogisticUserExpendDO tLogisticUserExpendDO);

	/**
	 * 动态返回  分页列表
	 * @param tLogisticUserExpendDO
	 * @param startBasePageResponse 起始页
	 * @param BasePageResponseSize 每页记录数
	 * @return BasePageResponse<TLogisticUserExpendDO>
	 * @
	 * @author longhaisheng 2018-03-24 14:53:13
	 */
	BasePageResponse<TLogisticUserExpendDO> queryBasePageResponseListByTLogisticUserExpendDOAndStartBasePageResponseSize(TLogisticUserExpendDO tLogisticUserExpendDO,int startBasePageResponse,int BasePageResponseSize);

}
