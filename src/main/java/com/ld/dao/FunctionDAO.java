package com.ld.dao;

import java.util.List;

import com.ld.model.Function;

public interface FunctionDAO {

	Integer update(Function function);

	Integer deleteById(Long id);

	Integer updateDynamic(Function function);

	Function selectById(Integer id);

	Long selectCountDynamic(Function function);

	List<Function> selectDynamic(Function function);

	List<Function> selectDynamicPageQuery(Function function);

	Long insert(Function function);

	List<Function> selectAllFunctionByRole(Long id);
}
