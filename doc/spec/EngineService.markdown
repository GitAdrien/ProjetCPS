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
    init: int * int * int * Player * Player -> [Engine]
    pre init(h,w,s,p1,p2) \requires h > 0 \and s > 0 \and w > s \and p1 != p2

## Operators
    step: [Engine] * Command * Command -> [Engine]
    pre step(E) \requires \not(gameOver(E))

## Observations

[Invariant]

    gameOver(E) = Character::dead(player(E, 0)) \or Character::dead(player(E, 1))

[init]:

    height(init(h, w, s, p1, p2, f)) = h
    width(init(h, w, s, p1, p2, f)) = w
    player(init(h, w, s, p1, p2, f), 0) = p1
    player(init(h, w, s, p1, p2, f), 1) = p2
    Character::positionX(char(init(h, w, s, p1, p2, f), 0)) = w//2 − s//2
    Character::positionX(char(init(h, w, s, p1, p2, f), 1)) = w//2 + s//2
    Character::positionY(char(init(h, w, s, p1, p2, f), 0)) = height(init(h, w, s, p1, p2, f)) - HitboxService::height(CharacterService::charbox(PlayerService::character(p1)))
    Character::positionY(char(init(h, w, s, p1, p2, f), 0)) = height(init(h, w, s, p1, p2)) - HitboxService::height(CharacterService::charbox(PlayerService::character(p2)))
    Character::faceRight(char(init(h, w, s, p1, p2, f), 0))
    \not(Character::(faceRight(char(init(h, w, s, p1, p2, f), 1))))

[step]:

    \forall x \in {0, 1} character(step(E), x) = CharacterService::useTechnic(CharacterService::stepState(CharacterService::step(character(E, x), PlayerService::getActiveDirection(player(E, x)))), PlayerService::commandsWithinWindow(player(E, x)))
