package com.lys.meeting.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.lys.meeting.dao.RoomDao;
import com.lys.meeting.model.Room;





public class DataUtils {
	static Connection conn=ConnDB.getConnection();
	
	public static String getCount(String sql) {
		int count=0;
		try {
			ResultSet set=query(sql, new Object[] {});
			if (set.next()) {
				count=set.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(count);
	}
	
	public static ResultSet query(String sql, Object[] objects) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=null;
//        �ж�Object�Ƿ�Ϊ�գ�Ϊ��ֱ��ִ��sql���
        if (objects == null) {
            resultSet = pst.executeQuery();
        } else {
            for (int i = 0; i < objects.length; i++) {
                pst.setObject(i+1,objects[i]);
            }
            resultSet = pst.executeQuery();
        }
        return resultSet;
    }
	
    public static int Update(String sql,Object[] objects) throws SQLException {
        Connection conn = ConnDB.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//      �ж������Ƿ�Ϊ��
        try {
            if (objects == null || objects.equals("")) {
                return pst.executeUpdate();
            } else {
                for (int i = 0; i < objects.length; i++) {
                    pst.setObject(i + 1, objects[i]);
                }
                return pst.executeUpdate();
            }
        } finally {
            
        }
 
    }
	
	public static Object[][] getSetArrays(ResultSet set) throws SQLException {
        Object[][] objects;
        set.last();
        int rowcount = set.getRow();
        ResultSetMetaData rsm = set.getMetaData();
        int colcount = rsm.getColumnCount();//��ȡ����
//      ������ά����
        objects = new Object[rowcount][colcount+1];
        set.first();
        for (int i = 0; i < rowcount; i++) {
            objects[i][0]=i+1;//��ÿһ�еĵ�һ��������
            for (int j = 1; j < colcount+1; j++) {
                objects[i][j] = set.getObject(j);
            } 
            set.next();
        }
        return objects;
    }
	
	
//	public static void main(String[] args) {
//		String sql="select * from room";
//		System.out.println(2);
//		try {
//			ResultSet set=query(sql, new Object[] {});
//			System.out.println(Arrays.toString(getSetArrays(set)));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	

}
