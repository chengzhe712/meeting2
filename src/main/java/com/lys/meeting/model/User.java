package com.lys.meeting.model;



/**
 * @description user
 * @author liyansheng.blog.csdn.net
 * @date 2022-06-19
 */
public class User {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 账号
    */
    private String account;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 所在部门
    */
    private String apartment;

    /**
    * 电话
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 密码
    */
    private String password;

    /**
    * 职务
    */
    private String duty;

    public User() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", realName=" + realName + ", apartment=" + apartment
				+ ", phone=" + phone + ", email=" + email + ", password=" + password + ", duty=" + duty + "]";
	}
	
	
    
}
