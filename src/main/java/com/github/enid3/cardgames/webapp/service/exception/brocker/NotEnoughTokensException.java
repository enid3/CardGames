package com.github.enid3.cardgames.webapp.service.exception.brocker;

import com.github.enid3.cardgames.webapp.data.entity.User;

public class NotEnoughTokensException extends BrockerException {
    public NotEnoughTokensException(User user, long tokensToReserve) {
        super("Can't reserve tokens(" + tokensToReserve  +  ") " +
                "for user(" + user.toString() + ")");

    }

}
