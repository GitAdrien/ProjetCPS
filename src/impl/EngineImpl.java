package impl;

import java.util.Observable;

import enums.AttackCommand;
import enums.DirectionCommand;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;
import factory.CharacterFactory;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public class EngineImpl extends Observable implements EngineService {
	private int width, height;
	private CharacterService[] characters;
	private PlayerService[] players;



	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		width = w;
		height = h;

		players = new PlayerService[]{p1, p2};
		characters = new CharacterService[2];
		
		characters[0] = CharacterFactory.newCharacterOnLeftSide(this, s);
		characters[1] = CharacterFactory.newCharacterOnRightSide(this, s);
		
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
	public EngineService step() {
		stepCharacter(0);
		stepCharacter(1);
		
		notifyObservers();

		return this;
	}
	
	
	private void stepCharacter(int p) {
		DirectionCommand d;
		AttackCommand a;
		
		d = players[p].getActiveDirection();
		a = players[p].getActiveAttack();
		
		characters[p].step(d);
		characters[p].step(a);
		
	}

}
