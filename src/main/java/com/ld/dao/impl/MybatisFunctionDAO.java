package com.ld.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.dao.FunctionDAO;
import com.ld.model.Function;

@Component(value="functionDAO")
public class MybatisFunctionDAO extends MybatisBaseDAO implements FunctionDAO {
	@Override
	public Long insert(Function Function) {
		int i = getSqlSession().insert("com.ld.model.FunctionMapper.MybatisFunctionDAO_insert", Function);
		if (i > 0) {
			return Long.valueOf(Function.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(Function function) {
		return getSqlSession().update("com.ld.model.FunctionMapper.MybatisFunctionDAO_updateById", function);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.FunctionMapper.MybatisFunctionDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(Function function) {
		return getSqlSession().update("com.ld.model.FunctionMapper.MybatisFunctionDAO_update_dynamic", function);
	}

	@Override
	public Function selectById(Integer id) {
		return getSqlSession().selectOne("com.ld.model.FunctionMapper.MybatisFunctionDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(Function function) {
		
		return getSqlSession().selectOne("com.ld.model.FunctionMapper.MybatisFunctionDAO_select_dynamic_count", function);
	}

	@Override
	public List<Function> selectDynamic(Function function) {
		
		return getSqlSession().selectList("com.ld.model.FunctionMapper.MybatisFunctionDAO_select_dynamic", function);
	}

	@Override
	public List<Function> selectDynamicPageQuery(Function function) {
		return getSqlSession().selectList("com.ld.model.FunctionMapper.MybatisFunctionDAO_select_dynamic_page_query", function);
	}

	@Override
	public List<Function> selectAllFunctionByRole(Long id) {
		return getSqlSession().selectList("com.ld.model.FunctionMapper.MybatisFunctionDAO_selectAllFunctionByRole", id);
	}

}
