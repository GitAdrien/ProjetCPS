## Service
FrameCounterService

## Types
Integer

## Observator
    frame:[FrameCounterService] -> Integer
    max:[FrameCounterService] -> Integer

## Constructors
    init: Integer -> [FrameCounterService]
    pre init(m) require
        m > 0

## Operators

    nextFrame:[FrameCounterService] -> [FrameCounterService]
    difference2:[FrameCounterService] x Integer -> Integer
    pre difference(fc, i) require
        i >= 0 and
        i < max(fc)

    difference1:[FrameCounterService] x Integer x Integer -> Integer
    pre difference(fc, i, j) \require
        i >= 0 and
        i < max(fc) and
        j >= 0 and
        j < max(fc)

## Observations

[Invariant]

    frame(fc) >= 0
    frame(fc) < max(fc)

[init]

    max(init(m)) = m
    frame(init(m)) = 0

[difference1]

    i < j => difference1(fc, i, j) = (max(fc) - i) + j
    i >= j => difference1(fc, i, j) = i - j

[difference2]

    difference2(fc, i) = difference1(fc, max(fc), i)
