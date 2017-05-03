package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import enums.Command;
import enums.direction.ComplexeDirectionCommand;
import enums.direction.SimpleDirectionCommand;
import factory.CharacterFactory;
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

	// State
	private boolean isCrouched;
	private boolean isUsingTechnic;
	private boolean isStunned;

	// Tech
	private int techStart;
	private TechnicService activeTechnic;
	private HitboxService currentTechnicHitbox;
	private boolean hit;

	// Opponent
	private CharacterService opponent;
	private HitboxService opponentHitbox;

	// Stun
	private int stunStart;
	private int stunDuration;
	

	@Override
	public CharacterService init(int l, int s, boolean f, EngineService e) {
		life = l;
		speed = s;
		faceRight = f;
		engine = e;
		hitbox = new HitboxImpl(); // la hitbox n'est pas initialisé
		technics = new ArrayList<>();
		opponent = null;
		opponentHitbox = null;
		isCrouched = false;
		hit = false;
		currentTechnicHitbox = null;
		stunStart = 0;
		stunDuration = 0;
		
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
		if (isMouvementDisabled())
			return this;

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
		if (isMouvementDisabled())
			return this;

		if (opponentHitbox == null)
			defineOpponent();

		int x = positionX();

		HitboxService cpy = hitbox.copy();
		cpy.moveTo(positionX() + speed, cpy.positionY());


		if (positionX() + hitbox.width() + speed > engine.width())
			x = engine.width() - hitbox.width();
		else if (!opponentHitbox.collidesWith(cpy)) 
			x = positionX() + speed;

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
		technics.sort((t1, t2) -> {
			return Integer.compare(t1.commands().size(), t2.commands().size());
		});
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
		checkStun();
		checkTechValidity();
		checkHit();
		

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

	private void checkHit() {
		if (isUsingTechnic && !hit) {
			defineOpponent();
			if (currentTechnicHitbox.collidesWith(opponentHitbox)) {
				hit = true;
				opponent.takeDamages(activeTechnic.damage(), activeTechnic.stun());
			}
			
		}
	
	}

	private void handleCrouch() {
		if (isCrouched) {
			standUp();
		}
	}


	private void defineOpponent() {
		if (engine.character(0).charBox().equalsTo(hitbox)) {
			opponent = engine.character(1);
			opponentHitbox = engine.character(1).charBox();
		} else {
			opponent = engine.character(0);
			opponentHitbox = engine.character(0).charBox();
		}

	}

	@Override
	public boolean stunned() {
		return isStunned;
	}

	@Override
	public boolean usingTechnic() {
		return isUsingTechnic;
	}

	private boolean isMouvementDisabled() {
		return isCrouched || isStunned || isUsingTechnic;
	}

	@Override
	public CharacterService useTechnic(List<Command> commands) {
		if (isUsingTechnic || isStunned) {
			return this;
		}
		
		// La liste est normalement triée.
		TechnicService tech = null;
		boolean validTechnic;
		List<Command> tmpCmd;

		for (TechnicService t : technics) {
			tmpCmd = new ArrayList<Command>(commands);
			tmpCmd.subList(0, t.commands().size()-1);

			validTechnic = true;

			for (Command command : t.commands()) {
				if (tmpCmd.contains(command)) {
					tmpCmd.remove(command);
				} else {
					validTechnic = false;
					break;
				}
			}

			if (validTechnic) {
				tech = t;
				break;
			}
		}


		if (tech != null) {
			isUsingTechnic = true;
			activeTechnic = tech;
			techStart = engine.frameCounter().frame();
			currentTechnicHitbox = tech.hitbox().copy();

			if (faceRight) {
				currentTechnicHitbox.moveTo(hitbox.positionX() + (hitbox.width()/2),
						hitbox.positionY() + currentTechnicHitbox.positionY());
			} else {
				currentTechnicHitbox.moveTo(hitbox.positionX() - (currentTechnicHitbox.width() - (hitbox.width()/2)),
						hitbox.positionY() + currentTechnicHitbox.positionY());
			}
			setChanged();
		}
		
		
		return this;
	}

	private void checkTechValidity() {
		if (!isUsingTechnic)
			return;

		int dif = engine.frameCounter().difference(techStart);

		if (dif > activeTechnic.frame()) {
			activeTechnic = null;
			isUsingTechnic = false;
			hit = false;
			currentTechnicHitbox = null;
			setChanged();
		}

	}

	private void checkStun() {
		if (isStunned) {
			int dif = engine.frameCounter().difference(stunStart);
			
			if (dif > stunDuration) {
				isStunned = false;
				setChanged();
			}
			
		}
	}
	
	@Override
	public HitboxService currentTechnicHitbox() {
		return currentTechnicHitbox;
	}

	@Override
	public CharacterService takeDamages(int amount, int stun) {
		life -= amount;
		isStunned = true;
		stunStart = engine.frameCounter().frame();
		stunDuration = stun;
		return null;
	}
	

}
