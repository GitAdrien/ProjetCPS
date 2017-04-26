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
	public HitboxService moveTo(int x, int y) {
		return delegate.moveTo(x, y);
	}
	
	@Override
	public HitboxService copy() {
		return delegate.copy();
	}

	@Override
	public int width() {
		return delegate.width();
	}

	@Override
	public int height() {
		return delegate.height();
	}

	@Override
	public HitboxService init(int x, int y, int width, int height) {
		return delegate.init(x, y, width, height);
	}

	@Override
	public HitboxService setWidth(int width) {
		return delegate.setWidth(width);
	}

	@Override
	public HitboxService setHeight(int height) {
		return delegate.setHeight(height);
	}
}
