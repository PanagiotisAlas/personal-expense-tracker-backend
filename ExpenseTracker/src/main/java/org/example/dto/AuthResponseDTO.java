package org.example.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String message;

    public AuthResponseDTO(String token, String message) {
        this.message = message;
        this.token = token;
    }
}
