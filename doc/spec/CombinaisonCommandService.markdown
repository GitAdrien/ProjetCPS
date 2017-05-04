## Service
CombinaisonCommandService

## Types
int, Command, SimpleDirectionCommand

## Observator
    commands: [CombinaisonCommandService] -> list<SimpleDirectionCommand>
    complexeCommands: [CombinaisonCommandService] -> list<Command> 
    player: [CombinaisonCommandService] -> int

## Constructors
    init: int ->       [CombinaisonCommandService]
    \pre:init(p) \requires p >= 0 \and p < 2

## Operators
    addCommand: [CombinaisonCommandService] * SimpleDirectionCommand -> [CombinaisonCommandService]
## Observations
[Invariant]

[init]:
    player(init(p)) = p
    commands(init(p)) = \empty
    complexeCommands(init(p)) = \empty

[addCommand]:
    addCommand(ccs, c) \implique c \in commands(ccs)
