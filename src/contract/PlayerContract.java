package contract;

import interfaceservice.CharacterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;
import contract.decorator.PlayerDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		if(!((this.character().engine().frameCounter().difference(lastInput()) > window()) && 
				getActiveDirection() == SimpleDirectionCommand.NEUTRAL && 
				getActiveAttack() == SimpleAttackCommand.NONE))
			if(!commandsWithinWindow().isEmpty())
				throw new InvariantError("list is not empty");
				
			
	}
	
	@Override
	public PlayerService init(int w, CharacterService c, InputManagerService im){
		// \pre : w >= 0
		if(!(w >= 0))
			throw new PreConditionError("w is not positive");
		//checkInvariant();
		
		super.init(w, c, im);

		checkInvariant();

		
		// \post : window(init(w, c)) = w
		if(!(window() == w))
			throw new PostConditionError("window != w");
		// \post : character(init(w, c)) = c
		if(!(character() == c))
			throw new PostConditionError("character != c");
		// \post : commandsWithinWindow(init(w,c)) = \empty
		if(!commandsWithinWindow().isEmpty())
			throw new PostConditionError("commandsWithinWindow() is not empty");
		return this;
	}
	
	//TODO méthode éventuellement
}
