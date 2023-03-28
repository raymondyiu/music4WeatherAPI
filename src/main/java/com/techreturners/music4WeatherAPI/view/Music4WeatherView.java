package com.techreturners.music4WeatherAPI.view;

import com.techreturners.music4WeatherAPI.controller.Music4WeatherController;
import com.techreturners.music4WeatherAPI.controller.WeatherController;
import com.techreturners.music4WeatherAPI.model.Track;
import com.techreturners.music4WeatherAPI.model.Weather;
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
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Component
public class Music4WeatherView extends JFrame {

    @Autowired
    private Music4WeatherController music4WeatherController;
    @Autowired
    private WeatherController weatherController;

    private Player mp3Player;

    public Music4WeatherView() {

        initUI();

    }

    private void initUI() {

        UI ui = new UI();

        ui.cityButton.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                ResponseEntity<Track> trackQuery = music4WeatherController.getTrack(ui.cityBox.getText());

                ResponseEntity<Weather> weatherQuery = weatherController.getWeatherDetails(ui.cityBox.getText());
//                if (trackQuery.getStatusCode() == 400)

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
        });

        ui.setVisible(true);

    }

    private void playSample(String urlString) throws Exception {

        URLConnection conn = new URL(urlString).openConnection();
        InputStream is = conn.getInputStream();

        if (mp3Player != null) mp3Player.close();

        mp3Player = new Player(is);
        mp3Player.play();

    }
}

