package com.music.playlist_service.client;

import com.music.playlist_service.client.dto.SongDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.music.playlist_service.config.FeignConfig;

// url = "http://song-service:8082"
@FeignClient(name = "song-service", url = "http://song-service:8082", configuration = FeignConfig.class)
public interface SongClient {

    @GetMapping("/songs/genre/{genre}")
    List<SongDTO> getSongsByGenre(@PathVariable("genre") String genre);
}
