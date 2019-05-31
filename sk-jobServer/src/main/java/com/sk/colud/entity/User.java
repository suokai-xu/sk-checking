package com.sk.colud.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.sk.colud.annotation.Id;

/**
* 2019年5月7日 下午3:42:56
* @HXing xu
* sk-data-service
* 
**/

@Table(name="m_user")
public class User implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String password;
	private String salt;
	
	@Id(value="id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="salt")
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	

	
	
}
