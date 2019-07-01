package com.ld.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ld.dao.FunctionDAO;
import com.ld.dao.RoleDAO;
import com.ld.dao.RoleFunctionDAO;
import com.ld.dao.UserDAO;
import com.ld.model.Function;
import com.ld.model.RoleFunction;
import com.ld.model.User;
import com.ld.response.BasePageResponse;
import com.ld.util.EncryptUtils;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoleFunctionDAO roleFunctoinDAO;
	
	@Autowired
	FunctionDAO functionDAO;
	
	@Autowired
	RoleDAO roleDAO;
	

	public BasePageResponse<User> getPageList(User user) {
		try {
			BasePageResponse<User> response = new BasePageResponse<User>();
			response.setRecords(userDAO.selectCountDynamic(user));
			response.setPageSize(user.getRows());
			response.setRows(userDAO.selectDynamicPageQuery(user));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User selectUserById(Integer userId) {
		try {
			return userDAO.selectById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long insert(User user) {
		try {
			user.setSalt(UUID.randomUUID().toString().replace("-", "").substring(20));
			user.setPassword(EncryptUtils.md5(user.getPassword(), user.getSalt()));
			return userDAO.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.getId();
	}

	public User selectByPhone(String phone) {
		User query = new User();
		query.setPhone(phone);
		List<User> list = null;
		try {
			list = userDAO.selectDynamic(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	public boolean canVisit(User user, String url) {
		Function functionQuery = new Function();
		functionQuery.setUrl(url);
		List<Function> functions = functionDAO.selectDynamic(functionQuery);
		if(CollectionUtils.isEmpty(functions)) {
			return false;
		}
		if(functions.get(0).isFunction()) return true;
		RoleFunction roleFunctionQuery = new RoleFunction();
		roleFunctionQuery.setFunctionId(functions.get(0).getId());
		roleFunctionQuery.setRoleId(user.getRoleId());
		roleFunctionQuery.setDeleted(false);
		if(CollectionUtils.isEmpty(roleFunctoinDAO.selectDynamic(roleFunctionQuery))) {
			return false;
		}
		return true;
	}
	
	public List<Function> selectAllFunctionByRole(Long roleId) {
		return functionDAO.selectAllFunctionByRole(roleId);
	}
}
