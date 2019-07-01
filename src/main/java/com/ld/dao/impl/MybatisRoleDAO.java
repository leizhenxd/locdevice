package com.ld.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.dao.RoleDAO;
import com.ld.model.Role;

@Component(value="roleDAO")
public class MybatisRoleDAO extends MybatisBaseDAO implements RoleDAO {
	@Override
	public Long insert(Role Role) {
		int i = getSqlSession().insert("com.ld.model.RoleMapper.MybatisRoleDAO_insert", Role);
		if (i > 0) {
			return Long.valueOf(Role.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(Role role) {
		return getSqlSession().update("com.ld.model.RoleMapper.MybatisRoleDAO_updateById", role);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.RoleMapper.MybatisRoleDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(Role role) {
		return getSqlSession().update("com.ld.model.RoleMapper.MybatisRoleDAO_update_dynamic", role);
	}

	@Override
	public Role selectById(Integer id) {
		return getSqlSession().selectOne("com.ld.model.RoleMapper.MybatisRoleDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(Role role) {
		
		return getSqlSession().selectOne("com.ld.model.RoleMapper.MybatisRoleDAO_select_dynamic_count", role);
	}

	@Override
	public List<Role> selectDynamic(Role role) {
		
		return getSqlSession().selectList("com.ld.model.RoleMapper.MybatisRoleDAO_select_dynamic", role);
	}

	@Override
	public List<Role> selectDynamicPageQuery(Role role) {
		return getSqlSession().selectList("com.ld.model.RoleMapper.MybatisRoleDAO_select_dynamic_page_query", role);
	}

}
