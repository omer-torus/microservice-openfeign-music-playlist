package com.music.playlist_service.service;

import com.music.playlist_service.client.SongClient;
import com.music.playlist_service.client.UserClient;
import com.music.playlist_service.client.dto.SongDTO;
import com.music.playlist_service.client.dto.UserDTO;
import com.music.playlist_service.dto.PlaylistResponseDTO;
import com.music.playlist_service.model.Playlist;
import com.music.playlist_service.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    // Inject Feign Clients!
    private final UserClient userClient;
    private final SongClient songClient;

    public PlaylistResponseDTO generateAutoPlaylist(Long userId) {
        // 1. Get user from User Service via Feign
        UserDTO user = userClient.getUserById(userId);

        // 2. Get user's favorite genres from User Service via Feign
        List<String> favoriteGenres = userClient.getFavoriteGenres(userId);

        if (favoriteGenres == null || favoriteGenres.isEmpty()) {
            throw new RuntimeException("User has no favorite music genres!");
        }

        // 3. Get songs based on favorite genres from Song Service via Feign
        List<SongDTO> recommendedSongs = new ArrayList<>();
        for (String genre : favoriteGenres) {
            List<SongDTO> songs = songClient.getSongsByGenre(genre);
            recommendedSongs.addAll(songs);
        }

        // 4. Save playlist to database
        Playlist playlist = new Playlist();
        playlist.setUserId(userId);
        playlist.setName(user.getUsername() + "'s Auto Mix Playlist");
        playlist.setCreatedAt(LocalDateTime.now());
        playlistRepository.save(playlist);

        // 5. Return response with a proper DTO (using PlaylistResponseDTO instead of anonymous Object)
        return new PlaylistResponseDTO(
                playlist.getId(),
                playlist.getName(),
                user.getUsername(),
                recommendedSongs);
    }

    public List<Playlist> getUserPlaylists(Long userId) {
        return playlistRepository.findByUserId(userId);
    }
}
