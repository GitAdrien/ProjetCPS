package impl;

import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;

public class CharacterImpl implements CharacterService {
	private int life;
	private EngineService engine;
	private HitboxService hitbox;
	private int speed;
	private boolean faceRight;
	
	
	@Override
	public CharacterService init(int l, int s, boolean f, EngineService e) {
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
		hitbox = new HitboxImpl();
		
		return this;
	}
	
	@Override
	public int positionX() {

		return hitbox.positionX();
	}

	@Override
	public int positionY() {
		return hitbox.positionY();
	}

	@Override
	public EngineService engine() {
		return engine;
	}

	@Override
	public HitboxService charBox() {
		return hitbox;
	}

	@Override
	public int life() {
		return life;
	}

	@Override
	public int speed() {
		return speed;
	}

	@Override
	public boolean faceRight() {
		return faceRight;
	}

	@Override
	public boolean dead() {
		return life == 0;
	}

	@Override
	public CharacterService moveLeft() {
		int x;
		
		if (positionX() - speed < 0)
			x = 0;
		else 
			x = positionX() - speed;
		
		
		hitbox.moveTo(x, positionY());
		
		return this;
	}

	@Override
	public CharacterService moveRight() {
		int x;
		
		if (positionX() + speed > engine.width())
			x = engine.width();
		else 
			x = positionX() + speed;
		
		
		hitbox.moveTo(x, positionY());
		
		return this;
	}

	@Override
	public CharacterService switchSide() {
		faceRight = !faceRight;
		return this;
	}

}
