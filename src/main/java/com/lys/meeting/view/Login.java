package com.lys.meeting.view;

import javax.swing.JPanel;

import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.lys.meeting.dao.AdminDao;
import com.lys.meeting.dao.UserDao;
import com.lys.meeting.model.Admin;
import com.lys.meeting.model.User;

import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Login extends JPanel {
	CardLayout cardLayout;

	JPanel contentPane;
	private JTextField jt1;
	private JPasswordField jt2;
	UserDao userDao=new UserDao();
	AdminDao adminDao=new AdminDao();



	/**
	 * Create the panel.
	 */
	public Login(CardLayout cardLayout,JPanel contentPane) {
		setLayout(null);
		this.cardLayout=cardLayout;
		this.contentPane=contentPane;
		JButton btnNewButton = new JButton("重置");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));

		btnNewButton.setBounds(112, 376, 97, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));

		btnNewButton_1.setBounds(390, 376, 97, 23);
		add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setIcon(null);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(92, 76, 92, 94);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(87, 181, 97, 29);
		add(lblNewLabel_1);

		jt1 = new JTextField();
		jt1.setFont(new Font("宋体", Font.PLAIN, 20));
		jt1.setBounds(146, 107, 353, 32);
		add(jt1);
		jt1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u89D2\u8272");
		lblNewLabel_2.setIcon(null);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(74, 267, 92, 48);
		add(lblNewLabel_2);

//		按钮组
		ButtonGroup group=new ButtonGroup();

		JRadioButton rb1 = new JRadioButton("\u666E\u901A\u7528\u6237",true);
		rb1.setFont(new Font("宋体", Font.PLAIN, 20));
		rb1.setBounds(146, 280, 127, 23);
		group.add(rb1);
		add(rb1);

		JRadioButton rb2 = new JRadioButton("\u7BA1\u7406\u5458");
		rb2.setFont(new Font("宋体", Font.PLAIN, 20));
		rb2.setBounds(303, 280, 127, 23);
		group.add(rb2);
		add(rb2);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rb1);
		buttonGroup.add(rb2);

		JLabel lblNewLabel_3 = new JLabel("\u4F1A\u8BAE\u5BA4\u9884\u5B9A\u5C0F\u7CFB\u7EDF");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(197, 36, 372, 32);
		add(lblNewLabel_3);

		jt2 = new JPasswordField();
		jt2.setFont(new Font("宋体", Font.PLAIN, 15));
		jt2.setBounds(146, 181, 353, 32);
		add(jt2);

		JLabel jl = new JLabel("");
		jl.setForeground(Color.RED);
		jl.setFont(new Font("宋体", Font.PLAIN, 16));
		jl.setBounds(146, 223, 341, 34);
		add(jl);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText("");
				jt2.setText("");
				jl.setText("");
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account=jt1.getText();
				char pwd[]=jt2.getPassword();
				if (account.equals("")||pwd.equals("")) {
					jl.setText("请输入账号/密码");
				}
				if (rb1.isSelected()) {
					User user=userDao.selectOne(account);
					if (user==null||!String.valueOf(pwd).equals(user.getPassword())) {
						jl.setText("账号或者密码错误！请重新输入");
					}else {
						UserWin userWin=new UserWin(cardLayout,contentPane,account,user.getId());
						contentPane.add(userWin,"userWin");
						cardLayout.show(contentPane, "userWin");
					}
				}
				if (rb2.isSelected()) {
					Admin admin=adminDao.selectOne(account);
					if (admin==null||!String.valueOf(pwd).equals(admin.getPassword())) {
						jl.setText("账号或者密码错误！请重新输入");
					}
					else {
						AdminWin adminWin=new AdminWin(cardLayout,contentPane,admin.getId());
						contentPane.add(adminWin,"adminWin");
						cardLayout.show(contentPane, "adminWin");
					}
				}
			}
		});

	}
}
