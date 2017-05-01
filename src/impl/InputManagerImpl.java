package impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import enums.Command;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;
import interfaceservice.InputManagerService;

public class InputManagerImpl implements InputManagerService {
	private Map<SimpleDirectionCommand, Boolean> directionCommands;
	private Map<SimpleAttackCommand, Boolean> attackCommands;
	
	@Override
	public InputManagerService init() {
		directionCommands = new HashMap<>();
		attackCommands = new HashMap<>();
		Arrays.stream(SimpleDirectionCommand.values()).forEach(cmd -> directionCommands.put(cmd, false));
		Arrays.stream(SimpleAttackCommand.values()).forEach(cmd -> attackCommands.put(cmd, false));
		
		
		return this;
	}

	@Override
	public void setPressed(Command cmd) {
		if (cmd instanceof SimpleDirectionCommand)
			directionCommands.put((SimpleDirectionCommand) cmd, true);
		else
			attackCommands.put((SimpleAttackCommand) cmd, true);
	}

	@Override
	public void setReleased(Command cmd) {
		if (cmd instanceof SimpleDirectionCommand)
			directionCommands.put((SimpleDirectionCommand) cmd, false);
		else
			attackCommands.put((SimpleAttackCommand) cmd, false);
	}

	@Override
	public boolean isPressed(Command cmd) {
		if (cmd instanceof SimpleDirectionCommand)
			return directionCommands.get(cmd);
		else
			return attackCommands.get(cmd);
		
		
	}

}
