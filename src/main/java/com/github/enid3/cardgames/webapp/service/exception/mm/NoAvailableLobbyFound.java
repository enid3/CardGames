package com.github.enid3.cardgames.webapp.service.exception.mm;

import com.github.enid3.cardgames.webapp.service.exception.ServiceException;

public class NoAvailableLobbyFound extends ServiceException {
    public NoAvailableLobbyFound() {
        super("No available lobby found");
    }
}
