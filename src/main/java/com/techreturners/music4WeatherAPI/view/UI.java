package com.techreturners.music4WeatherAPI.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {

    JLabel intro;
    JLabel prompt, xLabel, zLabel, xLabel2, zLabel2, fLabel;
    JTextField xBox, zBox, xBox2, zBox2;
    JButton button;
    Container container;

    public UI() {
        intro = new JLabel("Track title"/*track.getTitle()*/);
        intro.setFont(new Font("Courier", Font.BOLD, 20));
        prompt = new JLabel(".");
        xLabel = new JLabel(":");
        xBox = new JTextField();
        zLabel = new JLabel(":");
        zBox = new JTextField();
        xLabel2 = new JLabel(":");
        xBox2 = new JTextField();
        zLabel2 = new JLabel(":");
        zBox2 = new JTextField();
        zLabel2 = new JLabel(":");
        zBox2 = new JTextField();
        button = new JButton("Get Track");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListener();
    }

    public void setBounds() {
        intro.setBounds(85, 10, 600, 30);
        prompt.setBounds(112, 60, 600, 30);
        xLabel.setBounds(120, 110, 100, 30);
        xBox.setBounds(200, 110, 250, 30);
        zLabel.setBounds(120, 160, 100, 30);
        zBox.setBounds(200, 160, 250, 30);
        xLabel2.setBounds(120, 210, 100, 30);
        xBox2.setBounds(200, 210, 250, 30);
        zLabel2.setBounds(120, 260, 100, 30);
        zBox2.setBounds(200, 260, 250, 30);
        button.setBounds(165, 370, 250, 30);
    }

    public void addComponents() {
        container.add(intro);
        container.add(prompt);
        container.add(xLabel);
        container.add(xBox);
        container.add(zLabel);
        container.add(zBox);
        container.add(xLabel2);
        container.add(xBox2);
        container.add(zLabel2);
        container.add(zBox2);
        container.add(button);
    }

    public void addActionListener() {
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            // TODO: This should be moved to the View Controller to fetch the track on button press

            this.dispose();
        }

    }

}