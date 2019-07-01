package com.ld.logistic.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.logistic.dao.TLogisticParcelDAO;
import com.ld.logistic.domain.TLogisticParcelDO;

@Component(value="tLogisticParcelDAO")
public class MybatisTLogisticParcelDAO extends MybatisBaseDAO implements TLogisticParcelDAO {
	public Long insert(TLogisticParcelDO tLogisticParcelDO) {
		int i = getSqlSession().insert("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_insert", tLogisticParcelDO);
		if (i > 0) {
			return Long.valueOf(tLogisticParcelDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TLogisticParcelDO tLogisticParcelDO) {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_updateById", tLogisticParcelDO);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(TLogisticParcelDO tLogisticParcelDO) {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_update_dynamic", tLogisticParcelDO);
	}

	@Override
	public TLogisticParcelDO selectById(Long id) {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(TLogisticParcelDO tLogisticParcelDO) {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_select_dynamic_count", tLogisticParcelDO);
	}

	@Override
	public List<TLogisticParcelDO> selectDynamic(TLogisticParcelDO tLogisticParcelDO) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_select_dynamic", tLogisticParcelDO);
	}

	@Override
	public List<TLogisticParcelDO> selectDynamicPageQuery(TLogisticParcelDO tLogisticParcelDO) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticParcelMapper.MybatisTLogisticParcelDAO_select_dynamic_page_query", tLogisticParcelDO);
	}

}
