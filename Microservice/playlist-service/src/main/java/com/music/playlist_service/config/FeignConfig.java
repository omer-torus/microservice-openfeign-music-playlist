package com.music.playlist_service.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        // FULL: İstek ve cevapların header, body ve metadata bilgilerini detaylıca
        // loglar.
        return Logger.Level.FULL;
    }
}
