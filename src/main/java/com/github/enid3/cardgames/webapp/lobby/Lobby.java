package com.github.enid3.cardgames.webapp.lobby;

import com.github.enid3.cardgames.game.impl.cardcomparsion.CardComparisonGamePlayer;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Lobby {
    private Long id;

    private List<CardComparisonGamePlayer> players;
    private Long size;


    public enum Status {
        NOT_FILLED, READY, IN_GAME, FINISHED
    }
    private Status status;
    public enum Type {
        OPEN, CLOSED
    }
    private Type type;

}
