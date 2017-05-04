## Service
InputManagerService

## Types
Boolean, Command

## Observator
    isPressed:[InputManagerService] x Command -> Boolean

## Constructors
    init: none -> [InputManagerService]

## Operators
    setPressed:[InputManagerService] x Command -> [InputManagerService]
    setReleased:[InputManagerService] x Command -> [InputManagerService]

## Observations

[Invariant]

    none

[init]

    isPressed(init(), c) = false

[setPressed]

    isPressed(setPressed(im, c), c) = true
    isPressed(setPressed(setReleased(im, c), c), c) = true

[setReleased]

    isPressed(setReleased(im, c), c) = false
    isPressed(setReleased(setPressed(im, c), c), c) = false
