package com.music.playlist_service.client.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<String> favoriteGenres;
}
