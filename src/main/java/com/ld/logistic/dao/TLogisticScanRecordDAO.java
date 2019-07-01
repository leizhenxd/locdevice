package com.ld.logistic.dao;

import java.util.List;

import com.ld.logistic.domain.TLogisticScanRecordDO;
import com.ld.response.logistic.DeliverHistory;
import com.ld.response.logistic.ScanRecordHistory;


 /**
 *  DAO
 * @author haisheng.Long 2018-03-11 09:28:17
 */
public interface TLogisticScanRecordDAO {

	/**
	 * 插入  
	 * @param tLogisticScanRecordDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long insert(TLogisticScanRecordDO tLogisticScanRecordDO) ;

	/**
	 * 根据ID更新 全部属性
	 * @param tLogisticScanRecordDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer update(TLogisticScanRecordDO tLogisticScanRecordDO) ;

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
	 * @param tLogisticScanRecordDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Integer updateDynamic(TLogisticScanRecordDO tLogisticScanRecordDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return TLogisticScanRecordDO
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	TLogisticScanRecordDO selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param tLogisticScanRecordDO
	 * @return 记录条数
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	Long selectCountDynamic(TLogisticScanRecordDO tLogisticScanRecordDO) ;

	/**
	 * 根据   动态返回  列表
	 * @param tLogisticScanRecordDO
	 * @return List<TLogisticScanRecordDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<TLogisticScanRecordDO> selectDynamic(TLogisticScanRecordDO tLogisticScanRecordDO) ;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param tLogisticScanRecordDO start,pageSize属性必须指定
	 * @return List<TLogisticScanRecordDO>
	 * @
	 * @author longhaisheng 2018-03-11 09:28:17
	 */
	List<ScanRecordHistory> selectDynamicPageQuery(TLogisticScanRecordDO tLogisticScanRecordDO) ;
	
	List<DeliverHistory> queryDeliverHistory (Long userId);
	List<TLogisticScanRecordDO> queryParcelInfoById (Long parcelId);
}
