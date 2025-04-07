package com.lys.meeting.model;

import lombok.Data;

/**
 * @description room
 * @author liyansheng.blog.csdn.net
 * @date 2022-06-19
 */
@Data
public class Room {
    /**
    * ����
    */
    private Integer id;

    /**
    * ����������
    */
    private String name;

    /**
    * �����ҵص�
    */
    private String address;

    /**
    * ��λ��
    */
    private Integer seatNum;
    
    private String status;

    public Room() {}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
    
    
}
