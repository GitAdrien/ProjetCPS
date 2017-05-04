package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import enums.AttackCommand;
import enums.Command;
import enums.direction.ComplexeDirectionCommand;
import enums.direction.SimpleDirectionCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class CharacterImpl extends Observable implements CharacterService {
	private final static int JUMP_NO_DIR = 0;
	private final static int JUMP_LEFT = -1;
	private final static int JUMP_RIGHT = 1;
	
	private EngineService engine;
	private HitboxService hitbox;
	private int speed;
	private boolean faceRight;
	private ArrayList<TechnicService> technics;

	// Life
	private int maxLife;
	private int life;
	
	// State
	private boolean isCrouched;
	private boolean isUsingTechnic;
	private boolean isStunned;
	private boolean isJumping;
	private boolean isBlocking;
	
	// Tech
	private int techStart;
	private TechnicService activeTechnic;
	private HitboxService currentTechnicHitbox;
	private boolean hit;
	private int lastSum;

	// Opponent
	private CharacterService opponent;
	private HitboxService opponentHitbox;

	// Stun
	private int stunStart;
	private int stunDuration;
	
	
	// Jump
	private int gravity;
	private int jumpSpeed;
	private int jumpForce_Y;
	private int jumpDirection_X;
	

	
	@Override
	public CharacterService init(int l, int s, int g, int js, boolean f, EngineService e) {
		life = l;
		maxLife = l;
		
		speed = s;
		faceRight = f;
		engine = e;
		hitbox = new HitboxImpl();
		technics = new ArrayList<>();
		opponent = null;
		opponentHitbox = null;
		isCrouched = false;

		hit = false;
		currentTechnicHitbox = null;
		lastSum = 0;
		
		stunStart = 0;
		stunDuration = 0;
		
		isJumping = false;
		gravity = g;
		jumpDirection_X = 0;
		jumpForce_Y = 0;
		jumpSpeed = js;
		
		isBlocking = false;
		
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
			return Integer.compare(t2.commands().size(), t1.commands().size());
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
			case UP:
				jump(JUMP_NO_DIR);
				break;
			default:
				Logger.getAnonymousLogger().log(Level.INFO, com + " is not implemented yet.");
				break;
			} 
			
			if (faceRight && (SimpleDirectionCommand)com == SimpleDirectionCommand.LEFT)
				block();
			else if (!faceRight && (SimpleDirectionCommand)com == SimpleDirectionCommand.RIGHT)
				block();
			else 
				isBlocking = false;
			
		} else if (com instanceof ComplexeDirectionCommand) {
			switch ((ComplexeDirectionCommand)com) {
			case DOWN_LEFT:
				crouch();
				break;
			case DOWN_RIGHT:
				crouch();
				break;
			case UP_LEFT:
				jump(JUMP_LEFT);
				break;
			case UP_RIGHT:
				jump(JUMP_RIGHT);
				break;
			default:
				Logger.getAnonymousLogger().log(Level.INFO, com + " is not implemented yet.");
				break;
			}
			
			
			ComplexeDirectionCommand cdc = (ComplexeDirectionCommand) com;
			
			if (faceRight && (cdc.getC1() == SimpleDirectionCommand.LEFT || cdc.getC2() == SimpleDirectionCommand.LEFT))
				block();
			else if (!faceRight && (cdc.getC1() == SimpleDirectionCommand.RIGHT || cdc.getC2() == SimpleDirectionCommand.RIGHT))
				block();
			else
				isBlocking = false;
		}

		setChanged();
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
		return isCrouched || isStunned || isUsingTechnic || isJumping;
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
			
			if (t.commands().size() > commands.size())
				continue;
			
			tmpCmd.subList(0, t.commands().size());

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
		System.out.println("ligne 393");

		if (tech != null) {
			
			
			int sum = 0;
			
			for (Command command : commands) {
				if (command instanceof AttackCommand)
					sum++;
			}
			System.out.println(lastSum);
			System.out.println(sum);
			System.out.println(engine.frameCounter().difference(techStart));
			System.out.println(engine.player(0).window());
			if (lastSum != sum || (lastSum == sum && engine.frameCounter().difference(techStart) > engine.player(0).window())) {
				System.out.println("est-ont passé?");
				isUsingTechnic = true;
				activeTechnic = tech;
				techStart = engine.frameCounter().frame();
				currentTechnicHitbox = tech.hitbox().copy();
				
				
				lastSum = sum;
				
				refreshTechHitBox();
				setChanged();
			}
			
		}
		
		
		return this;
	}
	
	private void refreshTechHitBox() {
		if (!isUsingTechnic)
			return;
		
		if (faceRight) {
			currentTechnicHitbox.moveTo(hitbox.positionX() + (hitbox.width()/2),
					hitbox.positionY() + activeTechnic.hitbox().positionY());
		} else {
			currentTechnicHitbox.moveTo(hitbox.positionX() - (currentTechnicHitbox.width() - (hitbox.width()/2)),
					hitbox.positionY() + activeTechnic.hitbox().positionY());
		}
		setChanged();
	}

	private void checkTechValidity() {
		if (!isUsingTechnic)
			return;

		int dif = engine.frameCounter().difference(techStart);

		if (dif > activeTechnic.frame()) {
			isUsingTechnic = false;
			hit = false;
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
	
	private void checkJump() {
		if (!isJumping)
			return;
		
		defineOpponent();
		
		HitboxService nextHitbox = hitbox.copy();
		int x, y;
		nextHitbox.moveTo(hitbox.positionX() + jumpDirection_X, hitbox.positionY() - jumpForce_Y);

		x = nextHitbox.positionX();
		y = nextHitbox.positionY();
		
		if (nextHitbox.collidesWith(opponentHitbox)) {
			if (faceRight) {
				bumpRight();
				opponent.bumpLeft();
			} else {
				bumpLeft();
				opponent.bumpRight();
			}
		}
		
		if (y <= 0) {
			jumpForce_Y = 0;
			y = 0;
		} else if (y + hitbox.height() >= engine.height()) {
			isJumping = false;
			jumpDirection_X = 0;
			jumpForce_Y = 0;
			y = engine.height() - hitbox.height();
		}
		
		if (x <= 0) {
			x = 0;
		} else if (x + hitbox.width() >= engine.width()) {
			x = engine.width() - hitbox.width();
		}
		
		nextHitbox.moveTo(x, y);

		if (nextHitbox.collidesWith(opponentHitbox)) {
			y = hitbox.positionY();
			x = hitbox.positionX();
			jumpDirection_X = 0;
		} else {
			jumpForce_Y -= gravity;
		}
		
		hitbox.moveTo(x, y);
		
		refreshTechHitBox();
		
		setChanged();
		
	}
	
	private void checkDirection() {
		defineOpponent();
		
		if (hitbox.positionX() > opponentHitbox.positionX()) {
			faceRight = false;
		} else {
			faceRight = true;
		}
			
		
	}
	
	@Override
	public HitboxService currentTechnicHitbox() {
		return currentTechnicHitbox;
	}

	@Override
	public CharacterService takeDamages(int amount, int stun) {
		if (!isBlocking)
			life -= amount;
		
		isStunned = true;
		stunStart = engine.frameCounter().frame();
		stunDuration = stun;
		return null;
	}

	@Override
	public boolean jumping() {
		return isJumping;
	}

	@Override
	public CharacterService jump(int direction) {
		if (isJumping)
			return this;
		
		isJumping = true;
		jumpForce_Y = jumpSpeed;
		
		switch (direction) {
		case -1:
			jumpDirection_X = -speed;
			break;
		case 0:
			jumpDirection_X = 0;
			break;
		case 1:
			jumpDirection_X = speed;
			break;
		default:
			Logger.getGlobal().log(Level.WARNING, "Invalid jump direction : " + direction);
			break;
		}
		
		return this;
	}

	@Override
	public int gravity() {
		return gravity;
	}

	@Override
	public int jumpSpeed() {
		return jumpSpeed;
	}

	@Override
	public CharacterService bumpLeft() {
		int x = hitbox.positionX() + (speed/2);
		
		if (x + hitbox.width() <= engine.width())
			hitbox.moveTo(x, hitbox.positionY());
		
		return this;
	}

	@Override
	public CharacterService bumpRight() {
		int x = hitbox.positionX() - (speed/2);
		
		if (x >= 0)
			hitbox.moveTo(x, hitbox.positionY());

		return this;
	}

	@Override
	public int maxLife() {
		return maxLife;
	}

	@Override
	public CharacterService stepState() {
		checkJump();
		checkStun();
		checkTechValidity();
		checkHit();
		checkDirection();
		
		return this;
	}

	@Override
	public boolean blocking() {
		return isBlocking;
	}

	@Override
	public CharacterService block() {
		if (!isStunned || !isUsingTechnic)
			isBlocking = true;
		else
			isBlocking = false;
		
		setChanged();
		
		return this;
	}

}
