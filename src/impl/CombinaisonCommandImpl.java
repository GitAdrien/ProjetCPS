package impl;

import java.util.ArrayList;
import java.util.List;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CombinaisonCommandService;

public class CombinaisonCommandImpl implements CombinaisonCommandService {
	private int player;
	private List<SimpleCommand> commands;
	private List<Command> complexeCommands;
	
	
	@Override
	public CombinaisonCommandService init(int p) {
		player = p;
		commands = new ArrayList<>();
		complexeCommands = new ArrayList<>();
		
		return this;
	}

	@Override
	public List<SimpleCommand> commands() {
		return commands;
	}

	@Override
	public List<Command> complexeCommands() {
		return complexeCommands;
	}

	@Override
	public int player() {
		return player;
	}


	@Override
	public void parseCommands() {
		// TODO 
	}

	@Override
	public void addCommand(SimpleCommand c) {
		commands.add(c);
	}

}
