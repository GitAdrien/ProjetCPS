package enums.attack;

import enums.AttackCommand;
import enums.Command;

public enum ComplexeAttackCommand implements AttackCommand {
	PUNCH_N_KICK(SimpleAttackCommand.PUNCH, SimpleAttackCommand.KICK);
	
	private Command c1, c2;
	
	private ComplexeAttackCommand(Command c1, Command c2) {
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
