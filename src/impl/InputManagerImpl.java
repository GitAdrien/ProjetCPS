package impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import enums.SimpleCommand;
import interfaceservice.InputManagerService;

public class InputManagerImpl implements InputManagerService {
	private Map<SimpleCommand, Boolean> commands;
	
	
	@Override
	public InputManagerService init() {
		commands = new HashMap<>();
		Arrays.stream(SimpleCommand.values()).forEach(cmd -> commands.put(cmd, false));
		
		return this;
	}

	@Override
	public void setPressed(SimpleCommand cmd) {
		commands.put(cmd, true);
	}

	@Override
	public void setReleased(SimpleCommand cmd) {
		commands.put(cmd, false);
	}

	@Override
	public boolean isPressed(SimpleCommand cmd) {
		return commands.get(cmd);
	}

}
