package com.github.enid3.cardgames.webapp.service.exception.brocker;

import com.github.enid3.cardgames.webapp.service.exception.ServiceException;

public class BrockerException extends ServiceException {
    public BrockerException(String message) {
        super(message);
    }

    public BrockerException(Throwable throwable) {
        super(throwable);
    }
}
