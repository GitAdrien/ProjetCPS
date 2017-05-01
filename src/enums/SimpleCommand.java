package enums;

public enum SimpleCommand implements Command {
	LEFT("left", 2), RIGHT("right", 2), NEUTRAL("neutral", 0), DOWN("down", 1), UP("up", 1),
	PUNCH("punch", 3), KICK("kick", 3);
	
	private String trigger;
	private int priority;
	
	
	private SimpleCommand(String trigger, int priority) {
		this.trigger = trigger;
		this.priority = priority;
	}
	
	public static SimpleCommand parseString(String s) {
		for (SimpleCommand sc : SimpleCommand.values()) {
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
