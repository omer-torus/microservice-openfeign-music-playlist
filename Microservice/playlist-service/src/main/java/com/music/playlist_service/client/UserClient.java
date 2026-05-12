package com.music.playlist_service.client;

import com.music.playlist_service.client.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.music.playlist_service.config.FeignConfig;

// url = "http://user-service:8081" (Network name inside Docker)
@FeignClient(name = "user-service", url = "http://user-service:8081", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @GetMapping("/users/{id}/favorites")
    List<String> getFavoriteGenres(@PathVariable("id") Long id);
}
