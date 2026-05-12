package com.music.playlist_service.client.dto;

import lombok.Data;

@Data
public class SongDTO {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private int durationSeconds;
}
