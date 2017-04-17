package contract.decorator;

import interfaceservice.HitboxService;

public class HitboxDecorator implements HitboxService {
	private HitboxService delegate;
	
	public HitboxDecorator(HitboxService delegate) {
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
	public boolean collidesWith(HitboxService h) {
		return delegate.collidesWith(h);
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return delegate.equalsTo(h);
	}

	@Override
	public HitboxService init(int x, int y) {
		return delegate.init(x, y);
	}

	@Override
	public HitboxService moveTo(int x, int y) {
		return delegate.moveTo(x, y);
	}
	
	@Override
	public HitboxService clone() {
		return delegate.clone();
	}
}
