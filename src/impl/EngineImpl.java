package impl;

import java.util.Observable;

import contract.CharacterContract;
import contract.decorator.CharacterDecorator;
import enums.AttackCommand;
import enums.DirectionCommand;
import factory.CharacterFactory;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.PlayerService;

public class EngineImpl extends Observable implements EngineService {
	private static int MAX_FRAME = 300000;
	
	private int width, height;
	private CharacterService[] characters;
	private CharacterImpl[] charI;
	private PlayerService[] players;
	private FrameCounterService frameCounter;



	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2, FrameCounterService fc) {
		width = w;
		height = h;

		players = new PlayerService[]{p1, p2};
		characters = new CharacterService[2];
		
		charI = new CharacterImpl[2];
		
		charI[0] = CharacterFactory.newCharacterImplOnLeftSide(this, s);
		charI[1] = CharacterFactory.newCharacterImplOnRightSide(this, s);
		
		characters[0] = new CharacterDecorator(new CharacterContract(charI[0]));
		characters[1] = new CharacterDecorator(new CharacterContract(charI[1]));
		
		fc.init(MAX_FRAME);
		
		frameCounter = fc;
		
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
		
		frameCounter.nextFrame();
		
		
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
		
		characters[p].useTechnic(players[p].commandsWithinWindow());
		
		characters[p].stepState();
		
		
	}

	@Override
	public FrameCounterService frameCounter() {
		return frameCounter;
	}
	
	public CharacterImpl getCharImpl(int c) {
		if (c == 0 || c == 1)
			return charI[c];
		else 
			return null;
	
	}

}
