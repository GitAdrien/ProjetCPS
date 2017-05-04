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

[init]

    window(init(w, c, i)) = w
    character(init(w, c, i)) = c
    inputManager(init(w, c, i)) = i

[getActiveAttack]

    InputManagerService::isPressed(inputManager(p), c1) \and
    InputManagerService::isPressed(inputManager(p), c2) \and
    (\exists c, c \in ComplexeAttackCommand, (ComplexeAttackCommand::getC1(c, c1) \and ComplexeAttackCommand::getC2(c, c1)) \or
    (ComplexeAttackCommand::getC2(c, c1) \and ComplexeAttackCommand::getC1(c, c1))) => getActiveAttack(p) = c
    InputManagerService::isPressed(inputManager(p), c) \and c \in AttackCommand => getActiveAttack(p) = c


[getActiveDirection]

    InputManagerService::isPressed(inputManager(p), c1) \and
    InputManagerService::isPressed(inputManager(p), c2) \and
    (\exists c, c \in ComplexeDirectionCommand, (ComplexeDirectionCommand::getC1(c, c1) \and ComplexeDirectionCommand::getC2(c, c1)) \or
    (ComplexeDirectionCommand::getC2(c, c1) \and ComplexeDirectionCommand::getC1(c, c1))) => getActiveDirection(p) = c
    InputManagerService::isPressed(inputManager(p), c) \and c \in DirectionCommand => getActiveDirection(p) = c
