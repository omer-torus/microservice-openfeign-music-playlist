package com.music.playlist_service.controller;

import com.music.playlist_service.dto.PlaylistResponseDTO;
import com.music.playlist_service.model.Playlist;
import com.music.playlist_service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    // 🔥 Sihrin gerçekleştiği yer! (Feign Client çalışacak)
    @PostMapping("/generate/{userId}")
    public ResponseEntity<PlaylistResponseDTO> generateAutoPlaylist(@PathVariable Long userId) {
        return ResponseEntity.ok(playlistService.generateAutoPlaylist(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Playlist>> getUserPlaylists(@PathVariable Long userId) {
        return ResponseEntity.ok(playlistService.getUserPlaylists(userId));
    }
}
