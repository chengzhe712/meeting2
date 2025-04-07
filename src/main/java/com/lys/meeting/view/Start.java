package com.lys.meeting.view;

import java.awt.*;

public class Start {
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    com.formdev.flatlaf.intellijthemes.FlatNordIJTheme.setup();
                    Win frame = new Win();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
