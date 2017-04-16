package contract;

import interfaceservice.Hitbox;
import contract.decorator.HitboxDecorator;
import contract.errors.PostConditionError;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(Hitbox service) {
		super(service);
	}

	
	public void checkInvariant() {
		// Collision status
		// TODO
	
	}
	
	@Override
	public Hitbox init(int x, int y) {
		// No pre
		
		super.init(x, y);

		// Post-init invariant
		checkInvariant();
		
		// \post PositionX(init(x, y)) = x
		if (!(x == positionX()))
			throw new PostConditionError("x != positionX");
		// \post PositionY(init(x, y)) = y
		if (!(y == positionY()))
			throw new PostConditionError("y != positionY");
		
		return this;
	}
	
	
	@Override
	public Hitbox moveTo(int x, int y) {
		// No pre
		
		// pre invariant
		checkInvariant();
		
		
		super.moveTo(x, y);
		
		// post invariant
		checkInvariant();
		
		// \post : PositionX(MoveTo(h, x, y)) = x
		if (!(x == positionX()))
			throw new PostConditionError("x != positionX");
		// \post : PositionY(MoveTo(h, x, y)) = y
		if (!(y == positionY()))
			throw new PostConditionError("y != positionY");
		// \post : \exists u, v, BelongsTo(MoveTo(h, x, y), u, v) = 
		//							BelongsTo(h, u-(x-PositionX(h)), v-(y-PositionY(h))
		// TODO
		
		return this;
	}
	
}
