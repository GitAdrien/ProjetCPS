## Service
PlayerService

## Types
Integer, Command, CharacterService, InputManagerService, DirectionCommand, AttackCommand

## Observator
    window:[PlayerService] -> Integer
    commandsWithinWindow:[PlayerService] -> List<Command>
    character[PlayerService] -> CharacterService
    inputManager[PlayerService] -> InputManagerService

## Constructors
    init:Integer x CharacterService x InputManagerService -> [PlayerService]
    \pre init(w, c, i) \require
        w >= 0

## Operators
    getActiveDirection:[PlayerService] -> DirectionCommand
    getActiveAttack:[PlayerService] -> AttackCommand

## Observations

[Invariant]

    none

[getActiveAttack]

    TODO

[getActiveDirection]

    TODO
