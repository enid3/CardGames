package com.github.enid3.cardgames.webapp.data.dto;

import com.github.enid3.cardgames.webapp.lobby.Lobby;
import lombok.Getter;

@Getter
public class GameLobbyDTO {
    Lobby.Status status;
    Lobby.Type type;
}
