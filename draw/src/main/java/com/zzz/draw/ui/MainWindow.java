package com.zzz.draw.ui;

import com.zzz.draw.client.send.MessageSendMessage;
import com.zzz.draw.client.send.ReadySendMessage;
import com.zzz.draw.server.Application;

import javax.swing.*;

/**
 * Created by zha on 2018/4/16.
 */
public class MainWindow extends JFrame {

    private DrawPanel drawPanel;

    private JButton rect;
    private JButton line;
    private JTextArea messageBox;

    private JTextField message;
    private JButton send;
    private JButton ready;

    public MainWindow() {
        line = new JButton("曲线");
        rect = new JButton("矩形");
        send = new JButton("发送");
        ready = new JButton("准备");
        messageBox = new JTextArea(30, 30);
        message = new JTextField();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        drawPanel = new DrawPanel();
        drawPanel.setLocation(0, 0);
        this.add(drawPanel);
        line.setBounds(610, 30, 60, 30);
        rect.setBounds(680, 30, 60, 30);
        ready.setBounds(750, 30, 60, 30);
        this.add(rect);
        this.add(ready);
        this.add(line);
        message.setBounds(610, 500, 180, 30);
        send.setBounds(800, 500, 60, 30);
        messageBox.setBounds(610, 63, 250, 400);
        messageBox.setLineWrap(true);        //激活自动换行功能
        messageBox.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane scrollPane_1 = new JScrollPane(messageBox);
        scrollPane_1.setBounds(610, 63, 250, 400);
        this.add(scrollPane_1);
        this.add(message);
        this.add(send);
        initEvent();
        this.setVisible(true);
        Application.putBean(this);
    }

    private void initEvent() {
        rect.addActionListener(e -> drawPanel.changeRect());
        line.addActionListener(e -> drawPanel.changeLine());
        send.addActionListener(e -> Application.getBean(MessageSendMessage.class).sendMessage(message.getText()));
        ready.addActionListener(e -> {
            String text = ready.getText();
            int code = 2;
            if (text.equals("准备")) {
                code = 1;
            }
            Application.getBean(ReadySendMessage.class).sendMessage(code);
        });
    }

    public void appendText(String name, String message) {
        messageBox.append(name + ":" + message + "\r\n");

    }

    public void ready() {
        String text = ready.getText();
        if (text.equals("准备")) {
            ready.setText("取消准备");
        } else {
            ready.setText("准备");
        }
    }
}
