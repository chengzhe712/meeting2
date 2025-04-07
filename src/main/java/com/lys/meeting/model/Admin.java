package com.lys.meeting.model;

import lombok.Setter;

/**
 * @description admin
 * @author liyansheng.blog.csdn.net
 * @date 2022-06-19
 */
@Setter
public class Admin {

	/**
	 * Ö÷¼ü
	 */
	private Integer id;

	/**
	 * ĞÕÃû
	 */
	private String name;

	/**
	 * µç»°
	 */
	private String phone;

	/**
	 * ÕËºÅid
	 */
	private String account;

	/**
	 * ÃÜÂë
	 */
	private String password;

	/**
	 * ÓÊÏä
	 */
	private String email;

	public Admin() {
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	
}
