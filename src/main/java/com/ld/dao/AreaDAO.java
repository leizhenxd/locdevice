package com.ld.dao;

import java.util.List;

import com.ld.model.Area;


 /**
 *  DAO
 * @author haisheng.Long 2017-06-15 19:53:21
 */
public interface AreaDAO {

	/**
	 * 插入  
	 * @param areaDO
	 * @return 主键
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Long insert(Area areaDO) ;

	/**
	 * 根据ID更新 全部属性
	 * @param areaDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Integer update(Area areaDO) ;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 删除行数
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Integer deleteById(Long id) ;

	/**
	 * 动态更新 部分属性，包括全部
	 * @param areaDO
	 * @return 更新行数
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Integer updateDynamic(Area areaDO) ;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return AreaDO
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Area selectById(Long id) ;

	/**
	 * 根据   动态返回记录数
	 * @param areaDO
	 * @return 记录条数
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	Long selectCountDynamic(Area areaDO) ;

	/**
	 * 根据   动态返回  列表
	 * @param areaDO
	 * @return List<AreaDO>
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	List<Area> selectDynamic(Area areaDO) ;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param areaDO start,pageSize属性必须指定
	 * @return List<AreaDO>
	 * @
	 * @author longhaisheng 2017-06-15 19:53:21
	 */
	List<Area> selectDynamicPageQuery(Area areaDO) ;
}
