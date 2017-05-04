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
    pre step(E) \requires \no(gameOver(E))

## Observations

[Invariant]
gameOver(E) = \exists i \in {1, 2} Character ::dead(player(E, i))
[init]:
height(init(h, w, s, p1, p2)) = h
width(init(h, w, s, p1, p2)) = w
player(init(h, w, s, p1, p2), 1) = p1
player(init(h, w, s, p1, p2), 2) = p2
Character ::positionX(char(init(h, w, s, p1, p2), 1)) = w//2 − s//2
Character ::positionX(char(init(h, w, s, p1, p2), 2)) = w//2 + s//2
Character ::positionY(char(init(h, w, s, p1, p2), 1)) = height(init(h, w, s, p1, p2)) - hCharact
Character ::positionY(char(init(h, w, s, p1, p2), 2)) = height(init(h, w, s, p1, p2)) - hCharact
Character ::faceRight(char(init(h, w, s, p1, p2), 1))
Character ::\no(faceRight(char(init(h, w, s, p1, p2), 2)))
[step]:
char(step(E, C1, C2), 1) = step(char(E, 1), C1)
char(step(E, C1, C2), 2) = step(char(E, 2), C2)
