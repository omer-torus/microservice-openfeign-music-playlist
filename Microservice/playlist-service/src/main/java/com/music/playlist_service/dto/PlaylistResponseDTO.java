package com.music.playlist_service.dto;

import com.music.playlist_service.client.dto.SongDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponseDTO {

    private Long playlistId;
    private String playlistName;
    private String owner;
    private List<SongDTO> songs;
}
