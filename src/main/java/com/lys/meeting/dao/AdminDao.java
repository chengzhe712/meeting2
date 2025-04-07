package com.lys.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lys.meeting.model.Admin;


public class AdminDao extends BaseDAO{
//	根据用户名查找一个用户
	public Admin selectOne(String account) {
		String sql="select * from admin where account=?";
		return super.select(sql, new Object[] {account});
	}
//	通过id查询用户信息
	public Admin selectOneById(int id) {
		String sql="select * from admin where id=?";
		return super.select(sql, new Object[] {id});
	}
//	修改信息
	public int updateInfo(Admin admin) {
		String sql="UPDATE admin a set a.name=?,a.phone=?,a.email=?,a.`password`=? WHERE id=?";
		return super.update(sql, new Object[] {
				admin.getName(),
				admin.getPhone(),
				admin.getEmail(),
				admin.getPassword(),
				admin.getId()
		});
	}
	

	@Override
	public Admin rowMapper(ResultSet rs) {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		try {
			admin.setId(rs.getInt(1));
			admin.setName(rs.getString(2));
			admin.setPhone(rs.getString(3));
			admin.setAccount(rs.getString(4));
			admin.setPassword(rs.getString(5));
			admin.setEmail(rs.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	
	

}
