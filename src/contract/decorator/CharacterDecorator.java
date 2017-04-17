package contract.decorator;

import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;

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
	public CharacterService init(int l, int s, boolean f, EngineService e) {
		return delegate.init(l, s, f, e);
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
	
	
}
