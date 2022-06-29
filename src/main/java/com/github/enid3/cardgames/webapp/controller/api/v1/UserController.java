package com.github.enid3.cardgames.webapp.controller.api.v1;

import com.github.enid3.cardgames.webapp.data.dto.UserResponseDTO;
import com.github.enid3.cardgames.webapp.data.entity.Identifiable;
import com.github.enid3.cardgames.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Provide information about yourself
     * @param id Authentication principal of user;
     * @return Information about user(id, name, ...)
     * @see UserResponseDTO
     */
    @GetMapping("/me")
    UserResponseDTO whoAmI(@AuthenticationPrincipal Identifiable id) {
        return userService.getUser(id.getId());
    }
}
