package impl;

import java.util.ArrayList;
import java.util.List;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.PlayerService;

public class PlayerImpl implements PlayerService {
	private int window;
	private CharacterService character;
	private List<Command> commands;
	private SimpleCommand lastCommand;
	
	@Override
	public PlayerService init(int w, CharacterService c) {
		commands = new ArrayList<>();
		lastCommand = SimpleCommand.NEUTRAL;
		
		character = c;
		
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
	public SimpleCommand getLastCommand() {
		SimpleCommand result = lastCommand;
		
		lastCommand = SimpleCommand.NEUTRAL;
		return result;
	}
	
	@Override
	public void addCommand(SimpleCommand c) {
		lastCommand = c;
		
		//TODO s'occuper de la window.
	}

}
