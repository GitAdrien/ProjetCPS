## Service:
CharacterService

## Types:
bool, int, Command, EngineService, TechnicService, HitboxService

## Observators:
    const maxLife: [CharacterService] -> int
    const speed: [CharacterService] -> int
    const jumpSpeed: [CharacterService] -> int
    const gravity: [CharacterService] -> int

    positionX: [CharacterService] -> int
    positionY: [CharacterService] -> int
    engine: [CharacterService] -> EngineService
    charBox: [CharacterService] -> HitboxService
    life: [CharacterService] -> int
    technics: [CharacterService] -> list<TechnicService>
    currentTechnicHitbox: [CharacterService] -> HitboxService

    faceRight: [CharacterService] -> bool
    dead: [CharacterService] -> bool
    crouched: [CharacterService] -> bool
    stunned: [CharacterService] -> bool
    usingTechnic: [CharacterService] -> bool
    jumping: [CharacterService] -> bool
    blocking: [CharacterService] -> bool

## Constructors:
    init: int * int * int * int * bool * Engine -> [Character]
    pre init(l, s, g, js, f, e) requires
        l > 0 \and
        s > 0 \and
        g > 0 \and
        js >= 0

## Operators:
    moveLeft: [CharacterService] -> [CharacterService]
    moveRight: [CharacterService] -> [CharacterService]
    switchSide: [CharacterService] -> [CharacterService]
    crouch: [CharacterService] -> [CharacterService]
    standUp:[CharacterService] -> [CharacterService]
    jump: [CharacterService] * int -> [CharacterService]
    useTechnic: [CharacterService] * list<Command> -> [CharacterService]
    bumpLeft: [CharacterService] -> [CharacterService]
    bumpRight: [CharacterService] -> [CharacterService]
    step: [CharacterService] * Commande -> [CharacterService]

    addTechnic: [CharacterService] * TechnicService -> [CharacterService]
        pre addTechnic(c, t) requires t \not \in technics(c)

    takeDamages: [CharacterService] * int * int -> [CharacterService]
        pre takeDamages(c, a, s) requires a >= 0 \and s >= 0

    stepState: [CharacterService] -> [CharacterService]
    block: [CharacterService] -> [CharacterService]

## Observations:

[invariant]

    positionX(C) > 0 \and positionX(C) < Engine::width(engine)
    positionY(C) > 0 \and positionY(C) < Engine::height(engine)
    dead(C) = life <= 0

[init]

    life(init(l, s, g, js, f, e)) = l
    maxLife(init(l, s, g, js, f, e)) = l
    speed(init(l, s, g, js, f, e)) = s
    gravity(init(l, s, g, js, f, e)) = g
    jumpSpeed(init(l, s, g, js, f, e)) = js
    faceRight(init(l, s, g, js, f, e)) = f
    engine(init(l, s, g, js, f, e)) = e
    charbox(init(l, s, g, js, f, e)) = h

[moveLeft]

    (\exists i, player(engine(C), i)  != C \and collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i))))
    \implique positionX(moveLeft(C)) = positionX(C)
    positionX(C) ≤ speed(C)
    \and(\some i, player(engine(C), i)  != C \implique \non(collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i)))))
    \implique positionX(moveLeft(C)) = 0
    positionX(C) > speed(C)
    \and(\some i, player(engine(C), i)  != C \implique \non(collisionwith(hitbox(moveLeft(C)), hitbox(player(engine(C), i))))
    \implique positionX(moveLeft(C)) = PositionX(C) - speed(C)
    faceRight(moveLeft(C)) = faceRight(C) \and life(moveLeft(C)) = life(C)
    positionY(moveLeft(C)) = positionY(C)

[moveRight]

    (\exists i, player(engine(C), i)  != C \and collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i))))
    \implique positionX(moveRight(C)) = positionX(C)
    Engine::width(engine(C)) <= positionX(C) + speed(C)
    \and (\some i, player(engine(C), i)  != C \implique \non(collisionwith(hitbox(moveRight(C)), hitbox(player(engine(C), i)))))
    \implique positionX(moveRight(C)) = positionX(C) + speed(C)
    Engine::width(engine(C)) > positionX(C) + speed(C)
    \and(\some i, player(engine(C), i)  != C \implique \non(collisionwith(hitbox(moveRiht(C)), hitbox(player(engine(C), i))))
    \implique positionX(moveRight(C)) = Engine::width(engine(C))
    faceRight(moveRight(C)) = faceRight(C) \and life(moveRight(C)) = life(C)
    positionY(moveRight(C)) = positionY(C)

[switchSide]

    faceRight(switchSide(C)) != faceRight(C)
    positionX(switchSide(C)) = positionX(C)
    positionY(switchSide(C)) = positionY(C)

[crouch]:

    \not(crouched(C)) \implique ((positionX(crouch(C)) = positionX(C)) \and (positionY(crouch(C)) = positionY(C) - Hitbox::height(charBox(C))/2) \and Hitbox::height(charbox(C))/2 = Hitbox::height(charBox(crouch(C)))
    (crouched(C)) \implique C = crouch(C)

[standUp]:

    crouched(C) \implique ((positionX(standUp(C)) = positionX(C)) \and (positionY(standUp(C)) = positionY(C) + Hitbox::height(charBox(C))) \and Hitbox::height(charbox(C))/2 = Hitbox::height(charBox(standUp(C)))
    \not(crouched(C)) \implique C = standUp(C)


[bumpLeft]:

    positionX(bumpLeft(C) = min(Engine::width, positionX(C) + (speed(C)/2) + HitboxService::witdh(charbox(C))))

[bumpRight]:

    positionX(bumpRight(C)) = max(0, positionX(C) - (speed(C)/2)

[step]:

    step(C, LEFT) = moveLeft(C)
    step(C, RIGHT) = moveRight(C)
    step(C, NEUTRAL) = C
    step(C, DOWN) = crouch(C)
    step(C, UP) = jump(C, 0)
    step(C, UP_LEFT) = jump(C, -1)
    step(C, UP_RIGHT) = jump(C, 1)
    step(C, DOWN_LEFT) = crouch(C)
    step(C, DOW_RIGHT) = crouch(C)


[addTechnic]

    addTechnic(C,t) \implique t \in technics(C)

[takeDamages]

    life(takeDamages(C, a, s)) = life(C) - a
    ((s > 0) \and stunned(takeDamages(C, a, s))) = true

[block]

    (\not(usingTechnic(c)) \and \not(stunned(c))) => blocking(block(c))

[jump]
