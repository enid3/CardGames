package com.github.enid3.cardgames.webapp.service.exception.user;

import com.github.enid3.cardgames.webapp.service.exception.ServiceException;

public class UserServiceException extends ServiceException {
    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(Throwable throwable) {
        super(throwable);
    }
}
