package com.lys.meeting.view.user;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.lys.meeting.dao.UserDao;
import com.lys.meeting.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Personal extends JPanel {

	UserDao userDao=new UserDao();

	User currentUser=null;

	private JTextField jt1;
	private JTextField jt2;
	private JTextField jt3;
	private JTextField jt4;
	private JTextField jt5;
	private JTextField jt6;
	private JTextField jt7;

	//	加载用户基本信息
	public void getInfo(String account) {
		User user=userDao.selectOne(account);
		System.out.println(user);
		this.currentUser=user;
		jt1.setText(user.getAccount());
		jt2.setText(user.getRealName());
		jt3.setText(user.getApartment());
		jt4.setText(user.getPhone());
		jt5.setText(user.getEmail());
		jt6.setText(user.getDuty());
		jt7.setText(user.getPassword());
	}


	/**
	 * Create the panel.
	 */
	public Personal(String account) {
		setBorder(new TitledBorder(null, "个人\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);



		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(99, 36, 58, 15);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u771F\u5B9E\u59D3\u540D");
		lblNewLabel_1.setBounds(99, 76, 58, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u90E8\u95E8");
		lblNewLabel_2.setBounds(99, 114, 58, 15);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u624B\u673A\u53F7");
		lblNewLabel_3.setBounds(99, 150, 58, 15);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u90AE\u7BB1\u5730\u5740");
		lblNewLabel_4.setBounds(99, 188, 58, 15);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\u804C\u52A1");
		lblNewLabel_5.setBounds(99, 228, 58, 15);
		add(lblNewLabel_5);

		jt1 = new JTextField();
		jt1.setEditable(false);
		jt1.setBounds(167, 30, 250, 21);
		add(jt1);
		jt1.setColumns(10);

		jt2 = new JTextField();
		jt2.setEditable(false);
		jt2.setBounds(167, 70, 250, 21);
		add(jt2);
		jt2.setColumns(10);

		jt3 = new JTextField();
		jt3.setEditable(false);
		jt3.setBounds(167, 108, 250, 21);
		add(jt3);
		jt3.setColumns(10);

		jt4 = new JTextField();
		jt4.setEditable(false);
		jt4.setBounds(167, 144, 250, 21);
		add(jt4);
		jt4.setColumns(10);

		jt5 = new JTextField();
		jt5.setEditable(false);
		jt5.setBounds(167, 182, 250, 21);
		add(jt5);
		jt5.setColumns(10);

		jt6 = new JTextField();
		jt6.setEditable(false);
		jt6.setBounds(167, 222, 250, 21);
		add(jt6);
		jt6.setColumns(10);

		JButton jb1 = new JButton("\u4FEE\u6539");

		jb1.setBounds(81, 290, 97, 23);
		add(jb1);

		JButton jb2 = new JButton("\u4FDD\u5B58");

		jb2.setEnabled(false);
		jb2.setBounds(324, 290, 97, 23);
		add(jb2);

		JLabel lblNewLabel_5_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_5_1.setBounds(99, 265, 58, 15);
		add(lblNewLabel_5_1);

		jt7 = new JTextField();
		jt7.setEditable(false);
		jt7.setColumns(10);
		jt7.setBounds(167, 259, 250, 21);
		add(jt7);

		getInfo(account);

		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt2.setEditable(true);
				jt3.setEditable(true);
				jt4.setEditable(true);
				jt5.setEditable(true);
				jt6.setEditable(true);
				jt7.setEditable(true);
				jb2.setEnabled(true);
				jb1.setEnabled(false);
			}
		});

		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user=new User();
				user.setId(currentUser.getId());
				user.setRealName(jt2.getText());
				user.setApartment(jt3.getText());
				user.setPhone(jt4.getText());
				user.setEmail(jt5.getText());
				user.setDuty(jt6.getText());
				user.setPassword(jt7.getText());
				int result=userDao.update(user);
				if (result>0) {
					jt2.setEditable(false);
					jt3.setEditable(false);
					jt4.setEditable(false);
					jt5.setEditable(false);
					jt6.setEditable(false);
					jt7.setEditable(false);
					jb2.setEnabled(false);
					jb1.setEnabled(true);
					JOptionPane.showMessageDialog(null,"修改成功");
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "修改失败");
				}
			}
		});

	}
}
