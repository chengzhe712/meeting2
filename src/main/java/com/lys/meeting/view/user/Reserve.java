package com.lys.meeting.view.user;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lys.meeting.dao.RoomDao;
import com.lys.meeting.model.Room;
import com.lys.meeting.utils.DataUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
//Reserve��ʾһ����壬������ʾ�ʹ��������ԤԼ�Ľ��档
public class Reserve extends JPanel {

	private final JTextField jt;   //jt ��һ���ı���������������ҵ� ID��
	private final JTable table;   //table ��һ�����������ʾ�û���ԤԼ��Ϣ��
	RoomDao roomDao=new RoomDao();   //roomDao ��һ�����ݷ��ʶ����������������ص����ݿ������
	String[] header ={"���","����������","��ַ","��λ����","״̬"};  //header ���鶨���˱����б��⡣


	public Reserve(int id)  {
		//�������ı߿�Ͳ��֡�
		setBorder(new TitledBorder(null, "ԤԼ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		//������������ҵ�id������ǩ
		JLabel lblNewLabel = new JLabel("����������ҵ�ID��");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel.setBounds(28, 26, 233, 21);
		add(lblNewLabel);

		//�ı���-�������������id
		jt = new JTextField();
		jt.setBounds(28, 57, 241, 21);
		add(jt);
		jt.setColumns(10);
		
		JButton jb = new JButton("��ҪԤԼ");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���Ȼ�ȡ�û�����Ļ�����id
				int roomId=Integer.parseInt(jt.getText());
				//ͨ�� roomDao ��ѯ�����ң���������Ҳ����ڻ��ѱ�Ԥ����������Ӧ����ʾ��
				Room room=roomDao.search(roomId);
				if (room==null) {
					JOptionPane.showMessageDialog(null, "���޴˻����ң�");
				}else if (!room.getStatus().equals("����")) {
					JOptionPane.showMessageDialog(null, "�û������ѱ�Ԥ�����뻻һ����");
				}else {
					//��������ҿ��ã���ִ�� SQL ������䣬��ԤԼ��¼��ӵ����ݿ��У������±����ʾ��
					String sql="INSERT INTO `order` VALUES(0,?,?,'�����')";
					try {
						int i=DataUtils.Update(sql, new Object[] {id,roomId});
						if (i>0) {
							JOptionPane.showMessageDialog(null, "ԤԼ�ɹ�");
							showMine(id);
						}
						else {
							JOptionPane.showMessageDialog(null, "ԤԼʧ�ܣ�");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		jb.setFont(new Font("����", Font.PLAIN, 16));
		jb.setBounds(279, 56, 135, 21);
		add(jb);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 425, 150);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("�ҵ�ԤԼһ����");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 101, 233, 21);
		add(lblNewLabel_1);
		showMine(id);		//��ʾ�û���ԤԼ��Ϣ


	}

	//showMine ����ͨ�� SQL ��ѯ��ȡ�û���ԤԼ��Ϣ�����������ʾ�ڱ���С�
	public void showMine(int id) {
		String sql="SELECT\r\n"
				+ "	r.`name`,\r\n"
				+ "	r.address,\r\n"
				+ "	r.seat_num,\r\n"
				+ "	o.`status` \r\n"
				+ "FROM\r\n"
				+ "	`user` u\r\n"
				+ "	LEFT JOIN `order` o ON u.id = o.user_id\r\n"
				+ "	LEFT JOIN room r ON r.id = o.room_id \r\n"
				+ "WHERE\r\n"
				+ "	u.id ="+String.valueOf(id)+";";
		try {
			//ʹ�� DataUtils ��ķ���ִ�в�ѯ������������
			TableModel model=new DefaultTableModel(DataUtils.getSetArrays(DataUtils.query(sql, new Object[] {})),header);
			table.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
