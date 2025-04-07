package com.lys.meeting.view.admin;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.lys.meeting.utils.DataUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Addup extends JPanel {
	JLabel jl1,jl2,jl3,jl4;

	/**
	 * Create the panel.
	 */
	public Addup() {
		setBorder(new TitledBorder(null, "数据统计", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\u603B\u7528\u6237\u6570");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(163, 57, 81, 28);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u53EF\u7528\u4F1A\u8BAE\u5BA4\u6570\u91CF");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(122, 114, 107, 28);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u9884\u7EA6\u6570\u91CF");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(167, 176, 86, 28);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u5F85\u5BA1\u6838\u6570\u91CF");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(152, 237, 96, 28);
		add(lblNewLabel_3);

		jl1 = new JLabel("New label");
		jl1.setForeground(Color.RED);
		jl1.setFont(new Font("宋体", Font.BOLD, 23));
		jl1.setBounds(239, 54, 107, 28);
		add(jl1);

		jl2 = new JLabel("New label");
		jl2.setForeground(Color.RED);
		jl2.setFont(new Font("宋体", Font.BOLD, 23));
		jl2.setBounds(239, 114, 96, 28);
		add(jl2);

		jl3 = new JLabel("New label");
		jl3.setForeground(Color.RED);
		jl3.setFont(new Font("宋体", Font.BOLD, 23));
		jl3.setBounds(239, 176, 96, 28);
		add(jl3);

		jl4 = new JLabel("New label");
		jl4.setForeground(Color.RED);
		jl4.setFont(new Font("宋体", Font.BOLD, 23));
		jl4.setBounds(239, 237, 96, 28);
		add(jl4);
	}

	public void getData() {
		String sql1="SELECT count(*) FROM `user`";
		String sql2="SELECT count(*) FROM room";
		String sql3="SELECT count(*) FROM `order`";
		String sql4="SELECT count(*) from `order` WHERE `order`.`status`='审核中'";
		jl1.setText(DataUtils.getCount(sql1));
		jl2.setText(DataUtils.getCount(sql2));
		jl3.setText(DataUtils.getCount(sql3));
		jl4.setText(DataUtils.getCount(sql4));
	}

}
