/**
 * 
 */
package com.lys.meeting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lys.meeting.model.Room;



/**
 * @author ʤʤ
 *
 */
public class RoomDao extends BaseDAO {
	
//	���ݻ��������Ʋ�ѯ
	public Room search(String name) {
		String sql="select * from room where name=?";
		return super.select(sql, new Object[] {name});
	}
	
//  ͨ��id��room
	public Room search(Integer id) {
		String sql="select * from room where id=?";
		return super.select(sql, new Object[] {id});
	}
	
	
	public List<Room> selectAll(){
		String sql="select * from room;";
		return super.selectAll(sql, new Object[] {});
	}
	
	@Override
	public Room rowMapper(ResultSet rs) {
		// TODO Auto-generated method stub
		Room room=new Room();
		
		try {
			room.setId(rs.getInt(1));
			room.setName(rs.getString(2));
			room.setAddress(rs.getString(3));
			room.setSeatNum(rs.getInt(4));
			room.setStatus(rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	
	public static void main(String[] args) {
		RoomDao roomDao=new RoomDao();
		System.out.println(roomDao.selectAll());
	}

}
