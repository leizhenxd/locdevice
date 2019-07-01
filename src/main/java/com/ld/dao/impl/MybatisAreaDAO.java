package com.ld.dao.impl;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.dao.AreaDAO;
import com.ld.model.Area;

@Component(value="areaDAO")
public class MybatisAreaDAO extends MybatisBaseDAO implements AreaDAO {
	public Long insert(Area areaDO) {
		int i = getSqlSession().insert("com.ld.model.AreaMapper.MybatisAreaDAO_insert", areaDO);
		if (i > 0) {
			return Long.valueOf(areaDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(Area areaDO) {
		return getSqlSession().update("com.ld.model.AreaMapper.MybatisAreaDAO_updateById", areaDO);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.AreaMapper.MybatisAreaDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(Area areaDO) {
		return getSqlSession().update("com.ld.model.AreaMapper.MybatisAreaDAO_update_dynamic", areaDO);
	}

	@Override
	public Area selectById(Long id) {
		return getSqlSession().selectOne("com.ld.model.AreaMapper.MybatisAreaDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(Area areaDO) {
		return getSqlSession().selectOne("com.ld.model.AreaMapper.MybatisAreaDAO_select_dynamic_count", areaDO);
	}

	@Override
	public List<Area> selectDynamic(Area areaDO) {
		return getSqlSession().selectList("com.ld.model.AreaMapper.MybatisAreaDAO_select_dynamic", areaDO);
	}

	@Override
	public List<Area> selectDynamicPageQuery(Area areaDO) {
		return getSqlSession().selectList("com.ld.model.AreaMapper.MybatisAreaDAO_select_dynamic_page_query", areaDO);
	}

}
