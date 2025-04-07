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
        setTitle("会议记录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel startTimeLabel = new JLabel("开始时间:");
        startTimeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(startTimeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        formPanel.add(startTimeField, constraints);

        JLabel endTimeLabel = new JLabel("结束时间:");
        endTimeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(endTimeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        formPanel.add(endTimeField, constraints);

        JLabel roomNameLabel = new JLabel("会议室名称:");
        roomNameField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(roomNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        formPanel.add(roomNameField, constraints);

        JLabel addressLabel = new JLabel("地址:");
        addressField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 3;
        formPanel.add(addressLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        formPanel.add(addressField, constraints);

        JLabel attendeeLabel = new JLabel("人数:");
        attendeeField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 4;
        formPanel.add(attendeeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        formPanel.add(attendeeField, constraints);

        JLabel speakerLabel = new JLabel("主讲人:");
        speakerField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 5;
        formPanel.add(speakerLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        formPanel.add(speakerField, constraints);

        JLabel topicLabel = new JLabel("主题:");
        topicField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 6;
        formPanel.add(topicLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        formPanel.add(topicField, constraints);

        JLabel trainingContentLabel = new JLabel("培训内容:");
        trainingContentField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 7;
        formPanel.add(trainingContentLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        formPanel.add(trainingContentField, constraints);

        JLabel recorderLabel = new JLabel("记录人:");
        recorderField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 8;
        formPanel.add(recorderLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 8;
        formPanel.add(recorderField, constraints);

        JPanel buttonPanel = new JPanel();
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        JButton resetButton = new JButton("重置");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        buttonPanel.add(resetButton);

        JButton saveButton = new JButton("保存");
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

        // 在这里执行保存数据的逻辑
        System.out.println("开始时间：" + startTime);
        System.out.println("结束时间：" + endTime);
        System.out.println("会议室名称：" + roomName);
        System.out.println("地址：" + address);
        System.out.println("人数：" + attendee);
        System.out.println("主讲人：" + speaker);
        System.out.println("主题：" + topic);
        System.out.println("培训内容：" + trainingContent);
        System.out.println("记录人：" + recorder);

        String sql = "insert into record(id,start_time,end_time,room_name,address,attendees,speaker,topic,training_content,recorder) values(0,?,?,?,?,?,?,?,?,?)";

        try {
            int update = DataUtils.Update(sql, new Object[]{
                    startTime, endTime, roomName, address, attendee, speaker, topic, trainingContent, recorder
            });
            if (update > 0) {
                JOptionPane.showMessageDialog(this, "保存成功！");
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "保存失败！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
