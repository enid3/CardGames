package com.github.enid3.cardgames.webapp.service.impl;


import com.github.enid3.cardgames.webapp.data.dto.UserResponseDTO;
import com.github.enid3.cardgames.webapp.data.entity.User;
import com.github.enid3.cardgames.webapp.data.repository.UserRepository;
import com.github.enid3.cardgames.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponseDTO getUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Such User exception"));

        return new UserResponseDTO(user.getId(), user.getName(), user.getTokens());
    }
}
