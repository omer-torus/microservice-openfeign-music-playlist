package com.music.playlist_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //  MOST IMPORTANT PART! Enabling Feign.
public class PlaylistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistServiceApplication.class, args);
	}

}
