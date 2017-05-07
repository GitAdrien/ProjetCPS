package contract;

import java.util.List;

import interfaceservice.HitboxService;
import interfaceservice.TechnicService;
import contract.decorator.TechnicDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.Command;

public class TechnicContract extends TechnicDecorator {

	public TechnicContract(TechnicService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		//hitbox(t) != \empty
		if(!(hitbox().width() > 0 && hitbox().height() > 0))
			throw new InvariantError("hitbox is empty");
	}
	
	@Override
	public TechnicService init(String n, List<Command> c, int d, int f, int s, HitboxService h) {
		// \pre  : n != \empty \and c != \empty \and d >= 0
		if (!(n != null))
			throw new PreConditionError("name is empty");
		if (!(!n.equals("")))
			throw new PreConditionError("name is empty");
		if (!(!c.isEmpty()))
			throw new PreConditionError("c is empty");
		if (!(d >= 0))
			throw new PreConditionError("d < 0");
		if (!(f >= 0))
			throw new PreConditionError("f < 0");
		if (!(s >= 0))
			throw new PreConditionError("s < 0");
		

		super.init(n, c, d, f, s, h);
		
		
		// Post invariants
		checkInvariant();
		
		// \post : name(init(n, c, d, h)) = n
		if (!(name().equals(n)))
			throw new PostConditionError("name() != n");
		// \post : commands(init(n, c, d, h)) = c
		if (!(commands().equals(c)))
			throw new PostConditionError("commands() != c");
		// \post : damage(init(n, c, d, h)) = d
		if (!(damage() == d))
			throw new PostConditionError("dammage() != d");
		// \post : hitbox(init(n, c, d, h)) = h
		if (!(hitbox().equalsTo(h)))
			throw new PostConditionError("hitbox() != h");
		
		
		return this;
	}
	
}
