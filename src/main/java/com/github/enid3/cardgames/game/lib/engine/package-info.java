/**
 * There is some info, about game engine abstraction, provided
 * in this package.
 *
 * At first, any game engine will operate {@link com.github.enid3.cardgames.game.lib.common.Identifiable}
 * instead of direct value, like {@link java.lang.Long}, {@link java.lang.String}, ...;
 * The purpose of {@link com.github.enid3.cardgames.game.lib.common.Identifiable}
 * is identification {@link com.github.enid3.cardgames.game.lib.engine.entity.Player}.
 * In some cases, it's cool to use {@link java.lang.Long} (like in mine), because
 * it's a primary key in the database. In others {@link java.lang.String} is useful,
 * for instance, in HotSeat games, where we will use nicknames for identify current player,
 * and matching {@link java.lang.Long} values will only complicate logic.
 *
 * Second one - {@link com.github.enid3.cardgames.game.lib.engine.entity.Player} is
 * a main entity, over the collection of which, game engines are operate.
 * It contains one extra field: {@link com.github.enid3.cardgames.game.lib.engine.entity.Player#getTokens()}
 * kind of score, money and so on.
 *
 * {@link com.github.enid3.cardgames.game.lib.engine.GeneralGameEngine} is a
 * header interface, that represent type of engines, supported from the box.
 *
 * Any game engine is responsible only for game logic and state. That's all.
 * No concurrency, no communication, no notifying, etc.
 *
 * For handling game engine's lifecycle(in thread safe manner)
 * {@link com.github.enid3.cardgames.game.lib.server.handler.GameEngineHandler}
 * can be used;
 * Besides it, there is an implementation,
 * {@link com.github.enid3.cardgames.game.lib.server.handler.impl.NotifyingGameEngineHandler},
 * that supports bi-directional communication,
 * through {@link com.github.enid3.cardgames.game.lib.server.notification.NotificationProvider}
 * so it allows, for example, implement Socket(WebSocket) based interface.
 *
 * Another useful feature of this package is providing factories,
 * that simplify game engines/handlers creation.
 * @see com.github.enid3.cardgames.game.lib.util.GameHandlerFactory
 * @see com.github.enid3.cardgames.game.lib.util.GameEngineFactory
 *
 * Potentially, they can be implimented with DI support, but now
 * it requires {@link com.github.enid3.cardgames.game.lib.util.GameHandlerFactory#register(java.lang.String, java.lang.Class, com.github.enid3.cardgames.game.lib.engine.configuration.Configuration)}
 *
 */
package com.github.enid3.cardgames.game.lib.engine;