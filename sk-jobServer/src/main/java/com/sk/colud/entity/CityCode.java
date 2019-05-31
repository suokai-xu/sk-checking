package com.sk.colud.entity;

import java.io.Serializable;

import com.sk.colud.annotation.Column;
import com.sk.colud.annotation.Id;
import com.sk.colud.annotation.Table;

/**
* 2019年5月9日 下午3:07:32
* @HXing xu
* sk-data-service
* 
**/
@Table(value = "m_city_code")
public class CityCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String pid;
	private String code;
	private String name;
	@Id(value = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(value = "pid")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(value = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(value = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
