package enums;

public enum SimpleCommand implements Command {
	LEFT("left"), RIGHT("right"), NEUTRAL("neutral"), DOWN("down"), UP("up"),
	PUNCH("punch"), KICK("kick");
	
	private String trigger;
	
	private SimpleCommand(String trigger) {
		this.trigger = trigger;
	}
	
	public static SimpleCommand parseString(String s) {
		for (SimpleCommand sc : SimpleCommand.values()) {
			if (s.toLowerCase().equals(sc.trigger))
				return sc;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return trigger;
	}
}
