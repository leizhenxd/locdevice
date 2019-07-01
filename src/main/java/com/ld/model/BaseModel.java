package com.ld.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 6581029953195073637L;

	private Integer start;
	/** 每页记录数 */
	private Integer rows=10;
	
	private String sord;
	/** 起始页 */
	private Integer page=1;
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return (page-1)*rows;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
}
