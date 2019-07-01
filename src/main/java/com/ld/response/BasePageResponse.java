package com.ld.response;

import java.util.List;

public class BasePageResponse<T> {
	// 总页数
	private Long total=1l;
	// 总记录数
	private Long records=1l;
	
	private Integer pageSize=1;
	private List<T> rows;
	public Long getTotal() {
		return ((records%pageSize>0)?((records/pageSize)+1):(records/pageSize));
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public static void main(String[] args) {
		BasePageResponse<String> s = new BasePageResponse<String>();
	}
}
