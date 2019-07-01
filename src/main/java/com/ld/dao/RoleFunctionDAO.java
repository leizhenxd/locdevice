package com.ld.dao;

import java.util.List;

import com.ld.model.RoleFunction;

public interface RoleFunctionDAO {

	Integer update(RoleFunction roleFunction);

	Integer deleteById(Long id);

	Integer updateDynamic(RoleFunction roleFunction);

	RoleFunction selectById(Integer id);

	Long selectCountDynamic(RoleFunction roleFunction);

	List<RoleFunction> selectDynamic(RoleFunction roleFunction);

	List<RoleFunction> selectDynamicPageQuery(RoleFunction roleFunction);

	Long insert(RoleFunction roleFunction);

}
