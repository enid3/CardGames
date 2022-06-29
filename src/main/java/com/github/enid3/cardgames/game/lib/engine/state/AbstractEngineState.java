package com.github.enid3.cardgames.game.lib.engine.state;

import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Strategy;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import lombok.*;

import java.util.Collection;
import java.util.Map;

@Getter
@AllArgsConstructor
public class AbstractEngineState<P extends Player> implements GameState {
    protected Collection<P> players;
    // TODO fix: JSON serialization polymorphism
    protected Map<Identifiable, Strategy> playerStrategies;
    protected Iterable<Identifiable> winners;

    protected AbstractEngineState(AbstractEngineState<P> state) {
        this.winners = state.winners;
        this.players = state.players;
        this.playerStrategies = state.playerStrategies;
    }
}

