package contract;

import interfaceservice.CombinaisonCommandService;
import contract.decorator.CombinaisonCommandDecorator;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.SimpleCommand;

public class CombinaisonCommandContract extends CombinaisonCommandDecorator {

	public CombinaisonCommandContract(CombinaisonCommandService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		// TODO
	}

	@Override
	public CombinaisonCommandService init(int p) {
		// \pre  : p >= 0 \and p < 2
		if (!(p >= 0))
			throw new PreConditionError("p < 0");
		if (!(p < 2))
			throw new PreConditionError("p >= 2");
		
		// Pre invariants
		checkInvariant();
		
		super.init(p);
		
		//Post invariants
		checkInvariant();
		
		// \post : player(init(p)) = p
		if (!(player() == p))
			throw new PostConditionError("player() != p");
		// \post : commands(init(p)) = \empty
		if (!(commands().isEmpty()))
			throw new PostConditionError("commands() is not empty");
		// \post : complexeCommands(init(p)) = \empty
		if (!(complexeCommands().isEmpty()))
			throw new PostConditionError("complexeCommands() is not empty");
		
		return this;
	}
	
	
	@Override
	public void addCommand(SimpleCommand c) {
		
		// Pre invariants
		checkInvariant();
		
		super.addCommand(c);
		
		// Post invariants
		checkInvariant();
		
		// \post addCommand(ccs, c) \implique c \in commands(ccs)
		if (!(commands().contains(c)))
			throw new PostConditionError("commands() does not contain c");
	}
	
}
