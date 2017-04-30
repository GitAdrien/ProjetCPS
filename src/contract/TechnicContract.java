package contract;

import java.util.List;

import interfaceservice.HitboxService;
import interfaceservice.TechnicService;
import contract.decorator.TechnicDecorator;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.Command;

public class TechnicContract extends TechnicDecorator {

	public TechnicContract(TechnicService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		//TODO
	}
	
	@Override
	public TechnicService init(String n, List<Command> c, int d, HitboxService h) {
		// \pre  : n != \empty \and c != \empty \and d >= 0
		if (!(n != null || !n.equals("")))
			throw new PreConditionError("name is empty");
		if (!(!c.isEmpty()))
			throw new PreConditionError("c is empty");
		if (!(d >= 0))
			throw new PreConditionError("d < 0");
		

		// Pre invariants
		checkInvariant();
		
		super.init(n, c, d, h);
		
		
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
