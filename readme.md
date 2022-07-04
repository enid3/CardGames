# Overview
## Note 
I would like to admit, that abstraction segregation
has taken * a bit * more that I've expected.
It affects some other tarts of provided solution.
Like there is no `@ExceptionHander`'s, Http response
codes, that not follow specification and even unimplemented method.

## Game abstractions
Game package overview is provided in corresponding
`package-info.java` file(s).

## Communication
Process of creating new game is represented on the
following communication diagram.
![Alt text](docs/creategame.svg?raw=true "Create Game")


# Known limitations

## No reusable turn policy

At this moment, there is only one approach,
that allow providing turn sequence logic
(no matter simple or complex): by providing
`AbstractGameEngine` method:

`boolean validate(Identifiable playerId, Strategy strategy, Object... args);`

## No logic for leaving Game 

Since all general purpose data-structures, responsible for
providing access to `Player`'s are located in 
`AbstractGameEngine` - it's not a problem to provide 
leave operation.

But providing this functionality, at the exact that moment, 
brings plenty side-tasks, which are hard to accomplish 
in the context of the incompleteness(no tests, TODO's) of existing.
providing custom size, at tim.

## No Reusable Strategies
Implementing new game engine requires providing full set of
strategies, even most of them are common(PLAY, FOLD, ...).

Seems like thinking in this way will lead to `Entity-Component-System` 
pattern.

## No abstractions for user-specific & common; private & public game states
`GameState` interface represents immutable state of specific game.
Actual `GameState` can be received from role interface method `StateProvider.getState()`.
`GameEngineHandler` implements this interface too, providing
synchronization.

So, in case player must know, for instance, his hand, 
size of deck, [flop[turn[river]]], ... 
I'm using single `GameState` instance to handle it all at the
same time.
It causes security issues(there's full deck in response).
In terms of asynchronous communication(WebSocket):
ineffective network usage (effective one is by providing differences only),
state parsing logic required on the service layer, in order to provide
user-specific state.

By the way, it's poor from the point of code reuse, since we
have to duplicate `*GameEngine` variables in `*GameState`.


## Other
There are some `\\ TODO ...` around project, which 
represent limitations, bugs, poor abstractions and so on.

# Usage
You can use `docker-compose` to run application.


# Authentication
**Authentication** is performed based on `AUTHORIZATION` header,
it's considered to be username. Workaround only.



