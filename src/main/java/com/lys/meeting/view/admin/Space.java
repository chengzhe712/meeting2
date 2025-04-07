package com.lys.meeting.view.admin;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.lys.meeting.dao.AdminDao;
import com.lys.meeting.model.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Space extends JPanel {
	private JTextField jt1;
	private JTextField jt2;
	private JTextField jt3;
	private JTextField jt4;
	private JTextField jt5;
	AdminDao adminDao=new AdminDao();

	/**
	 * Create the panel.
	 */
	public Space(int id) {
		setBorder(new TitledBorder(null, "个人\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(106, 75, 86, 33);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1.setBounds(106, 118, 73, 31);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\u624B\u673A\u53F7");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(93, 159, 86, 34);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u90AE\u7BB1\u5730\u5740");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(76, 203, 86, 33);
		add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1_1_2.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(106, 246, 73, 33);
		add(lblNewLabel_1_1_2);

		jt1 = new JTextField();
		jt1.setEditable(false);
		jt1.setFont(new Font("宋体", Font.BOLD, 18));
		jt1.setBounds(170, 80, 219, 21);
		add(jt1);
		jt1.setColumns(10);

		jt2 = new JTextField();
		jt2.setEditable(false);
		jt2.setFont(new Font("宋体", Font.BOLD, 18));
		jt2.setColumns(10);
		jt2.setBounds(170, 122, 219, 21);
		add(jt2);

		jt3 = new JTextField();
		jt3.setEditable(false);
		jt3.setFont(new Font("宋体", Font.BOLD, 18));
		jt3.setColumns(10);
		jt3.setBounds(170, 165, 219, 21);
		add(jt3);

		jt4 = new JTextField();
		jt4.setEditable(false);
		jt4.setFont(new Font("宋体", Font.BOLD, 18));
		jt4.setColumns(10);
		jt4.setBounds(170, 208, 219, 21);
		add(jt4);

		jt5 = new JTextField();
		jt5.setEditable(false);
		jt5.setFont(new Font("宋体", Font.BOLD, 18));
		jt5.setColumns(10);
		jt5.setBounds(170, 251, 219, 21);
		add(jt5);

		JButton jb1 = new JButton("\u4FEE\u6539");

		jb1.setFont(new Font("宋体", Font.BOLD, 18));
		jb1.setBounds(82, 329, 97, 23);
		add(jb1);

		JButton jb2 = new JButton("\u4FDD\u5B58");
		jb2.setEnabled(false);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin admin=new Admin();
				admin.setId(id);
				admin.setName(jt2.getText());
				admin.setPhone(jt3.getText());
				admin.setEmail(jt4.getText());
				admin.setPassword(jt5.getText());
				if (adminDao.updateInfo(admin)==1) {
					JOptionPane.showMessageDialog(null, "信息更新成功！");
					jb1.setEnabled(true);
					jb2.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(null, "信息更新失败！");
				}
			}
		});
		jb2.setFont(new Font("宋体", Font.BOLD, 18));
		jb2.setBounds(292, 329, 97, 23);
		add(jb2);

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt2.setEditable(true);
				jt3.setEditable(true);
				jt4.setEditable(true);
				jt5.setEditable(true);
				jb1.setEnabled(false);
				jb2.setEnabled(true);
			}
		});
	}
	//	查询登录账户的全部信息
	public void getInfo(int id) {
		Admin admin=adminDao.selectOneById(id);
		if (admin!=null) {
			jt1.setText(admin.getAccount());
			jt2.setText(admin.getName());
			jt3.setText(admin.getPhone());
			jt4.setText(admin.getEmail());
			jt5.setText(admin.getPassword());
		}
	}
}
