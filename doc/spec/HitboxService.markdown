## Service
HitboxService

## Types

Integer, Boolean

## Observator

    positionX:[HitboxService] -> Integer
    positionY:[HitboxService] -> Integer
    width:[HitboxService] -> Integer
    height:[HitboxService] -> Integer
    belongsTo:[HitboxService] x Integer x Integer -> Boolean
    collidesWith:[HitboxService] x HitboxService -> Boolean
    equalsTo:[HitboxService] x HitboxService -> Boolean

## Constructors

    init:Integer x Integer x Integer x Integer -> HitboxService
    \pre init(x, y, w, h) \require
        w > 0 \and
        h > 0 \and


## Operators

    moveTo:[HitboxService] x Integer x Integer -> [HitboxService]
    \pre moveTo(h, x, y) \require
        x>=0 \and
        y>=0

    setWidth:[HitboxService] x Integer -> [HitboxService]
    \pre setWidth(hb, w) \require
        w > 0

    setHeight:[HitboxService] x Integer -> [HitboxService]
    \pre setHeight(hb, h) \require
        h > 0

    copy:[HitboxService] -> HitboxService

## Observations

[Invariant]

    (min) collidesWith(h1, h2) = \exists x, y \in belongsTo(h1, x, y) \and belongsTo(h2, x, y)
    (min) equalsTo(h1, h2) = \exists x, y \in belongsTo(h1, x, y) = belongsTo(h2, x, y)
    height(h) > 0
    witdh(h) > 0

[init]

    PositionX(init(x, y, w, h)) = x
    PositionY(init(x, y, w, h)) = y
    width(init(x, y, w, h)) = w
    height(init(x, y, w, h)) = h

[moveTo]

    positionX(moveTo(h, x, y)) = x
    positionY(moveTo(h, x, y)) = y
    u, v, belongsTo(moveTo(h, x, y), u, v) =
        belongsTo(h, u-(x-positionX(h)), v-(y-positionY(h))

[setWidth]

    width(setWidth(hb, w)) > 0
    width(setWidth(hb, w)) = w


[setHeight]

    height(setHeight(hb, h)) > 0
    height(setHeight(hb, h)) = h

[equalsTo]

    equalsTo(h1, h2) <=>
        (positionX(h1) = positionX(h2) \and
        positionY(h1) = positionY(h2) \and
        width(h1) = width(h2) \and
        height(h1) = height(h2))

[copy]

    equalsTo(copy(h), h) = true
