package com.github.enid3.cardgames.webapp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private long id;
    private String name;
    private long tokens;

}
