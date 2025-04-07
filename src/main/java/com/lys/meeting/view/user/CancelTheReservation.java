package com.lys.meeting.view.user;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.lys.meeting.utils.DataUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CancelTheReservation extends JPanel {
	private JTextField jt;

	/**
	 * Create the panel.
	 */
	public CancelTheReservation(int id) {
		setBorder(new TitledBorder(null, "ȡ��ԤԼ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u8981\u53D6\u6D88\u9884\u7EA6\u7684\u4F1A\u8BAEID\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel.setBounds(35, 100, 240, 21);
		add(lblNewLabel);

		jt = new JTextField();
		jt.setFont(new Font("����", Font.PLAIN, 16));
		jt.setBounds(35, 139, 268, 21);
		add(jt);
		jt.setColumns(10);

		JButton btnNewButton = new JButton("\u786E\u5B9A\u53D6\u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int roomId=Integer.parseInt(jt.getText());
//				��ѯ�û��Ƿ���Ԥ���������ļ�¼
				String sql="select * from `order` where user_id=? and room_id=?";
				try {
					if (DataUtils.query(sql, new Object[] {id,roomId})!=null) {
//						�м�¼
						String sql2="delete from `order` where user_id=? and room_id=?";
						if (DataUtils.Update(sql2, new Object[] {id,roomId})>0) {
//							ɾ���ɹ�
							JOptionPane.showMessageDialog(null, "ȡ��Ԥ���ɹ���");
							jt.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "ȡ��Ԥ��ʧ�ܣ�������");
						}
					}
					else {
//						�޼�¼
						JOptionPane.showMessageDialog(null, "��û��ԤԼ�û��飡���ѯ�����ԣ�");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 16));
		btnNewButton.setBounds(35, 186, 146, 21);
		add(btnNewButton);

	}

}
