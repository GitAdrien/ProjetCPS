package contract.decorator;

import interfaceservice.Hitbox;

public class HitboxDecorator implements Hitbox {
	private Hitbox delegate;
	
	public HitboxDecorator(Hitbox delegate) {
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
	public boolean belongsTo(int a, int b) {
		return delegate.belongsTo(a, b);
	}

	@Override
	public boolean collidesWith(Hitbox h) {
		return delegate.collidesWith(h);
	}

	@Override
	public boolean equalsTo(Hitbox h) {
		return delegate.equalsTo(h);
	}

	@Override
	public Hitbox init(int x, int y) {
		return delegate.init(x, y);
	}

	@Override
	public Hitbox moveTo(int x, int y) {
		return delegate.moveTo(x, y);
	}
}
