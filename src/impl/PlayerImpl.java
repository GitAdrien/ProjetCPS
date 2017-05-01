package impl;

import java.util.ArrayList;
import java.util.List;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;

public class PlayerImpl implements PlayerService {
	private int window;
	private CharacterService character;
	private List<Command> commands;
	private InputManagerService inputManager;

	private int frameCounter;
	private int lastInput;

	@Override
	public PlayerService init(int w, CharacterService c, InputManagerService im) {
		commands = new ArrayList<>();
		window = w;
		character = new CharacterImpl();
		character = c;
		inputManager = im;

		frameCounter = 0;
		lastInput = 0;

		return this;
	}

	@Override
	public int window() {
		return window;
	}

	@Override
	public List<Command> commandsWithinWindow() {
		return commands;
	}

	@Override
	public CharacterService character() {
		return character;
	}

	@Override
	public SimpleCommand getActiveCommand() {
		frameCounter = (frameCounter + 1) % Integer.MAX_VALUE;

		SimpleCommand result = SimpleCommand.NEUTRAL;

		for (SimpleCommand cm : SimpleCommand.values()) {
			if (inputManager.isPressed(cm) && cm.getPriority() > result.getPriority()) {
				result = cm;
			}
		}
		if (!result.equals(SimpleCommand.NEUTRAL)) {
			if ((Integer.MAX_VALUE - lastInput) + frameCounter > window) {
				commands.clear();
			}
			
			lastInput = frameCounter;
			commands.add(0, result);
		}
		return result;
	}

	@Override
	public InputManagerService inputManager() {
		return inputManager;
	}

}
