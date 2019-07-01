package com.ld.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.dao.RoleFunctionDAO;
import com.ld.model.RoleFunction;

@Component(value="roleFunctionDAO")
public class MybatisRoleFunctionDAO extends MybatisBaseDAO implements RoleFunctionDAO {
	@Override
	public Long insert(RoleFunction RoleFunction) {
		int i = getSqlSession().insert("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_insert", RoleFunction);
		if (i > 0) {
			return Long.valueOf(RoleFunction.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(RoleFunction roleFunction) {
		return getSqlSession().update("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_updateById", roleFunction);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(RoleFunction roleFunction) {
		return getSqlSession().update("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_update_dynamic", roleFunction);
	}

	@Override
	public RoleFunction selectById(Integer id) {
		return getSqlSession().selectOne("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(RoleFunction roleFunction) {
		
		return getSqlSession().selectOne("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_select_dynamic_count", roleFunction);
	}

	@Override
	public List<RoleFunction> selectDynamic(RoleFunction roleFunction) {
		
		return getSqlSession().selectList("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_select_dynamic", roleFunction);
	}

	@Override
	public List<RoleFunction> selectDynamicPageQuery(RoleFunction roleFunction) {
		return getSqlSession().selectList("com.ld.model.RoleFunctionMapper.MybatisRoleFunctionDAO_select_dynamic_page_query", roleFunction);
	}

}
