package contract.decorator;

import interfaceservice.Character;
import interfaceservice.Engine;
import interfaceservice.Hitbox;

public class CharacterDecorator implements Character {
	private Character delegate;

	public CharacterDecorator(Character delegate) {
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
	public Engine engine() {
		return delegate.engine();
	}

	@Override
	public Hitbox charBox() {
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
	public Character init(int l, int s, boolean f, Engine e) {
		return delegate.init(l, s, f, e);
	}

	@Override
	public Character moveLeft() {
		return delegate.moveLeft();
	}

	@Override
	public Character moveRight() {
		return delegate.moveRight();
	}

	@Override
	public Character switchSide() {
		return delegate.switchSide();
	}
	
	
}
