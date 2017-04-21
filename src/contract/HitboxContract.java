package contract;

import java.util.Random;

import interfaceservice.HitboxService;
import contract.decorator.HitboxDecorator;
import contract.errors.PostConditionError;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService service) {
		super(service);
	}

	
	public void checkInvariant() {
		//On ne peut rien tester
	}
	
	@Override
	public HitboxService init(int x, int y) {
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
	public HitboxService moveTo(int x, int y) {
		// No pre
		
		// pre invariant
		checkInvariant();
		
		
		super.moveTo(x, y);
		boolean belongsTo_centre_at_pre = belongsTo(positionX(), positionY());
		boolean belongsTo_centre_100_at_pre = belongsTo(positionX() +100, positionY() +100);
		boolean belongsTo_abs_at_pre = belongsTo(300, 0);
		int positionX_at_pre = positionX();
		int positionY_at_pre = positionY();
		// post invariant
		checkInvariant();
		
		// \post : PositionX(MoveTo(h, x, y)) = x
		if (!(x == positionX()))
			throw new PostConditionError("x != positionX");
		// \post : PositionY(MoveTo(h, x, y)) = y
		if (!(y == positionY()))
			throw new PostConditionError("y != positionY");
		// \post : u, v, BelongsTo(MoveTo(h, x, y), u, v) = 
		//							BelongsTo(h, u-(x-PositionX(h)), v-(y-PositionY(h))

		if(! (belongsTo(positionX(), positionY()) == belongsTo_centre_at_pre))
			throw new PostConditionError("belongsTo(positionX(), positionY()) != belongsTo_centre_at_pre");
		
		if(!(belongsTo(positionX() +100, positionY() + 100) == belongsTo_centre_100_at_pre))
			throw new PostConditionError("belongsTo(positionX() +100, positionY() + 100) == belongsTo_centre_100_at_pre");
		
		if(! (belongsTo(300 +(x - positionX_at_pre), 0 + (y - positionY_at_pre)) == belongsTo_abs_at_pre))
			throw new PostConditionError("belongsTo(300 +(x - positionX_at_pre), 0 + (y - positionY_at_pre)) != belongsTo_abs_at_pre");
		// Vérifie si il y a collision.
		// TODO
		
		return this;
	}
	
}
