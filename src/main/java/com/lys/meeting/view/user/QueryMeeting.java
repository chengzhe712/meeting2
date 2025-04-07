package com.lys.meeting.view.user;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lys.meeting.dao.RoomDao;
import com.lys.meeting.utils.DataUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class QueryMeeting extends JPanel {
	private JTextField jt;

	RoomDao roomDao=new RoomDao();
	//	表头信息
	String[] heads= {"序号","id","名称","地址","座位数","状态"};
	private JTable table;

	/**
	 * Create the panel.
	 * @throws SQLException
	 */
	public QueryMeeting(){
		setBorder(new TitledBorder(null, "查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel jl1 = new JLabel("会议室名称");
		jl1.setBounds(40, 48, 86, 18);
		add(jl1);

		jt = new JTextField();
		jt.setBounds(120, 47, 180, 21);
		add(jt);
		jt.setColumns(10);

		JButton jb = new JButton("查询");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=jt.getText();
				if(text.equals("")) {
					getAllMeetings();
				}
				else {
					findMeeting(text);
				}
			}
		});
		jb.setBounds(317, 46, 97, 23);
		add(jb);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 91, 392, 274);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	//	查询会议信息
	public void findMeeting(String name) {
		String sql="select * from room where name=?";
		ResultSet set;
		try {
			set = DataUtils.query(sql, new Object[] {name});
			Object[][] objects=DataUtils.getSetArrays(set);
			for (Object[] objects2 : objects) {
				System.out.println(Arrays.toString(objects2));
			}
			TableModel model=new DefaultTableModel(objects,heads);
			table.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//	所有会议的列表信息
	public void getAllMeetings() {
		String sql="select * from room;";
		ResultSet set;
		try {
			set = DataUtils.query(sql, new Object[] {});
			Object[][] objects=DataUtils.getSetArrays(set);
			TableModel model=new DefaultTableModel(objects,heads);
			table.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
