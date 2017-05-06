Types Command

Types AttackCommand extends Command

Types SimpleAttackCommand extends AttackCommand = enum {
    PUNCH("punch", 3)
    KICK("kick", 3)
    NONE("none", 0)

    Observator
        getPriority: [SimpleAttackCommand] -> Integer

    Constructors
        init: String x Integer -> [SimpleAttackCommand]

    Observations
    [init]
        getPriority(init(n, p)) = p

}

Types ComplexeAttackCommand extends AttackCommand = enum {
    PUNCH_N_KICK(SimpleAttackCommand.PUNCH, SimpleAttackCommand.KICK)

    Observator
        getC1: [ComplexeAttackCommand] -> Command
        getC2: [ComplexeAttackCommand] -> Command

    Constructors
        init: Command x Command -> [ComplexeAttackCommand]

    Observations
    [init]
        getC1(init(c1, c2)) = c1
        getC2(init(c1, c2)) = c2

}

Types DirectionCommand extends Command

Types SimpleDirectionCommand extends DirectionCommand = enum {
    LEFT("left", 2)
    RIGHT("right", 2)
    NEUTRAL("neutral", 0)
    DOWN("down", 2)
    UP("up", 2)


    Observator
        getPriority: [SimpleDirectionCommand] -> Integer

    Constructors
        init: String x Integer -> [SimpleDirectionCommand]

    Observations
    [init]
        getPriority(init(n, p)) = p

}

Types ComplexeDirectionCommand extends DirectionCommand = enum {
    DOWN_RIGHT(SimpleDirectionCommand.DOWN, SimpleDirectionCommand.RIGHT)
    DOWN_LEFT(SimpleDirectionCommand.DOWN,SimpleDirectionCommand.LEFT)
	UP_LEFT(SimpleDirectionCommand.UP, SimpleDirectionCommand.LEFT)
    UP_RIGHT(SimpleDirectionCommand.UP, SimpleDirectionCommand.RIGHT)


    Observator
        getC1: [ComplexeDirectionCommand] -> Command
        getC2: [ComplexeDirectionCommand] -> Command

    Constructors
        init: Command x Command -> ComplexeDirectionCommand

    Observations
    [init]
        getC1(init(c1, c2)) = c1
        getC2(init(c1, c2)) = c2

}
