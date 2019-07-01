package com.ld.dao;

import java.util.List;

import com.ld.model.Role;

public interface RoleDAO {

	Integer update(Role role);

	Integer deleteById(Long id);

	Integer updateDynamic(Role role);

	Role selectById(Integer id);

	Long selectCountDynamic(Role role);

	List<Role> selectDynamic(Role role);

	List<Role> selectDynamicPageQuery(Role role);

	Long insert(Role role);

}
