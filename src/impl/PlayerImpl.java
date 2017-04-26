package impl;

import java.util.List;

import enums.Command;
import interfaceservice.CharacterService;
import interfaceservice.PlayerService;

public class PlayerImpl implements PlayerService {
	private int window;
	
	
	
	@Override
	public PlayerService init(int w, CharacterService c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int window() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Command> commandsWithinWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharacterService character() {
		// TODO Auto-generated method stub
		return null;
	}


}
