package com.ld.logistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.logistic.dao.TLogisticScanRecordDAO;
import com.ld.logistic.domain.TLogisticScanRecordDO;
import com.ld.response.BasePageResponse;
import com.ld.response.logistic.ScanRecordHistory;
 /**
 *  Service
 * @author haisheng.long 2018-03-24 14:53:12
 */
@Service
public class TLogisticScanRecordService {
	@Autowired
	TLogisticScanRecordDAO scanRecordDao;
	public BasePageResponse<ScanRecordHistory> getPageList(TLogisticScanRecordDO user) {
		try {
			BasePageResponse<ScanRecordHistory> response = new BasePageResponse<ScanRecordHistory>();
			response.setRecords(scanRecordDao.selectCountDynamic(user));
			response.setPageSize(user.getRows());
			response.setRows(scanRecordDao.selectDynamicPageQuery(user));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
