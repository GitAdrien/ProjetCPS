package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class CharacterImpl extends Observable implements CharacterService{
	
	private int life;
	private EngineService engine;
	private HitboxService hitbox;
	private int speed;
	private boolean faceRight;
	private ArrayList<TechnicService> technics;
	
	private HitboxService opponentHitbox;
	
	
	@Override
	public CharacterService init(int l, int s, boolean f, EngineService e) {
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
		hitbox = new HitboxImpl();
		technics = new ArrayList<>();
		opponentHitbox = null;
		
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
		if (opponentHitbox == null)
			defineOpponent();
		
		int x = positionX();
		
		
		HitboxService cpy = hitbox.copy();
		cpy.moveTo(positionX() - speed, cpy.positionY());
		
		if (positionX() - speed < 0)
			x = 0;
		else if (!opponentHitbox.collidesWith(cpy)) 
			x = positionX() - speed;
		
		
		hitbox.moveTo(x, positionY());
		
		setChanged();
		
		return this;
	}


	@Override
	public CharacterService moveRight() {
		if (opponentHitbox == null)
			defineOpponent();
		
		int x = positionX();
		
		HitboxService cpy = hitbox.copy();
		cpy.moveTo(positionX() + speed, cpy.positionY());

		
		if (positionX() + speed > engine.width())
			x = engine.width();
		else if (!opponentHitbox.collidesWith(cpy)) 
			x = positionX() + speed;
		
		// TODO bug ici!
		
		hitbox.moveTo(x, positionY());
		
		setChanged();
		
		return this;
	}

	@Override
	public CharacterService switchSide() {
		faceRight = !faceRight;

		setChanged();
		
		return this;
	}

	@Override
	public List<TechnicService> technics() {
		return technics;
	}

	@Override
	public void addTechnic(TechnicService t) {
		technics.add(t);
	}
	
	@Override
	public Character step(Command com) {
		if (com instanceof SimpleCommand) {
			
			switch ((SimpleCommand)com) {
			case LEFT :
				moveLeft();
				Logger.getAnonymousLogger().log(Level.INFO, "Moving left -> x:" + positionX());
				break;
			case RIGHT :
				moveRight();
				Logger.getAnonymousLogger().log(Level.INFO, "Moving right -> x:" + positionY());
				break;
			case NEUTRAL :
//				Logger.getAnonymousLogger().log(Level.INFO, "Neutral");
				break;
				
			//TODO
				
			default:
				Logger.getAnonymousLogger().log(Level.INFO, com + " is not implemented yet.");
				break;
			}
			
		}
		
		return null;
	}

	
	private void defineOpponent() {
		System.out.println("engine : " + engine);
		System.out.println("player : " + engine.player(0));
		System.out.println("character : " + engine.player(0).character());
		
		
		if (engine.character(0).charBox().equalsTo(hitbox))
			opponentHitbox = engine.character(1).charBox();
		else
			opponentHitbox = engine.character(0).charBox();
		
	}

}
