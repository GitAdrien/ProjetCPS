package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import enums.Command;
import enums.direction.ComplexeDirectionCommand;
import enums.direction.SimpleDirectionCommand;
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
	
	private boolean isCrouched;
	
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
		isCrouched = false;
		
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

		
		if (positionX() + hitbox.width() + speed > engine.width())
			x = engine.width() - hitbox.width();
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
	public boolean crouched() {
		return isCrouched;
	}

	@Override
	public CharacterService crouch() {
		if (!isCrouched) {
			hitbox.moveTo(positionX(), positionY() + hitbox.height()/2);
			hitbox.setHeight(hitbox.height()/2);
			isCrouched = true;
			setChanged();
		}
		
		return this;
	}

	@Override
	public CharacterService standUp() {
		if (isCrouched) {
			hitbox.moveTo(positionX(), positionY() - hitbox.height());
			hitbox.setHeight(hitbox.height()*2);
			isCrouched = false;
			setChanged();
		}
		
		return this;
	}

	
	@Override
	public CharacterService step(Command com) {
		
		if (com instanceof SimpleDirectionCommand) {
			switch ((SimpleDirectionCommand)com) {
			case LEFT :
				handleCrouch();
				moveLeft();
				break;
			case RIGHT :
				handleCrouch();
				moveRight();
				break;
			case NEUTRAL :
				handleCrouch();
				break;
			case DOWN :
				crouch();
				break;
				
			//TODO
				
			default:
				Logger.getAnonymousLogger().log(Level.INFO, com + " is not implemented yet.");
				break;
			} 
		} else if (com instanceof ComplexeDirectionCommand) {
			switch ((ComplexeDirectionCommand)com) {
			case DOWN_LEFT:
				crouch();
				break;
			case DOWN_RIGHT:
				crouch();
				break;
			default:
				Logger.getAnonymousLogger().log(Level.INFO, com + " is not implemented yet.");
				break;
			}
		}
		
		notifyObservers();
		
		return this;
	}
	
	private void handleCrouch() {
		if (isCrouched) {
			standUp();
		}
	}

	
	private void defineOpponent() {
		if (engine.character(0).charBox().equalsTo(hitbox))
			opponentHitbox = engine.character(1).charBox();
		else
			opponentHitbox = engine.character(0).charBox();
		
	}


}
