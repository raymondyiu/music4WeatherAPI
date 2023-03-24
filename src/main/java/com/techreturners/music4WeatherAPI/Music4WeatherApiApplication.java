package com.techreturners.music4WeatherAPI;

import com.techreturners.music4WeatherAPI.view.Music4WeatherView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;


@SpringBootApplication
public class Music4WeatherApiApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = new SpringApplicationBuilder(Music4WeatherApiApplication.class)
				.headless(false).run(args);

		EventQueue.invokeLater(() -> context.getBean(Music4WeatherView.class));

	}

}
