package com.techreturners.music4WeatherAPI.view;

import javax.swing.*;

import java.awt.*;

public class UI extends JFrame {

    JLabel intro;
    JLabel trackLabel, cityLabel, artistLabel, artistIcon, albumLabel, albumIcon, location, weatherIcon, temperature, otherWeatherLabel;
    JTextField cityBox;
    JButton button;
    Container container;

    public UI() {
        intro = new JLabel("Music4Weather"/*track.getTitle()*/);
        intro.setFont(new Font("Agency FB", Font.BOLD, 40));
        intro.setForeground(Color.LIGHT_GRAY);
        location = new JLabel("");
        location.setForeground(Color.LIGHT_GRAY);
        location.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        weatherIcon = new JLabel("");
        temperature = new JLabel("");
        temperature.setForeground(Color.LIGHT_GRAY);
        temperature.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
        otherWeatherLabel = new JLabel("");
        otherWeatherLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        otherWeatherLabel.setForeground(Color.LIGHT_GRAY);
        trackLabel = new JLabel("");
        cityLabel = new JLabel("Get track by city:");
        cityLabel.setForeground(Color.LIGHT_GRAY);
        cityLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        cityBox = new JTextField();
        artistLabel = new JLabel();
        artistIcon = new JLabel();
        albumLabel = new JLabel();
        albumIcon = new JLabel(":");
        button = new JButton("Get Track");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        this.setBounds(0, 0, 800, 520);
        this.container.setBackground(Color.DARK_GRAY);
    }

    public void setBounds() {
        intro.setBounds(85, 50, 600, 30);
        location.setBounds(340, 30, 600, 30);
        weatherIcon.setBounds(340, 55, 64, 64);
        temperature.setBounds(415, 63, 128, 64);
        otherWeatherLabel.setBounds(490, 20, 192, 128);
        trackLabel.setBounds(112, 80, 600, 30);
        cityLabel.setBounds(100, 130, 200, 30);
        cityBox.setBounds(260, 130, 250, 30);
        artistLabel.setBounds(120, 180, 100, 30);
        artistIcon.setBounds(120, 230, 300, 300);
        albumLabel.setBounds(120, 280, 100, 30);
        albumIcon.setBounds(150, 380, 112, 112);
        button.setBounds(165, 390, 250, 30);
    }

    public void addComponents() {
        container.add(intro);
        container.add(location);
        container.add(weatherIcon);
        container.add(temperature);
        container.add(otherWeatherLabel);
        container.add(trackLabel);
        container.add(cityLabel);
        container.add(cityBox);
        container.add(artistLabel);
        container.add(artistIcon);
        container.add(albumLabel);
        container.add(albumIcon);
        container.add(button);
    }

}