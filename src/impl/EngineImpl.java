package impl;

import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public class EngineImpl implements EngineService {
	private int width, height;
	private CharacterService[] characters;
	private PlayerService[] players;



	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		width = w;
		height = h;
		players = new PlayerService[]{p1, p2};
		characters = new CharacterService[2];
		
		
		return this;
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public CharacterService character(int i) {
		// TODO Auto-generated method stub
		return characters[i];
	}

	@Override
	public PlayerService player(int i) {
		return players[i];
	}

	@Override
	public boolean gameOver() {
		return characters[0].dead() || characters[1].dead();
	}

	@Override
	public EngineService step(SimpleCommand c1, SimpleCommand c2) {
		interpretCommand(c1, characters[0]);
		interpretCommand(c2, characters[1]);
		
		return this;
	}
	
	private void interpretCommand(SimpleCommand c, CharacterService character) {
		switch (c) {
		case LEFT:
			character.moveLeft();
			break;
		case RIGHT:
			character.moveRight();
			break;
		case NEUTRAL:
			// Do stuff
			break;
		}
	}

}
