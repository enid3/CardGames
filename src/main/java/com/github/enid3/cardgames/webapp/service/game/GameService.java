package com.github.enid3.cardgames.webapp.service.game;

import com.github.enid3.cardgames.webapp.service.game.management.GameInteractionManagementService;
import com.github.enid3.cardgames.webapp.service.game.management.GameLifecycleManagementService;
import com.github.enid3.cardgames.webapp.service.game.provider.GameIdProvider;
import com.github.enid3.cardgames.webapp.service.game.provider.GameListProvider;
import com.github.enid3.cardgames.webapp.service.game.provider.GameStateProvider;

public interface GameService extends
        GameInteractionManagementService,
        GameLifecycleManagementService,
        GameStateProvider,
        GameListProvider,
        GameIdProvider

{ }
