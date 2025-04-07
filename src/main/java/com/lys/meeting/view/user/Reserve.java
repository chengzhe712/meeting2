package com.lys.meeting.view.user;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lys.meeting.dao.RoomDao;
import com.lys.meeting.model.Room;
import com.lys.meeting.utils.DataUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
//Reserve表示一个面板，用于显示和处理会议室预约的界面。
public class Reserve extends JPanel {

	private final JTextField jt;   //jt 是一个文本框，用于输入会议室的 ID。
	private final JTable table;   //table 是一个表格，用于显示用户的预约信息。
	RoomDao roomDao=new RoomDao();   //roomDao 是一个数据访问对象，用于与会议室相关的数据库操作。
	String[] header ={"序号","会议室名称","地址","座位数量","状态"};  //header 数组定义了表格的列标题。


	public Reserve(int id)  {
		//设置面板的边框和布局。
		setBorder(new TitledBorder(null, "预约", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		//“请输入会议室的id：”标签
		JLabel lblNewLabel = new JLabel("请输入会议室的ID：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(28, 26, 233, 21);
		add(lblNewLabel);

		//文本框-用于输入会议室id
		jt = new JTextField();
		jt.setBounds(28, 57, 241, 21);
		add(jt);
		jt.setColumns(10);
		
		JButton jb = new JButton("我要预约");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//首先获取用户输入的会议室id
				int roomId=Integer.parseInt(jt.getText());
				//通过 roomDao 查询会议室，如果会议室不存在或已被预定，弹出相应的提示框。
				Room room=roomDao.search(roomId);
				if (room==null) {
					JOptionPane.showMessageDialog(null, "查无此会议室！");
				}else if (!room.getStatus().equals("空闲")) {
					JOptionPane.showMessageDialog(null, "该会议室已被预定，请换一个！");
				}else {
					//如果会议室可用，则执行 SQL 插入语句，将预约记录添加到数据库中，并更新表格显示。
					String sql="INSERT INTO `order` VALUES(0,?,?,'审核中')";
					try {
						int i=DataUtils.Update(sql, new Object[] {id,roomId});
						if (i>0) {
							JOptionPane.showMessageDialog(null, "预约成功");
							showMine(id);
						}
						else {
							JOptionPane.showMessageDialog(null, "预约失败！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		jb.setFont(new Font("宋体", Font.PLAIN, 16));
		jb.setBounds(279, 56, 135, 21);
		add(jb);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 425, 150);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("我的预约一览：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 101, 233, 21);
		add(lblNewLabel_1);
		showMine(id);		//显示用户的预约信息


	}

	//showMine 方法通过 SQL 查询获取用户的预约信息，并将结果显示在表格中。
	public void showMine(int id) {
		String sql="SELECT\r\n"
				+ "	r.`name`,\r\n"
				+ "	r.address,\r\n"
				+ "	r.seat_num,\r\n"
				+ "	o.`status` \r\n"
				+ "FROM\r\n"
				+ "	`user` u\r\n"
				+ "	LEFT JOIN `order` o ON u.id = o.user_id\r\n"
				+ "	LEFT JOIN room r ON r.id = o.room_id \r\n"
				+ "WHERE\r\n"
				+ "	u.id ="+String.valueOf(id)+";";
		try {
			//使用 DataUtils 类的方法执行查询并处理结果集。
			TableModel model=new DefaultTableModel(DataUtils.getSetArrays(DataUtils.query(sql, new Object[] {})),header);
			table.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
