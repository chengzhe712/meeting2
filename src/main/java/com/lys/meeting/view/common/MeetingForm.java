package com.lys.meeting.view.common;

import com.lys.meeting.utils.DataUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MeetingForm extends JFrame {

    private static  final MeetingForm instance=new MeetingForm();

    public static MeetingForm getInstance() {
        return instance;
    }
    private JTextField startTimeField, endTimeField, roomNameField, addressField, attendeeField, speakerField,
            topicField, trainingContentField, recorderField;

    public MeetingForm() {
        setTitle("�����¼");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

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
        JButton cancelButton = new JButton("ȡ��");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        JButton resetButton = new JButton("����");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        buttonPanel.add(resetButton);

        JButton saveButton = new JButton("����");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFormData();
            }
        });
        buttonPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void resetForm() {
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

    private void saveFormData() {
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String roomName = roomNameField.getText();
        String address = addressField.getText();
        String attendee = attendeeField.getText();
        String speaker = speakerField.getText();
        String topic = topicField.getText();
        String trainingContent = trainingContentField.getText();
        String recorder = recorderField.getText();

        // ������ִ�б������ݵ��߼�
        System.out.println("��ʼʱ�䣺" + startTime);
        System.out.println("����ʱ�䣺" + endTime);
        System.out.println("���������ƣ�" + roomName);
        System.out.println("��ַ��" + address);
        System.out.println("������" + attendee);
        System.out.println("�����ˣ�" + speaker);
        System.out.println("���⣺" + topic);
        System.out.println("��ѵ���ݣ�" + trainingContent);
        System.out.println("��¼�ˣ�" + recorder);

        String sql = "insert into record(id,start_time,end_time,room_name,address,attendees,speaker,topic,training_content,recorder) values(0,?,?,?,?,?,?,?,?,?)";

        try {
            int update = DataUtils.Update(sql, new Object[]{
                    startTime, endTime, roomName, address, attendee, speaker, topic, trainingContent, recorder
            });
            if (update > 0) {
                JOptionPane.showMessageDialog(this, "����ɹ���");
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "����ʧ�ܣ�");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
