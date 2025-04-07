package com.lys.meeting.view;

import javax.swing.*;

import com.lys.meeting.view.admin.Addup;
import com.lys.meeting.view.admin.MeetingTableApp;
import com.lys.meeting.view.admin.Space;
import com.lys.meeting.view.admin.Verify;
import com.lys.meeting.view.common.MeetingForm;
import com.lys.meeting.view.common.welcome;

import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

public class AdminWin extends JPanel {
	CardLayout c=new CardLayout();
	CardLayout cardLayout;

	JPanel contentPane;


	/**
	 * Create the panel.
	 */
	public AdminWin(CardLayout cardLayout,JPanel contentPane,int id) {
		setLayout(null);
		this.cardLayout=cardLayout;
		this.contentPane=contentPane;
//		首页
		welcome welcome=new welcome();

		JButton btnNewButton = new JButton("退出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentPane, "login");
			}
		});
		btnNewButton.setBounds(28, 330, 97, 23);
		add(btnNewButton);

		JButton jb1 = new JButton("\u6570\u636E\u7EDF\u8BA1");

		jb1.setBounds(28, 10, 97, 23);
		add(jb1);

		JButton jb4 = new JButton("会议室信息");

		jb4.setBounds(28, 60, 97, 23);
		add(jb4);

		JButton jb2 = new JButton("\u5BA1\u6838\u9884\u7EA6");

		jb2.setBounds(28, 120, 97, 23);
		add(jb2);

		JPanel panel = new JPanel();
		panel.setBounds(135, 10, 450, 435);
		add(panel);

		panel.setLayout(c);

		panel.add(welcome,"welcome");

		JButton jb3 = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Space space=new Space(id);
				panel.add(space,"space");
				space.getInfo(id);
				c.show(panel, "space");
			}
		});
		jb3.setBounds(28, 260, 97, 23);
		add(jb3);


		//		会议记录管理按钮
		JButton record_jb = new JButton("会纪管理");
		record_jb.setBounds(29, 190, 97, 23);
		add(record_jb);

		record_jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MeetingTableApp app = new MeetingTableApp();
				app.setVisible(true);
			}
		});



		c.show(panel, "welcome");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addup addup=new Addup();
				panel.add(addup,"addup");
				addup.getData();
				c.show(panel, "addup");
			}
		});
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verify verify=new Verify();
				panel.add(verify,"verify");
				c.show(panel, "verify");
			}
		});
	}
}
