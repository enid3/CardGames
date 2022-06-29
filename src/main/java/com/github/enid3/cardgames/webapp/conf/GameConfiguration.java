package com.github.enid3.cardgames.webapp.conf;


import com.github.enid3.cardgames.game.impl.cardcomparsion.CardComparisonGameEngine;
import com.github.enid3.cardgames.game.impl.cardcomparsion.CardComparisonGameConfiguration;
import com.github.enid3.cardgames.game.impl.cardcomparsion.MaxCardInHandComparator;
import com.github.enid3.cardgames.game.lib.engine.components.card.pack.french.FrenchPack;
import com.github.enid3.cardgames.game.lib.util.GameHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Initializing & sharing {@link com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler}
 */
@Configuration
public class GameConfiguration {
    @Bean
    GameHandlerFactory getGameHandlerFactory(){
        GameHandlerFactory ghf = GameHandlerFactory.getInstance();
        CardComparisonGameConfiguration.CardComparisonGameConfigurationBuilder builder =
                CardComparisonGameConfiguration.builder()
                .cardPack(FrenchPack.getInstance())
                .handComparator(new MaxCardInHandComparator());
        ghf.register("single-card-game", CardComparisonGameEngine.class,
                        builder.handSize(1) .build());

        ghf.register("double-card-game", CardComparisonGameEngine.class,
                builder.handSize(2) .build());

        return ghf;
    }
}
