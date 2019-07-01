package com.ld.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.dao.UserDAO;
import com.ld.model.User;

@Component(value="userDAO")
public class MybatisUserDAO extends MybatisBaseDAO implements UserDAO {
	@Override
	public Long insert(User User) {
		int i = getSqlSession().insert("com.ld.model.UserMapper.MybatisUserDAO_insert", User);
		if (i > 0) {
			return Long.valueOf(User.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(User user) {
		return getSqlSession().update("com.ld.model.UserMapper.MybatisUserDAO_updateById", user);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.model.UserMapper.MybatisUserDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(User user) {
		return getSqlSession().update("com.ld.model.UserMapper.MybatisUserDAO_update_dynamic", user);
	}

	@Override
	public User selectById(Integer id) {
		return getSqlSession().selectOne("com.ld.model.UserMapper.MybatisUserDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(User user) {
		
		return getSqlSession().selectOne("com.ld.model.UserMapper.MybatisUserDAO_select_dynamic_count", user);
	}

	@Override
	public List<User> selectDynamic(User user) {
		
		return getSqlSession().selectList("com.ld.model.UserMapper.MybatisUserDAO_select_dynamic", user);
	}

	@Override
	public List<User> selectDynamicPageQuery(User user) {
		return getSqlSession().selectList("com.ld.model.UserMapper.MybatisUserDAO_select_dynamic_page_query", user);
	}

}
