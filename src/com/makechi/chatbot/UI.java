package com.makechi.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UI extends JFrame {
    JTextArea ta1 = new JTextArea();
    JTextField ta2 = new JTextField();
    JButton send = new JButton("Send");

    String name;

    UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();

    boolean c_command = false;

    public UI() {
        super("Java Assistant");
        setSize(428, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        name = JOptionPane.showInputDialog(this, "Enter your name");

        ta1.setText("Hi " + name + ",\nWhat can I do for you?\n");
        ta1.setBounds(0, 0, 396, 550);
        ta1.setFont(new Font("Neucha", Font.PLAIN, 20));
        ta1.setEditable(false);
        ta1.setFocusable(false);
        ta1.setLineWrap(true);

        ta2.setBounds(2, 555, 334, 60);
        ta2.setFont(new Font("Neucha", Font.PLAIN, 18));
        ta2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    doTask(ta2.getText());
                }
            }
        });

        send.setBounds(336, 555, 70, 60);
        send.addActionListener(e -> doTask(ta2.getText()));

        add(ta1);
        add(ta2);
        add(send);

        try {
            UIManager.setLookAndFeel(looks[1].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void doTask(String str) {
        ta2.setText("");
        ta1.append(str + "\n");
        String c = str.toLowerCase();
        if ((c.contains("hello")) || (c.contains("hi"))) {
            greet();
            this.c_command = true;
        }

        if ((c.contains("date"))) {
            new DateAndTime().getDate();
            this.c_command = true;
        }

        if (c.contains("time")) {
            new DateAndTime().getTime();
            this.c_command = true;
        }

        if ((c.contains("calculate"))) {
            calculate();
            this.c_command = true;
        }

        if ((c.contains("story"))) {
            story();
            this.c_command = true;
        }

        if (!(this.c_command)) {
            ta1.append("Invalid Command");
        }
    }

    public void greet() {
        int choice = (int) (100 * Math.random());
        if (choice <= 40) {
            ta1.append("Hi " + name);
        } else if (choice <= 80) {
            ta1.append("Hello " + name);
        } else {
            ta1.append("What's up! " + name);
        }
    }

    public void story() {
        ta1.append("Coming soon");
    }

    public void calculate() {
        ta1.append("Currently under construction...");
        ta1.append("Sorry from the Assistant's developer.");
    }

    public static void main(String[] args) {
        new UI().setVisible(true);
    }

    private class DateAndTime {

        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd / MM / yyyy");
        DateTimeFormatter tme = DateTimeFormatter.ofPattern("HH : mm");
        String date = dateObj.format(fmt);
        String time = dateObj.format(tme);

        public void getDate() {
            ta1.append("The date today is: " + date); }

        public void getTime() {
            ta1.append("The time now is: " + time); }

    }
}
