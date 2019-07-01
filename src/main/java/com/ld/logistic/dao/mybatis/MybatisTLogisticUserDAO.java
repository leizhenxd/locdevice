package com.ld.logistic.dao.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ld.logistic.dao.TLogisticUserDAO;
import com.ld.logistic.domain.TLogisticUserDO;

@Component(value="tLogisticUserDAO")
public class MybatisTLogisticUserDAO extends MybatisBaseDAO implements TLogisticUserDAO {
	public Long insert(TLogisticUserDO tLogisticUserDO) {
		int i = getSqlSession().insert("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_insert", tLogisticUserDO);
		if (i > 0) {
			return Long.valueOf(tLogisticUserDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(TLogisticUserDO tLogisticUserDO) {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_updateById", tLogisticUserDO);
	}

	@Override
	public Integer deleteById(Long id) {
		return getSqlSession().delete("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(TLogisticUserDO tLogisticUserDO) {
		return getSqlSession().update("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_update_dynamic", tLogisticUserDO);
	}

	@Override
	public TLogisticUserDO selectById(Long id) {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(TLogisticUserDO tLogisticUserDO) {
		return getSqlSession().selectOne("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_select_dynamic_count", tLogisticUserDO);
	}

	@Override
	public List<TLogisticUserDO> selectDynamic(TLogisticUserDO tLogisticUserDO) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_select_dynamic", tLogisticUserDO);
	}

	@Override
	public List<TLogisticUserDO> selectDynamicPageQuery(TLogisticUserDO tLogisticUserDO) {
		return getSqlSession().selectList("com.ld.logistic.domain.TLogisticUserMapper.MybatisTLogisticUserDAO_select_dynamic_page_query", tLogisticUserDO);
	}

}
