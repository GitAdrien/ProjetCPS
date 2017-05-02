package contract;

import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.PlayerService;
import contract.decorator.EngineDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;

public class EngineContract extends EngineDecorator {

	public EngineContract(EngineService service) {
		super(service);
	}
	
	public void checkInvariant() {
		// Gameover status
		if (!(gameOver() == (character(0).dead() || character(1).dead()))) 
			throw new InvariantError("Incoherent game over status");
	}

	
	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2, FrameCounterService fc) {
		// \pre : init(h, w, s, p1, p2) \with h > 0 \and s > 0 \and w > s \and p1 != p2 
		if (!(h > 0))
			throw new PreConditionError("h <= 0");
		if (!(s > 0))
			throw new PreConditionError("s <= 0");
		if(!(w > s))
			throw new PreConditionError("w <= s");
		if (!(w > 0))
			throw new PreConditionError("w <= 0");
		if (!(!p1.equals(p2)))
			throw new PreConditionError("p1 == p2");

		
		super.init(h, w, s, p1, p2, fc);

		
		// Post-init invariant
		checkInvariant();
		
		// \post : height(init(h, w, s, p1, p2)) = h
		if (!(h == height()))
			throw new PostConditionError("h != height");
		// \post : width(init(h, s, w, p1, p2) = w
		if (!(w == width()))
			throw new PostConditionError("w != width");
		// \post : player(init(h, s, w, p1, p2), 1) = p1
		if (!(p1.equals(player(0))))
			throw new PostConditionError("p1 != player(0)");
		// \post : player(init(h, s, w, p1, p2), 2) = p2
		if (!(p2.equals(player(1))))
			throw new PostConditionError("p2 != player(1)");
		// \post : Character::positionX(character(init(h, w, s, p1, p2), 1)) = w//2 - s//2
		if (!(character(0).positionX() == (w/2 - s/2))) // XXX wtf
			throw new PostConditionError("p1::positionX != w//2 - s//2");
		// \post : Character::positionX(character(init(h, w, s, p1, p2), 2)) = w//2 + s//2
		if (!(character(1).positionX() == (w/2 + s/2))) // XXX wtf
			throw new PostConditionError("p2::positionX != w//2 + s//2");
		// \post : Character::positionY(character(init(h, w, s, p1, p2), 1)) = 0
		if (!(character(0).positionY() == 0))
			throw new PostConditionError("p1::positionY != 0");
		// \post : Character::positionY(character(init(h, w, s, p1, p2), 2)) = 0
		if (!(character(1).positionY() == 0))
			throw new PostConditionError("p2::positionY != 0");
		// \post : Character::faceRight(char(init(h, w, s, p1, p2), 1))
		if (!(character(0).faceRight()))
			throw new PostConditionError("!p1::faceRight");
		// \post : Character::non(faceRight(char(init(h, w, s, p1, p2), 2)))
		if (!(!character(1).faceRight()))
			throw new PostConditionError("p2::faceRight");
		
		
		return this;
	}
	
	// TODO
}
