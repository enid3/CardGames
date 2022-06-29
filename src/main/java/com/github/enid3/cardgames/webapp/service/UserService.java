package com.github.enid3.cardgames.webapp.service;

import com.github.enid3.cardgames.webapp.data.dto.UserResponseDTO;

public interface UserService {
    public UserResponseDTO getUser(long id);
}
