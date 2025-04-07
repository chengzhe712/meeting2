package com.lys.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lys.meeting.model.User;

public class UserDao extends BaseDAO{
	
//	根据用户名查找一个用户
	public User selectOne(String account) {
		String sql="select * from user where account=?";
		return super.select(sql, new Object[] {account});
	}

	
//	信息修改
	public int update(User user) {
		String sql="update user set real_name=?,apartment=?,phone=?,email=?,password=?,"
				+ "duty=? where id=?";
		return super.update(sql, new Object[] {user.getRealName(),
				user.getApartment(),
				user.getPhone(),
				user.getEmail(),
				user.getPassword(),
				user.getDuty(),
				user.getId()});
	}

	@Override
	public User rowMapper(ResultSet rs) {
		// TODO Auto-generated method stub
		User user=new User();
		try {
			user.setId(rs.getInt(1));
			user.setAccount(rs.getString(2));
			user.setRealName(rs.getString(3));
			user.setApartment(rs.getString(4));
			user.setPhone(rs.getString(5));
			user.setEmail(rs.getString(6));
			user.setPassword(rs.getString(7));
			user.setDuty(rs.getString(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
