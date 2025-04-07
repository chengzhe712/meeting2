package com.lys.meeting.view;

import javax.swing.*;

import com.lys.meeting.view.admin.MeetingTableApp;
import com.lys.meeting.view.common.MeetingForm;
import com.lys.meeting.view.common.welcome;
import com.lys.meeting.view.user.CancelTheReservation;
import com.lys.meeting.view.user.Personal;
import com.lys.meeting.view.user.QueryMeeting;
import com.lys.meeting.view.user.Reserve;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserWin extends JPanel {
	CardLayout cardLayout=new CardLayout(0, 0);

	String account;
	QueryMeeting queryMeeting=new QueryMeeting();
	JPanel panel = new JPanel();
	welcome welcome=new welcome();
	CardLayout c1;
	JPanel j1;
	/**
	 * Create the panel.
	 */
	public UserWin(CardLayout c1,JPanel j1,String account,int id) {
		setLayout(null);
		this.account=account;
		this.c1=c1;
		this.j1=j1;
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personal personal=new Personal(account);
				panel.add(personal,"personal");
				cardLayout.show(panel, "personal");
			}
		});
		btnNewButton.setBounds(29, 30, 97, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2\u4F1A\u8BAE\u5BA4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryMeeting.getAllMeetings();
				cardLayout.show(panel, "queryMeeting");
			}
		});
		btnNewButton_1.setBounds(29, 90, 97, 23);
		add(btnNewButton_1);




		JButton record = new JButton("会议记录");
		record.setBounds(29, 150, 97, 23);
		add(record);

		record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否记录会议？", "确认", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.out.println("进入记录");
					MeetingForm form = new MeetingForm();
					form.setVisible(true);
				} else if (result == JOptionPane.NO_OPTION) {

				} else {

				}
			}
		});


		JButton btnNewButton_2 = new JButton("\u9884\u7EA6");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserve reserve=new Reserve(id);
				panel.add(reserve,"reserve");
				cardLayout.show(panel, "reserve");
			}
		});
		btnNewButton_2.setBounds(29, 210, 97, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u53D6\u6D88\u9884\u7EA6");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelTheReservation cancelTheReservation=new CancelTheReservation(id);
				panel.add(cancelTheReservation,"cancel");
				cardLayout.show(panel, "cancel");
			}
		});
		btnNewButton_3.setBounds(29, 270, 97, 23);
		add(btnNewButton_3);


		panel.setBounds(136, 23, 435, 413);
		add(panel);
		panel.setLayout(cardLayout);
		panel.add(welcome,"welcome");

		panel.add(queryMeeting,"queryMeeting");



		JButton btnNewButton_4 = new JButton("\u9000\u51FA");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(j1, "login");
			}
		});
		btnNewButton_4.setBounds(29, 330, 97, 23);
		add(btnNewButton_4);
		cardLayout.show(panel, "welcome");

	}
}
