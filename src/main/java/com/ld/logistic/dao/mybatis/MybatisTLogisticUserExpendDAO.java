package com.ld.logistic.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.logistic.dao.TLogisticUserExpendDAO;
import com.ld.logistic.domain.TLogisticUserExpendDO;

@Component(value="tLogisticUserExpendDAO")
public class MybatisTLogisticUserExpendDAO extends MybatisBaseDAO implements TLogisticUserExpendDAO {
	public Long insert(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		int i = getSqlSession().insert("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_insert", tLogisticUserExpendDO);
		if (i > 0) {
			return Long.valueOf(tLogisticUserExpendDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_updateById", tLogisticUserExpendDO);
	}

	@Override
	public Integer deleteById(Long id)  {
		return getSqlSession().delete("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_update_dynamic", tLogisticUserExpendDO);
	}

	@Override
	public TLogisticUserExpendDO selectById(Long id)  {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_select_dynamic_count", tLogisticUserExpendDO);
	}

	@Override
	public List<TLogisticUserExpendDO> selectDynamic(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_select_dynamic", tLogisticUserExpendDO);
	}

	@Override
	public List<TLogisticUserExpendDO> selectDynamicPageQuery(TLogisticUserExpendDO tLogisticUserExpendDO)  {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticUserExpendMapper.MybatisTLogisticUserExpendDAO_select_dynamic_page_query", tLogisticUserExpendDO);
	}

}
