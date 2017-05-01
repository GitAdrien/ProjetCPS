package enums.direction;

import enums.Command;
import enums.DirectionCommand;

public enum ComplexeDirectionCommand implements DirectionCommand {
	DOWN_RIGHT(SimpleDirectionCommand.DOWN, SimpleDirectionCommand.RIGHT), DOWN_LEFT(SimpleDirectionCommand.DOWN,SimpleDirectionCommand.LEFT),
	UP_LEFT(SimpleDirectionCommand.UP, SimpleDirectionCommand.LEFT), UP_RIGHT(SimpleDirectionCommand.UP, SimpleDirectionCommand.RIGHT);
	
	private Command c1, c2;
	
	private ComplexeDirectionCommand(Command c1, Command c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Command getC1() {
		return c1;
	}
	
	public Command getC2() {
		return c2;
	}
	
	@Override
	public String toString() {
		return c1 + "+" + c2;
	}
}
