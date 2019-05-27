package com.sk.colud.entity;

import java.io.Serializable;

/**
* 2019年5月7日 下午3:42:56
* @HXing xu
* sk-data-service
* 
**/

public class User implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String password;
	private String salt;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
 	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	

	
	
}
