## Service
TechnicService

## Types
String, Command, Integer, HitboxService
## Observator
    name:[TechnicService] -> String
    commands:[TechnicService] -> List<Command>
    damage:[TechnicService] -> Integer
    hitbox:[TechnicService] -> HitboxService
    frame:[TechnicService] -> Integer
    stun:[TechnicService]  -> Integer
## Constructors
    init:String x List<Command> x Integer x Integer x Integer x HitboxService -> [TechnicService]
    pre init(n, c, d, f, s, h) require
        n != \empty \and
        c != \empty \and
        h != \empty \and
        d >= 0 \and
        f >= 0 \and
        s >= 0 \and


## Operators

None

## Observations

[Invariant]

    hitbox(t) != empty

[init]

    name(init(n, c, d, f, s, h)) = n
    commands(init(n, c, d, f, s, h)) = c
    damage(init(n, c, d, f, s, h)) = d
    hitbox(init(n, c, d, f, s, h)) = h
    frame(init(n, c, d, f, s, h)) = f
    stun(init(n, c, d, f, s, h)) = s
