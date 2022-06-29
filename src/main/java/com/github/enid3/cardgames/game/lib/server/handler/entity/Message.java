package com.github.enid3.cardgames.game.lib.server.handler.entity;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// TODO fix: more useful class-name
public class Message {
    private final Identifiable id;
    private final String strategy;
}
