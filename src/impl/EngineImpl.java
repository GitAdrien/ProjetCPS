package impl;

import java.util.Observable;

import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public class EngineImpl extends Observable implements EngineService {
	private static int CHAR_LIFE = 100;
	private static int CHAR_SPEED = 15;
	private static int CHAR_H_WIDTH = 50;
	private static int CHAR_H_HEIGHT = 100;
	
	
	private int width, height;
	private CharacterService[] characters;
	private PlayerService[] players;



	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		width = w;
		height = h;
		// TODO s: espace entre les deux joueurs au départ
		players = new PlayerService[]{p1, p2};
		characters = new CharacterService[2];
		
		characters[0] = new CharacterImpl();
		characters[1] = new CharacterImpl();
		
		characters[0].init(CHAR_LIFE, CHAR_SPEED, true, this); // Coté gauche
		characters[1].init(CHAR_LIFE, CHAR_SPEED, false, this); // Coté droit

		
		
		
		int middle = w / 2;
		
		characters[0].charBox().init((middle - (s/2)), h - CHAR_H_HEIGHT, CHAR_H_WIDTH, CHAR_H_HEIGHT);
		characters[1].charBox().init((middle + (s/2)), h - CHAR_H_HEIGHT, CHAR_H_WIDTH, CHAR_H_HEIGHT);
		
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
		characters[0].step(c1);
		characters[1].step(c2);
		
		if (c1 != SimpleCommand.NEUTRAL || c2 != SimpleCommand.NEUTRAL) {
			setChanged();
			notifyObservers();
		}

		return this;
	}
	

}
