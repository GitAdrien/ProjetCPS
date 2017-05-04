package enums.direction;

import enums.DirectionCommand;

public enum SimpleDirectionCommand implements DirectionCommand {
	LEFT("left", 2), RIGHT("right", 2), NEUTRAL("neutral", 0), DOWN("down", 2), UP("up", 2);
	
	private String trigger;
	private int priority;
	
	
	private SimpleDirectionCommand(String trigger, int priority) {
		this.trigger = trigger;
		this.priority = priority;
	}
	
	public static SimpleDirectionCommand parseString(String s) {
		for (SimpleDirectionCommand sc : SimpleDirectionCommand.values()) {
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
