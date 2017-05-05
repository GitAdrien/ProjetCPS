package contract;

import interfaceservice.HitboxService;
import contract.decorator.HitboxDecorator;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;

public class HitboxContract extends HitboxDecorator {

	public HitboxContract(HitboxService service) {
		super(service);
	}


	public void checkInvariant() {
		//On ne peut rien tester
	}

	@Override
	public HitboxService init(int x, int y, int width, int height) { //TODO: service modifié!!!
		// \pre init(x, y, w, h) \require w > 0 \and h > 0 \and x >= 0 \and y >= 0 
		if (!(width > 0 && height > 0 && x >= 0 && y >= 0))
			throw new PreConditionError("(width or height <= 0)or (x or y < 0)");


		super.init(x, y, width, height);

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
		//  pre moveTo(x, y) \require x >= 0 \and y >= 0
		if(!(x >= 0 && y>=0))
			throw new PreConditionError("x or y < 0");
		// pre invariant
		checkInvariant();

		boolean belongsTo_centre_at_pre = belongsTo(positionX(), positionY());
		boolean belongsTo_centre_100_at_pre = belongsTo(positionX() +100, positionY() +100);
		boolean belongsTo_abs_at_pre = belongsTo(300, 0);
		int positionX_at_pre = positionX();
		int positionY_at_pre = positionY();

		super.moveTo(x, y);

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

		
		return this;
	}

	@Override
	public HitboxService setWidth(int width) {
		// \pre  : setWidth(hb, w) \require w > 0
		if (!(width > 0))
			throw new PreConditionError("width <= 0");

		// pre invariant
		checkInvariant();

		super.setWidth(width);

		// post invariant
		checkInvariant();

		// \post : width(setWidth(hb, w)) = w
		if (!(width() == width))
			throw new PostConditionError("width != w");
		
		return this;
	}

	@Override
	public HitboxService setHeight(int height) {
		// \pre  : setHeight(hb, h) \require h > 0
		if (!(height > 0))
			throw new PreConditionError("height <= 0");

		// pre invariant
		checkInvariant();

		super.setHeight(height);
		
		// post invariant
		checkInvariant();

		// \post : width(setHeight(hb, h)) = h
		if (!(height() == height))
			throw new PostConditionError("height != h");
		
		return this;
	}

	
	@Override
	public HitboxService copy() {
		super.copy();
		int x_at_pre = positionX();
		int y_at_pre = positionY();
		int w_at_pre = width();
		int h_at_pre = height();
		checkInvariant();
		// \post : positionX(h) = positionX(copy(h))
		if(!(x_at_pre == positionX()))
			throw new PostConditionError("positionX(h) != positionX(copy(h))");
		
		// \post : positionY(h) = positionY(copy(h))
		if(!(y_at_pre == positionY()))
			throw new PostConditionError("positionX(h) != positionX(copy(h))");

		// \post : width(h) = width(copy(h))
		if(!(w_at_pre == width()))
			throw new PostConditionError("positionX(h) != positionX(copy(h))");

		// \post : height(h) = height(copy(h))
		if(!(h_at_pre == height()))
			throw new PostConditionError("positionX(h) != positionX(copy(h))");
		return this;
	}
}
