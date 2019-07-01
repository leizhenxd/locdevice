package com.ld.logistic.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.logistic.dao.TLogisticScanRecordDAO;
import com.ld.logistic.domain.TLogisticScanRecordDO;
import com.ld.response.logistic.DeliverHistory;
import com.ld.response.logistic.ScanRecordHistory;

@Component(value="tLogisticScanRecordDAO")
public class MybatisTLogisticScanRecordDAO extends MybatisBaseDAO implements TLogisticScanRecordDAO {
	public Long insert(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		int i = getSqlSession().insert("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_insert", tLogisticScanRecordDO);
		if (i > 0) {
			return Long.valueOf(tLogisticScanRecordDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_updateById", tLogisticScanRecordDO);
	}

	@Override
	public Integer deleteById(Long id)  {
		return getSqlSession().delete("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_update_dynamic", tLogisticScanRecordDO);
	}

	@Override
	public TLogisticScanRecordDO selectById(Long id)  {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_select_dynamic_count", tLogisticScanRecordDO);
	}

	@Override
	public List<TLogisticScanRecordDO> selectDynamic(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_select_dynamic", tLogisticScanRecordDO);
	}

	@Override
	public List<ScanRecordHistory> selectDynamicPageQuery(TLogisticScanRecordDO tLogisticScanRecordDO)  {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticScanRecordMapper.MybatisTLogisticScanRecordDAO_select_dynamic_page_query", tLogisticScanRecordDO);
	}

	@Override
	public List<DeliverHistory> queryDeliverHistory(Long userId) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticScanRecordMapper.queryDeliverHistory", userId);
	}

	@Override
	public List<TLogisticScanRecordDO> queryParcelInfoById(Long parcelId) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticScanRecordMapper.queryParcelInfoById", parcelId);
	}

}
