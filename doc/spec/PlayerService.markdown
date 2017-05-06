## Service
PlayerService

## Types
Integer, Command, CharacterService, InputManagerService, DirectionCommand, AttackCommand

## Observator
    const window:[PlayerService] -> Integer
    commandsWithinWindow:[PlayerService] -> List<Command>
    character[PlayerService] -> CharacterService
    inputManager[PlayerService] -> InputManagerService
    lastInput[PlayerService] -> Integer
    

## Constructors
    init:Integer x CharacterService x InputManagerService -> [PlayerService]
    pre init(w, c, i) require
        w >= 0

## Operators
    getActiveDirection:[PlayerService] -> DirectionCommand
    getActiveAttack:[PlayerService] -> AttackCommand

## Observations

[Invariant]

    ((FrameCounterService::difference(EngineService::frameCounter(character(p)), lastInput(p)) > window) and getActiveDirection(p) = NEUTRAL and getActiveAttack(p) = NONE)
    => commandsWithinWindow(p) = empty
[init]

    window(init(w, c, i)) = w
    character(init(w, c, i)) = c
    inputManager(init(w, c, i)) = i

[getActiveAttack]

    InputManagerService::isPressed(inputManager(p), c1) \and
    InputManagerService::isPressed(inputManager(p), c2) \and
    (exists c, c in ComplexeAttackCommand, (ComplexeAttackCommand::getC1(c, c1) and ComplexeAttackCommand::getC2(c, c2)) or
    (ComplexeAttackCommand::getC2(c, c1) and ComplexeAttackCommand::getC1(c, c2))) => getActiveAttack(p) = c
    InputManagerService::isPressed(inputManager(p), c) and c in AttackCommand => getActiveAttack(p) = c


[getActiveDirection]

    InputManagerService::isPressed(inputManager(p), c1) and
    InputManagerService::isPressed(inputManager(p), c2) and
    (exists c, c \in ComplexeDirectionCommand, (ComplexeDirectionCommand::getC1(c, c1) and ComplexeDirectionCommand::getC2(c, c2)) or
    (ComplexeDirectionCommand::getC2(c, c1) and ComplexeDirectionCommand::getC1(c, c2))) => getActiveDirection(p) = c
    InputManagerService::isPressed(inputManager(p), c) and c in DirectionCommand => getActiveDirection(p) = c
