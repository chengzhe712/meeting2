package com.lys.meeting.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;

public class Win extends JFrame {

	private JPanel contentPane=new JPanel();
	private CardLayout cardLayout=new CardLayout();
	private Login login=new Login(cardLayout,contentPane);




	/**
	 * Create the frame.
	 */
	public Win() {
		setTitle("\u4F1A\u8BAE\u9884\u7EA6");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse_workplace\\Meetting\\src\\com\\lys\\meeting\\icon\\\u4F1A\u8BAE.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 469);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		contentPane.add(login, "login");
		cardLayout.show(contentPane, "login");

	}

	/**
	 * √Ê∞Â«–ªª
	 */
	public void changePanel(Container parent,String name) {
		cardLayout.show(parent, name);
	}

}
