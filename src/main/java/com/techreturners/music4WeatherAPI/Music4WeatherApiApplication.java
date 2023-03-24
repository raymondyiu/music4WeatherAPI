package com.techreturners.music4WeatherAPI;

import com.techreturners.music4WeatherAPI.view.Music4WeatherView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;


//@SpringBootApplication
public class Music4WeatherApiApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Music4WeatherApiApplication.class, args);

		Music4WeatherView music4WeatherView = context.getBean(Music4WeatherView.class);

//		var ctx = new SpringApplicationBuilder(Music4WeatherView.class)
//				.headless(false).run(args);
//
//		EventQueue.invokeLater(() -> {
//
//			Music4WeatherApiApplication ex = ctx.getBean(Music4WeatherView.class);
//			ex.setVisible(true);
//
//		});

	}

}
