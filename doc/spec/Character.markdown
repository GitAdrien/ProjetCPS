## Service: 
Character
## Types: 
bool, int, Command
## Observators:
positionX: [Character] -> int
positionY: [Character] -> int
engine: [Character] -> Engine
charBox: [Character] -> Hitbox
life: [Character] -> int
const speed: [Character] -> int
gravity: [Character] -> int
jumpSpeed: [Character] -> int
faceRight: [Character] -> bool
dead: [Character] -> bool
technics: [Character] -> list<TechnicService>
crouched: [Character] -> bool
stunned: [Character] -> bool
usingTechnic: [Character] -> bool
currentTechnicHitbox: [Character] -> HitboxService

## Constructors:
init: int * int * bool * Engine -> [Character]
pre init(l,s,f,e) requires l > 0 \and s > 0

## Operators:


moveLeft: [Character] -> [Character]
moveRight: [Character] -> [Character]
switchSide: [Character] -> [Character]
crouch: [Character] -> [Character] 
standUp:[Character] -> [Character]
jump: [Character] * int -> [Character]
useTechnic: [Character] * list<Command> -> [Character]
bumpLeft: [Character] -> [Character]
bumpRight: [Character] -> [Character]
step: [Character] * Commande -> [Character]
pre step() requires \no(dead)
addTechnic: [Character] * TechnicService -> [Character]
pre addTechnic(t) requires t \not \in technics(c)
takeDamages: [Character] * int * int -> [Character]
pre takeDamages(a, s) requires a >= 0 \and s >= 0


## Observations:

[invariant]:
positionX(C) > 0 \and positionX(C) < Engine:: width(engine)
positionY(C) > 0 \and positionY(C) < Engine:: height(engine)
dead(C) = \no(life > 0)

[init]:
life(init(l, s, f, e)) = l \and speed(init(l, s, f, e)) = s \and faceRight(init(l, s, f, e)) = f
\and engine(init(l, s, f, e)) = e
\exists h :Hitbox, charbox(init(l, s, f, e)) = h

[moveLeft]:
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

[moveRight]:
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

[switchSide]:
faceRight(switchSide(C)) != faceRight(C)
positionX(switchSide(C)) = positionX(C)

[crouch]:
\no(crouched(C)) \implique ((positionX(crouch(C)) = positionX(C)) \and (positionY(crouch(C)) = positionY(C) - Hitbox::height(charBox(C))/2) \and Hitbox::height(charbox(C))/2 = Hitbox::height(charBox(crouch(C))) 
(crouched(C)) \implique C = crouch(C)

[standUp]:
crouched(C) \implique ((positionX(standUp(C)) = positionX(C)) \and (positionY(standUp(C)) = positionY(C) + Hitbox::height(charBox(C))) \and Hitbox::height(charbox(C))/2 = Hitbox::height(charBox(standUp(C))) 
\no(crouched(C)) \implique C = standUp(C)


[bumpLeft]:
positionX(bumpLeft(C) = min(Engine::width, positionX(C) + speed(C)/2))

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