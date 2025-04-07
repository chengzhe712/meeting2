package com.lys.meeting.view.admin;

import com.lys.meeting.utils.DataUtils;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetingTableApp extends JFrame {
    private JTable table;

    String sql = "select * from record";

    String[] columnName = {"���","����","��ʼʱ��", "����ʱ��", "����������", "��ַ", "����", "������", "����", "��ѵ����", "��¼��"};
    private JTextField startTimeField, endTimeField, roomNameField, addressField, attendeeField, speakerField,
            topicField, trainingContentField, recorderField;

    private int selected = 0;
    private String sql_del="delete from record where id = ? ";

    String sql_add = "insert into record(id,start_time,end_time,room_name,address,attendees,speaker,topic,training_content,recorder) values(0,?,?,?,?,?,?,?,?,?)";
    private String sql_update = "update record set start_time=?,end_time=?,room_name=?,address=?,attendees=?, speaker=?,topic=?,training_content=?,recorder=? where id = ? ";

    public MeetingTableApp() {
        setTitle("������Ϣ����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 400);
        setLayout(new BorderLayout());

//        ��ȡ��ʼ������
        try {
            ResultSet set = DataUtils.query(sql, new Object[]{});

            table = new JTable(DataUtils.getSetArrays(set), columnName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel startTimeLabel = new JLabel("��ʼʱ��:");
        startTimeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(startTimeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        formPanel.add(startTimeField, constraints);

        JLabel endTimeLabel = new JLabel("����ʱ��:");
        endTimeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(endTimeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        formPanel.add(endTimeField, constraints);

        JLabel roomNameLabel = new JLabel("����������:");
        roomNameField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(roomNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        formPanel.add(roomNameField, constraints);

        JLabel addressLabel = new JLabel("��ַ:");
        addressField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 3;
        formPanel.add(addressLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        formPanel.add(addressField, constraints);

        JLabel attendeeLabel = new JLabel("����:");
        attendeeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 4;
        formPanel.add(attendeeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        formPanel.add(attendeeField, constraints);

        JLabel speakerLabel = new JLabel("������:");
        speakerField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 5;
        formPanel.add(speakerLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        formPanel.add(speakerField, constraints);

        JLabel topicLabel = new JLabel("����:");
        topicField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 6;
        formPanel.add(topicLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        formPanel.add(topicField, constraints);

        JLabel trainingContentLabel = new JLabel("��ѵ����:");
        trainingContentField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 7;
        formPanel.add(trainingContentLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        formPanel.add(trainingContentField, constraints);

        JLabel recorderLabel = new JLabel("��¼��:");
        recorderField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 8;
        formPanel.add(recorderLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 8;
        formPanel.add(recorderField, constraints);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("���");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMeeting();
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("����");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMeeting();
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("ɾ��");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMeeting();
            }
        });
        buttonPanel.add(deleteButton);

        JButton resetButton = new JButton("����");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimeField.setText("");
                endTimeField.setText("");
                roomNameField.setText("");
                addressField.setText("");
                attendeeField.setText("");
                speakerField.setText("");
                topicField.setText("");
                trainingContentField.setText("");
                recorderField.setText("");
            }
        });
        buttonPanel.add(resetButton);

        JButton exportButton = new JButton("��������");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportTableData();
            }
        });
        buttonPanel.add(exportButton);

        JButton closeButton = new JButton("�ر�");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(closeButton);

        add(formPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // ȷ���¼����ᱻ�ظ�����
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        // ��ȡѡ���е�����
                        selected = (int) table.getValueAt(row, 1);
                        startTimeField.setText((String) table.getValueAt(row, 2));
                        endTimeField.setText((String) table.getValueAt(row, 3));
                        roomNameField.setText((String) table.getValueAt(row, 4));
                        addressField.setText(String.valueOf(table.getValueAt(row, 5)));
                        attendeeField.setText(String.valueOf(table.getValueAt(row, 6)));
                        speakerField.setText((String) table.getValueAt(row, 7));
                        topicField.setText((String) table.getValueAt(row, 8));
                        trainingContentField.setText((String) table.getValueAt(row, 9));
                        recorderField.setText((String) table.getValueAt(row, 10));

                    }
                }
            }
        });
    }

    private void exportTableData() {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showSaveDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

                // д���ͷ
                for (int i = 0; i < table.getColumnCount(); i++) {
                    writer.write("---------------------------------------------------");
                    writer.write(table.getColumnName(i)+" | ");
                    writer.write("\t");
                }
                writer.newLine();

                // д��������
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Object value = table.getValueAt(row, col);
                        writer.write(value.toString());
                        writer.write("\t");
                    }
                    writer.newLine();
                }

                writer.close();
                JOptionPane.showMessageDialog(this, "�����ɹ���");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "����ʧ�ܣ�");
            }
        }
    }

    private void addMeeting() {
        if (startTimeField.getText().equals("") || endTimeField.getText().equals("") || roomNameField.getText().equals("") || addressField.getText().equals("")||attendeeField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "����д��Ҫ��Ϣ��");
        } else {
            try {
                DataUtils.Update(sql_add, new Object[]{
                        startTimeField.getText(), endTimeField.getText(), roomNameField.getText(), addressField.getText(), Integer.parseInt(attendeeField.getText()), speakerField.getText(),
                        topicField.getText(), trainingContentField.getText()
                        , recorderField.getText()
                });
                JOptionPane.showMessageDialog(this, "����OK��");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "����ʧ�ܣ�");
                throw new RuntimeException(e);
            }
            clearFields();
            reload();
        }
    }

    private void updateMeeting() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                DataUtils.Update(sql_update, new Object[]{
                        startTimeField.getText(), endTimeField.getText(), roomNameField.getText(), addressField.getText(), Integer.parseInt(attendeeField.getText()), speakerField.getText(),
                        topicField.getText(), trainingContentField.getText()
                        , recorderField.getText(), selected
                });
                JOptionPane.showMessageDialog(this, "����OK��");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "����ʧ�ܣ�");

                throw new RuntimeException(e);
            }

            clearFields();
            reload();
        } else {
            JOptionPane.showMessageDialog(this, "����ѡ��һ�����ݽ��и��£�");
        }
    }

    /**
     * ɾ������
     */
    private void deleteMeeting() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                DataUtils.Update(sql_del, new Object[]{selected});
                JOptionPane.showMessageDialog(this, "ɾ��OK��");
                reload();
                clearFields();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ�");
                throw new RuntimeException(e);
            }

        } else {
            JOptionPane.showMessageDialog(this, "����ѡ��һ�����ݽ���ɾ����");
        }
    }

    /**
     * ���¼���
     */
    void reload() {
        try {
            ResultSet set = DataUtils.query(sql, new Object[]{});
            table.setModel(new DefaultTableModel(DataUtils.getSetArrays(set), columnName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        startTimeField.setText("");
        endTimeField.setText("");
        roomNameField.setText("");
        addressField.setText("");
        attendeeField.setText("");
        speakerField.setText("");
        topicField.setText("");
        trainingContentField.setText("");
        recorderField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MeetingTableApp app = new MeetingTableApp();
                app.setVisible(true);
            }
        });
    }
}
