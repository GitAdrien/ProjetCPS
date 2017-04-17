package impl;

import interfaceservice.HitboxService;

public class HitboxImpl implements HitboxService {
	private int x, y;
	

	@Override
	public HitboxService init(int x, int y) {
		this.x = x;
		this.y = y;
		
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
		// TODO
		return false;
	}

	@Override
	public boolean collidesWith(HitboxService h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equalsTo(HitboxService h) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public HitboxService moveTo(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HitboxService clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
