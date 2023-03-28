package com.techreturners.music4WeatherAPI.view;

import com.techreturners.music4WeatherAPI.controller.Music4WeatherController;
import com.techreturners.music4WeatherAPI.controller.WeatherController;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
import com.techreturners.music4WeatherAPI.service.Music4WeatherService;
import javazoom.jl.player.Player;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class Music4WeatherView extends JFrame {

    @Autowired
    private Music4WeatherController music4WeatherController;
    @Autowired
    private WeatherController weatherController;
    @Autowired
    private Music4WeatherService music4WeatherService;

    private Player mp3Player;

    private UI ui;

    public Music4WeatherView() {

        this.ui = new UI();

        ui.setVisible(true);
        ui.setResizable(false);

        ui.cityButton.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {

                ResponseEntity<Track> trackQuery = music4WeatherController.getTrackFromCity(ui.cityBox.getText());

                ResponseEntity<Weather> weatherQuery = weatherController.getWeatherDetails(ui.cityBox.getText());
//                if (trackQuery.getStatusCode() == 400)

                initUI(trackQuery, weatherQuery);

            }
        });

        ui.locationButton.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {

                ResponseEntity<Track> trackQuery = music4WeatherController.getTrackFromLocation(null);

                ResponseEntity<Weather> weatherQuery = weatherController.getWeatherDetails(music4WeatherService.getPublicIPAddress());
//                if (trackQuery.getStatusCode() == 400)

                initUI(trackQuery, weatherQuery);

            }
        });

    }

    private void initUI(ResponseEntity<Track> trackQuery, ResponseEntity<Weather> weatherQuery) throws IOException {

        ui.trackLabel.setText(trackQuery.getBody().getTitle());

        URL weatherIconURL = new URL("https:" + weatherQuery.getBody().getCurrent().getCondition().getIcon());
        BufferedImage weatherImage = ImageIO.read(weatherIconURL);
        ImageIcon weatherIcon = new ImageIcon(weatherImage);

        URL artistURL = new URL(trackQuery.getBody().getArtist().getPicture());
        BufferedImage artistImage = ImageIO.read(artistURL);
        ImageIcon artistIcon = new ImageIcon(artistImage);

        URL albumURL = new URL(trackQuery.getBody().getAlbum().getCover_small());
        BufferedImage albumImage = ImageIO.read(albumURL);
        ImageIcon albumIcon = new ImageIcon(albumImage);

        ui.location.setText(weatherQuery.getBody().getLocation().getName() + ", " + weatherQuery.getBody().getLocation().getCountry());
        ui.weatherIcon.setIcon(weatherIcon);
        ui.temperature.setText(weatherQuery.getBody().getCurrent().getTemp_c() + "°c");
        ui.otherWeatherLabel.setText(
                "<html>Precipitation: " + weatherQuery.getBody().getCurrent().getPrecip_mm() +
                        "mm<br/>Humidity: " + weatherQuery.getBody().getCurrent().getHumidity() +
                        "%<br/>Wind: " + weatherQuery.getBody().getCurrent().getWind_mph() + "mph</html>");

        ui.artistLabel.setText("Artist: " + trackQuery.getBody().getArtist().getName());
        ui.artistIcon.setIcon(artistIcon);
        ui.albumLabel.setText("Album: " + trackQuery.getBody().getAlbum().getTitle());
        ui.albumIcon.setIcon(albumIcon);

        new Thread(() -> {
            try { playSample(trackQuery.getBody().getPreview()); } catch (Exception exception) { exception.printStackTrace(); }
        }).start();

    }

    private void playSample(String urlString) throws Exception {

        URLConnection conn = new URL(urlString).openConnection();
        InputStream is = conn.getInputStream();

        if (mp3Player != null) mp3Player.close();

        mp3Player = new Player(is);
        mp3Player.play();

    }

}

