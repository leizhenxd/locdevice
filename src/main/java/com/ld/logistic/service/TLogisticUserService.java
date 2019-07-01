package com.ld.logistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.logistic.dao.TLogisticUserDAO;
import com.ld.logistic.domain.TLogisticUserDO;
import com.ld.model.User;
import com.ld.response.BasePageResponse;
 
@Service
public class TLogisticUserService {

	@Autowired
	TLogisticUserDAO userDao;
	
	public BasePageResponse<TLogisticUserDO> getPageList(TLogisticUserDO user) {
		try {
			BasePageResponse<TLogisticUserDO> response = new BasePageResponse<TLogisticUserDO>();
			response.setRecords(userDao.selectCountDynamic(user));
			response.setPageSize(user.getRows());
			response.setRows(userDao.selectDynamicPageQuery(user));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
