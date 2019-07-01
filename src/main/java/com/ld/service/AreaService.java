package com.ld.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ld.dao.AreaDAO;
import com.ld.model.Area;
import com.ld.response.BasePageResponse;

@Service(value="areaService")
public class AreaService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private AreaDAO areaDAO;

	public Long insert(Area areaDO) {
		return areaDAO.insert(areaDO);
	}

	public int update(Area areaDO,boolean isAllField) {
		if(isAllField){
			return (Integer) areaDAO.update(areaDO);
		}else{
			return (Integer) areaDAO.updateDynamic(areaDO);
		}
	}

	public int deleteById(Long id) {
		return (Integer) areaDAO.deleteById(id);
	}

	public int updateDynamic(Area areaDO) {
		return (Integer) areaDAO.updateDynamic(areaDO);
	}

	public Area selectById(Long id) {
		return areaDAO.selectById(id);
	}

	public Long selectCountDynamic(Area areaDO) {
		return areaDAO.selectCountDynamic(areaDO);
	}

	public List<Area> selectDynamic(Area areaDO) {
		return areaDAO.selectDynamic(areaDO);
	}
	

	private List<Area> selectDynamicPageQuery(Area areaDO) {
		return areaDAO.selectDynamicPageQuery(areaDO);
	}

	public BasePageResponse<Area> queryPageListByAreaDO(Area areaDO) {
		if (areaDO != null) {
			Long totalCount = this.selectCountDynamic(areaDO);
			List<Area> resultList = this.selectDynamicPageQuery(areaDO);

			BasePageResponse<Area> response = new BasePageResponse<Area>();
			
			response.setRecords(totalCount);
			response.setPageSize(areaDO.getRows());
			response.setRows(resultList);
			return response;
		}
		return new BasePageResponse<Area>();
	}
}
