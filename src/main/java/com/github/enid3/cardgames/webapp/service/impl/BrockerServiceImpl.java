package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.common.impl.LongIdentifiable;
import com.github.enid3.cardgames.game.lib.common.impl.LongLongIdentifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.webapp.data.entity.User;
import com.github.enid3.cardgames.webapp.data.repository.UserRepository;
import com.github.enid3.cardgames.webapp.service.UserService;
import com.github.enid3.cardgames.webapp.service.brocker.IdentifiableBrocker;
import com.github.enid3.cardgames.webapp.service.brocker.PlayerBrocker;
import com.github.enid3.cardgames.webapp.service.exception.ServiceException;
import com.github.enid3.cardgames.webapp.service.exception.brocker.NotEnoughTokensException;
import com.github.enid3.cardgames.webapp.service.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BrockerServiceImpl implements PlayerBrocker, IdentifiableBrocker {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Player reserve(long userId, long tokensToReserve) {
        synchronized (this) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> (new UserNotFoundException(userId)));

            if(user.getTokens() >= tokensToReserve) {
                user.setTokens(user.getTokens() - tokensToReserve);
                user.setReservedTokes(user.getReservedTokes() + tokensToReserve);
                userRepository.save(user);
                return new Player(user.getId(), tokensToReserve);
            }  else {
                throw new NotEnoughTokensException(user, tokensToReserve);
            }
        }
    }

    @Override
    public Player cast(long userid) {
        return new Player(userid, 0);
    }

    @Override
    public long id(Identifiable id) {
        if(id instanceof LongIdentifiable)  {
            LongIdentifiable li = (LongIdentifiable) id;
            return li.getId();
        } else
        throw new IllegalArgumentException("This version of identifiable not supported");
    }

    @Override
    public Identifiable id(long id) {
        return LongIdentifiable.of(id);
    }

    @Override
    public Identifiable id(long id, long modifier) {
        return LongLongIdentifiable.of(id, modifier);
    }
}
