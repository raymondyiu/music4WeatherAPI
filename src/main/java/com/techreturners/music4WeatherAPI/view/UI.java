package com.techreturners.music4WeatherAPI.view;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI extends JFrame {

    JLabel intro;
    JLabel trackLabel, or, artistLabel, artistIcon, albumLabel, albumIcon, location, weatherIcon, temperature, otherWeatherLabel;
    JTextField cityBox;
    JButton cityButton, locationButton;
    Container container;

    @SneakyThrows
    public UI() {
        intro = new JLabel("Music4Weather");
        ClassPathResource resource = new ClassPathResource("agencyb.ttf");
        InputStream inputStream = resource.getInputStream();
        Font agencyBold = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(40f);
        intro.setFont(agencyBold);
        intro.setForeground(Color.LIGHT_GRAY);
        location = standardStylize(18f);
        weatherIcon = new JLabel("");
        temperature = standardStylize(25f);
        otherWeatherLabel = standardStylize(14f);
        or = standardStylize(18f);
        or.setText("or");
        cityBox = new JTextField();
        trackLabel = new JLabel("", SwingConstants.CENTER);
        ClassPathResource resource2 = new ClassPathResource("agencyr.ttf");
        InputStream inputStream2 = resource2.getInputStream();
        Font agencyBold2 = Font.createFont(Font.TRUETYPE_FONT, inputStream2).deriveFont(40f);
        trackLabel.setFont(agencyBold2);
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

    private JLabel standardStylize(float fontSize) throws IOException, FontFormatException {
        JLabel jLabel = new JLabel("");
        jLabel.setForeground(Color.LIGHT_GRAY);
        ClassPathResource resource = new ClassPathResource("bahnschrift.ttf");
        InputStream inputStream = resource.getInputStream();
        Font bahnschrift = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(fontSize);
        jLabel.setFont(bahnschrift);
        return jLabel;
    }

}