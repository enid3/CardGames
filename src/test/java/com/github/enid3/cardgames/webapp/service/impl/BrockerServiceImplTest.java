package com.github.enid3.cardgames.webapp.service.impl;

import com.github.enid3.cardgames.game.lib.common.impl.LongIdentifiable;
import com.github.enid3.cardgames.game.lib.common.impl.LongLongIdentifiable;
import com.github.enid3.cardgames.webapp.data.entity.User;
import com.github.enid3.cardgames.webapp.data.repository.UserRepository;
import com.github.enid3.cardgames.webapp.service.exception.brocker.NotEnoughTokensException;
import com.github.enid3.cardgames.webapp.service.exception.user.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrockerServiceImplTest {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final static long EXISTING_USER_ID = 100;
    private final static User existingUser = new User(EXISTING_USER_ID, "user", 900L, 100L);

    private final static long NON_EXISTING_USER_ID = 100;

    private BrockerServiceImpl bs = new BrockerServiceImpl(userRepository);


    @Test
    void whenReserveUserNotExists_shouldTrowUserNotFoundException() {
        when(userRepository.findById(NON_EXISTING_USER_ID))
                .thenReturn(Optional.empty());
         assertThrows(UserNotFoundException.class,
                 () -> bs.reserve(NON_EXISTING_USER_ID, 100));
    }

    @Test
    void whenReserveUserHasNotEnoughExceptions_shouldThrowNotEnoughTokensException () {
        when(userRepository.findById(existingUser.getId()))
                .thenReturn(Optional.of(new User(EXISTING_USER_ID, "user", 900L, 100L)));
        assertThrows(NotEnoughTokensException.class,
                () -> bs.reserve(EXISTING_USER_ID, existingUser.getTokens() + 1));

    }

    @Test
    void whenReserveUserHasEnoughTokens_shouldPersistCorrectly () {
        when(userRepository.findById(existingUser.getId()))
                .thenReturn(Optional.of(new User(10L, "user", 900L, 100L)));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        long tokensToReserve = existingUser.getTokens() / 2;
        bs.reserve(existingUser.getId(), tokensToReserve);

        verify(userRepository).save(userCaptor.capture());
        User toBePersisted = userCaptor.getValue();

        assertEquals(existingUser.getTokens() - tokensToReserve, toBePersisted.getTokens());
        assertEquals(existingUser.getReservedTokes() + tokensToReserve, toBePersisted.getReservedTokes());
    }

    @Test
    void whenReserveAllTokens_shouldPersistCorrectly () {
        when(userRepository.findById(existingUser.getId()))
                .thenReturn(Optional.of(new User(10L, "user", 900L, 100L)));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        bs.reserve(existingUser.getId(), existingUser.getTokens());

        verify(userRepository).save(userCaptor.capture());
        User toBePersisted = userCaptor.getValue();

        assertEquals(0L, toBePersisted.getTokens());
        assertEquals(existingUser.getTokens() + existingUser.getReservedTokes(),
                toBePersisted.getReservedTokes());
    }

    @Test
    void whenIdFromLongIdentifiable_shouldReturnId() {
        long idPrimitive = 123L;
        LongIdentifiable id = LongIdentifiable.of(idPrimitive);

        long resultId = bs.id(id);

        assertEquals(idPrimitive, resultId);
    }

    @ParameterizedTest
    @CsvSource({"0, 1"})
    void whenIdFromLongLongIdentifiable_shouldReturnId(long modA, long modB) {
        long idPrimitive = 123L;

        LongLongIdentifiable idA = LongLongIdentifiable.of(idPrimitive, modA);
        LongLongIdentifiable idB = LongLongIdentifiable.of(idPrimitive, modB);
        assertNotEquals(idA, idB);

        long resultIdA = bs.id(idA);
        long resultIdB = bs.id(idB);

        assertEquals(idPrimitive, resultIdA);
        assertEquals(idPrimitive, resultIdB);

    }
}