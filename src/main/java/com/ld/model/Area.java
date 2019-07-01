package com.ld.model;

/**
* 
* @author haisheng.long Thu Jun 15 19:53:21 CST 2017
*/

public class Area extends BaseModel {

/**
	 * 
	 */
	private static final long serialVersionUID = -1186060418936171510L;

/** 主键 */
private Long id;

/** 区域名称 */
private String name;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 区域名称
* @param name
*/
	public void setName(String name) {
	this.name = name;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 区域名称
* @return name
*/
	public String getName() {
	return name;
}

}