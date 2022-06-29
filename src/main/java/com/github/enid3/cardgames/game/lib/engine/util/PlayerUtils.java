package com.github.enid3.cardgames.game.lib.engine.util;

import com.github.enid3.cardgames.game.lib.engine.entity.Strategy;
import com.github.enid3.cardgames.game.lib.common.Identifiable;
import com.github.enid3.cardgames.game.lib.engine.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PlayerUtils {
    private PlayerUtils() {}
    public static <P extends Player, S extends Strategy> Map<S, List<P>> groupPlayersByStrategy(Map<P, S> playersStrategies) {
        Map<S, List<P>> byStrategy = playersStrategies.entrySet().stream()
                .collect(
                        Collectors.groupingBy(
                            Map.Entry<P, S>::getValue,
                            Collectors.mapping(
                                    Map.Entry<P, S>::getKey,
                                    Collectors.toList()
                            )
                        )
                );
        return byStrategy;
    }

    public static <P extends Player> Map<Identifiable, P> toMap(Collection<? extends Player> players, Function<Player, P> typeMapper){
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        typeMapper
                ));
    }

    public static <P extends Player> void applyPayoff(Map<P, Long> payoff) {
        for(Map.Entry<P, Long> e : payoff.entrySet()) {
            Player player = e.getKey();
            Long diff = e.getValue();
            player.setTokens(player.getTokens() + diff);
        }
    }
    public static <P extends Player> void applyPayoff(Collection<P> players, Long payoff) {
        for(Player player : players) {
            player.setTokens(player.getTokens() + payoff);
        }
    }

    public static <I extends Identifiable> Iterable<Identifiable> map(Collection<I> toBeMapped, Function<I, Identifiable> mapper) {
        return toBeMapped.stream().map(mapper).collect(Collectors.toList());
    }

    public static <I extends Identifiable> Map<Identifiable, Strategy> map(Map<I, Strategy> toBeMapped,
                                                                           Function<I, Identifiable> mapper) {
        return toBeMapped.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> mapper.apply(e.getKey()),
                        Map.Entry::getValue
                ));
    }
}
