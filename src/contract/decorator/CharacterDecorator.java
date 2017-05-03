package contract.decorator;

import java.util.List;

import enums.Command;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class CharacterDecorator implements CharacterService {
	private CharacterService delegate;

	public CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int positionX() {
		return delegate.positionX();
	}

	@Override
	public int positionY() {
		return delegate.positionY();
	}

	@Override
	public EngineService engine() {
		return delegate.engine();
	}

	@Override
	public HitboxService charBox() {
		return delegate.charBox();
	}

	@Override
	public int life() {
		return delegate.life();
	}

	@Override
	public int speed() {
		return delegate.speed();
	}

	@Override
	public boolean faceRight() {
		return delegate.faceRight();
	}

	@Override
	public boolean dead() {
		return delegate.dead();
	}


	@Override
	public CharacterService moveLeft() {
		return delegate.moveLeft();
	}

	@Override
	public CharacterService moveRight() {
		return delegate.moveRight();
	}

	@Override
	public CharacterService switchSide() {
		return delegate.switchSide();
	}

	@Override
	public List<TechnicService> technics() {
		return delegate.technics();
	}

	@Override
	public CharacterService step(Command com) {
		return delegate.step(com);
	}

	@Override
	public void addTechnic(TechnicService t) {
		delegate.addTechnic(t);
	}

	@Override
	public boolean crouched() {
		return delegate.crouched();
	}

	@Override
	public CharacterService crouch() {
		return delegate.crouch();
	}

	@Override
	public CharacterService standUp() {
		return delegate.standUp();
	}

	@Override
	public boolean stunned() {
		return delegate.stunned();
	}

	@Override
	public boolean usingTechnic() {
		return delegate.usingTechnic();
	}

	@Override
	public HitboxService currentTechnicHitbox() {
		return delegate.currentTechnicHitbox();
	}

	@Override
	public CharacterService useTechnic(List<Command> commands) {
		return delegate.useTechnic(commands);
	}

	@Override
	public CharacterService takeDamages(int amount, int stun) {
		return delegate.takeDamages(amount, stun);
	}

	@Override
	public int gravity() {
		return delegate.gravity();
	}

	@Override
	public int jumpSpeed() {
		return delegate.jumpSpeed();
	}

	@Override
	public boolean jumping() {
		return delegate.jumping();
	}

	@Override
	public CharacterService init(int l, int s, int g, int js, boolean f, EngineService e) {
		return delegate.init(l, s, g, js, f, e);
	}

	@Override
	public CharacterService jump(int direction) {
		return delegate.jump(direction);
	}

	@Override
	public CharacterService bumpLeft() {
		return delegate.bumpLeft();
	}

	@Override
	public CharacterService bumpRight() {
		return delegate.bumpRight();
	}

	@Override
	public int maxLife() {
		return delegate.maxLife();
	}
	
	
}
