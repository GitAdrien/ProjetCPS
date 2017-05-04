## Service
EngineService

## Types
Integer, CharacterService, PlayerService, Boolean

## Observator
    \const width:[EngineService] -> Integer
    \const height:[EngineService] -> Integer

    character:[EngineService] x Integer -> CharacterService
    \pre : character(e, i) \require
        i \in {0,1}

    player:[EngineService] x Integer -> PlayerService
    \pre : player(e, i) \require
        i \in {0,1}
    gameOver:[EngineService] -> Boolean
    frameCounter:[EngineService] -> FrameCounterService

## Constructors
    

## Operators

## Observations

[Invariant]

    Say stuff.
