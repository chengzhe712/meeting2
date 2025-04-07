package com.lys.meeting.view.admin;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.lys.meeting.utils.DataUtils;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Verify extends JPanel {
	private JTable table;
	String[] header= {"序号","预约ID","账号","姓名","会议室id","会议室名称","地址","审核状态"};
	Object[][] tableData1=new Object[][] {};
	Object[][] tableData2=new Object[][] {};
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public Verify() {
		setBorder(new TitledBorder(null, "预约审核", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 24, 456, 340);
		add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("已审核", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 47, 408, 256);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(tableData1,header));

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("待审核", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u9009\u4E2D\u8868\u683C\u7684\u884C\uFF0C\u7136\u540E\u70B9\u51FB\u6309\u94AE\u5BA1\u9605");
		lblNewLabel.setBounds(10, 261, 198, 27);
		panel_1.add(lblNewLabel);

		JButton jb1 = new JButton("\u5BA1\u6838\u901A\u8FC7");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="UPDATE `order` SET `status`='通过' WHERE id=?";


				int selectedRow=table_1.getSelectedRow();//获得选中行的索引
				if (selectedRow!=-1) {
					int orderId=(int) table_1.getValueAt(selectedRow, 1);
//					获取会议室的id
					int meetingId=(int) table_1.getValueAt(selectedRow, 4);
					try {
						int res=DataUtils.Update(sql, new Object[] {orderId});
//						更新会议室的状态
						String sql2="UPDATE room set `status`='已预定' WHERE id=?";
						int res2=DataUtils.Update(sql2, new Object[] {meetingId});
						if(res>0) {
							JOptionPane.showMessageDialog(null,"成功审核一条预约申请！");
							getTableData2();
							getTableData1();
						}else {
							JOptionPane.showMessageDialog(null,"操作失败！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		jb1.setBounds(220, 263, 97, 23);
		panel_1.add(jb1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 416, 239);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 416, 239);
		panel_2.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JButton jb2 = new JButton("\u4E0D\u7ED9\u901A\u8FC7");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				审核不通过
				String sql="UPDATE `order` SET `status`='未通过' WHERE id=?";
				int selectedRow=table_1.getSelectedRow();//获得选中行的索引
				if (selectedRow!=-1) {
					int MeetingId=(int) table_1.getValueAt(selectedRow, 1);
					try {
						int res=DataUtils.Update(sql, new Object[] {MeetingId});
						if(res>0) {
							JOptionPane.showMessageDialog(null,"成功审核一条预约申请！");
							getTableData2();
							getTableData1();
						}else {
							JOptionPane.showMessageDialog(null,"操作失败！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		jb2.setBounds(327, 263, 97, 23);
		panel_1.add(jb2);
		getTableData1();
		getTableData2();

	}

	public void getTableData1() {
		String sql="SELECT o.id, a.account,a.real_name,r.id,r.`name`,r.address,o.`status`\r\n"
				+ "FROM `user` a,room r,`order` o\r\n"
				+ "WHERE o.user_id=a.id AND o.room_id=r.id HAVING  o.status='未通过' OR `status`='通过';";
		try {
			table.setModel(new DefaultTableModel(DataUtils.getSetArrays(DataUtils.query(sql, new Object[] {})),header));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getTableData2() {
		String sql="SELECT o.id, a.account,a.real_name,r.id,r.`name`,r.address,o.`status`\r\n"
				+ "FROM `user` a,room r,`order` o\r\n"
				+ "WHERE o.user_id=a.id AND o.room_id=r.id AND o.status=\'审核中\';";
		try {
			table_1.setModel(new DefaultTableModel(DataUtils.getSetArrays(DataUtils.query(sql, new Object[] {})),header));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
