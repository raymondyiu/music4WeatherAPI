package com.techreturners.music4WeatherAPI.view;

import javax.swing.*;

import java.awt.*;

public class UI extends JFrame {

    JLabel intro;
    JLabel trackLabel, or, artistLabel, artistIcon, albumLabel, albumIcon, location, weatherIcon, temperature, otherWeatherLabel;
    JTextField cityBox;
    JButton cityButton, locationButton;
    Container container;

    public UI() {
        intro = new JLabel("Music4Weather"/*track.getTitle()*/);
        intro.setFont(new Font("Agency FB", Font.BOLD, 40));
        intro.setForeground(Color.LIGHT_GRAY);
        location = standardStylize(18);
        weatherIcon = new JLabel("");
        temperature = standardStylize(25);
        otherWeatherLabel = standardStylize(14);
        or = standardStylize(18);
        or.setText("or");
        cityBox = new JTextField();
        trackLabel = new JLabel("", SwingConstants.CENTER);
        trackLabel.setFont(new Font("Agency FB", Font.PLAIN, 35));
        trackLabel.setForeground(Color.LIGHT_GRAY);
        artistLabel = standardStylize(18);
        albumLabel = standardStylize(18);
        artistIcon = new JLabel("", SwingConstants.CENTER);
        albumIcon = new JLabel("", SwingConstants.CENTER);
        cityButton = new JButton("Get Track from City");
        locationButton = new JButton("Get Track from Current Location");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        this.setBounds(0, 0, 800, 520);
        this.container.setBackground(Color.DARK_GRAY);
    }

    public void setBounds() {
        intro.setBounds(80, 45, 600, 30);
        location.setBounds(345, 20, 600, 30);
        weatherIcon.setBounds(340, 45, 64, 64);
        temperature.setBounds(415, 49, 128, 64);
        otherWeatherLabel.setBounds(500, 12, 192, 128);
        or.setBounds(402, 132, 200, 30);
        cityBox.setBounds(200, 130, 175, 30);
        trackLabel.setBounds(0, 180, 750, 50);
        artistLabel.setBounds(50, 115, 350, 300);
        artistIcon.setBounds(0, 205, 300, 300);
        albumLabel.setBounds(400, 115, 350, 300);
        albumIcon.setBounds(450, 290, 112, 112);
        cityButton.setBounds(50, 130, 150, 30);
        locationButton.setBounds(445, 130, 250, 30);
    }

    public void addComponents() {
        container.add(intro);
        container.add(location);
        container.add(weatherIcon);
        container.add(temperature);
        container.add(otherWeatherLabel);
        container.add(trackLabel);
        container.add(or);
        container.add(cityBox);
        container.add(artistLabel);
        container.add(artistIcon);
        container.add(albumLabel);
        container.add(albumIcon);
        container.add(cityButton);
        container.add(locationButton);
    }

    private JLabel standardStylize(int fontSize) {
        JLabel jLabel = new JLabel("");
        jLabel.setForeground(Color.LIGHT_GRAY);
        jLabel.setFont(new Font("Bahnschrift", Font.PLAIN, fontSize));
        return jLabel;
    }

}