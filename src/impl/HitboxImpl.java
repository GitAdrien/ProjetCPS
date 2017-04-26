package impl;

import interfaceservice.HitboxService;

public class HitboxImpl implements HitboxService {
	private int x, y;
	private int width, height;
	

	@Override
	public HitboxService init(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		return this;
	}
	
	@Override
	public int positionX() {
		return x;
	}

	@Override
	public int positionY() {
		return y;
	}

	@Override
	public boolean belongsTo(int x, int y) {
		return x >= this.x && x <= this.x + this.width && 
				y >= this.y && y <= this.y + this.height;
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		return this.x < h.positionX() + h.width() &&
				this.x + this.width > h.positionX () &&
				this.y < h.positionY() + h.height() &&
				this.height + this.y > h.positionY();
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		return this.x == h.positionX() &&
				this.y == h.positionY() &&
				this.width == h.width() &&
				this.height == h.height();
	}


	@Override
	public HitboxService moveTo(int x, int y) {
		this.x = x;
		this.y = y;
		
		return this;
	}


	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}


	@Override
	public HitboxService setWidth(int width) {
		this.width = width;
		return this;
	}

	@Override
	public HitboxService setHeight(int height) {
		this.height = height;
		return this;
	}
	
	@Override
	public HitboxService copy() {
		HitboxImpl result = new HitboxImpl();
		
		result.init(x, y, width, height);
		
		return result;
	}

}
