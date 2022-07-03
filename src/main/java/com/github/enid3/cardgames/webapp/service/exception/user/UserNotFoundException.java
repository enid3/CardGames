package com.github.enid3.cardgames.webapp.service.exception.user;

public class UserNotFoundException extends UserServiceException{
    public UserNotFoundException(Long id) {
        super("Can't fina user with id=" + id);
    }
}
