package enums;

public enum ComplexeCommand implements Command {
	DOWN_RIGHT(SimpleCommand.DOWN, SimpleCommand.RIGHT), DOWN_LEFT(SimpleCommand.DOWN,SimpleCommand.LEFT),
	UP_LEFT(SimpleCommand.UP, SimpleCommand.LEFT), UP_RIGHT(SimpleCommand.UP, SimpleCommand.RIGHT),
	PUNCH_N_KICK(SimpleCommand.PUNCH, SimpleCommand.KICK);
	
	private SimpleCommand c1, c2;
	
	private ComplexeCommand(SimpleCommand c1, SimpleCommand c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public SimpleCommand getC1() {
		return c1;
	}
	
	public SimpleCommand getC2() {
		return c2;
	}
	
	@Override
	public String toString() {
		return c1 + "+" + c2;
	}
}
