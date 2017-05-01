package enums.attack;

import enums.AttackCommand;
import enums.Command;

public enum SimpleAttackCommand implements AttackCommand {
	PUNCH("punch", 3), KICK("kick", 3), NONE("none", 0);
	
	private String trigger;
	private int priority;
	
	
	private SimpleAttackCommand(String trigger, int priority) {
		this.trigger = trigger;
		this.priority = priority;
	}
	
	public static SimpleAttackCommand parseString(String s) {
		for (SimpleAttackCommand sc : SimpleAttackCommand.values()) {
			if (s.toLowerCase().equals(sc.trigger))
				return sc;
		}
		
		return null;
	}
	
	
	public int getPriority() {
		return priority;
	}
	
	@Override
	public String toString() {
		return trigger;
	}
}
