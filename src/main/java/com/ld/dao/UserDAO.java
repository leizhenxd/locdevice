package com.ld.dao;

import java.util.List;

import com.ld.model.User;

public interface UserDAO {

	Integer update(User User) ;

	Integer deleteById(Long id) ;

	Integer updateDynamic(User User) ;

	User selectById(Integer id) ;

	Long selectCountDynamic(User User) ;

	List<User> selectDynamic(User User) ;

	List<User> selectDynamicPageQuery(User User) ;

	Long insert(User User) ;

}
