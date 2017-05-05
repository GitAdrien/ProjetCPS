package contract;

import interfaceservice.CharacterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;
import contract.decorator.PlayerDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.AttackCommand;
import enums.DirectionCommand;
import enums.attack.ComplexeAttackCommand;
import enums.attack.SimpleAttackCommand;
import enums.direction.ComplexeDirectionCommand;
import enums.direction.SimpleDirectionCommand;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	public void checkInvariant() {
		if(((this.character().engine().frameCounter().difference(lastInput()) > window()) && 
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
	
	public DirectionCommand getActiveDirection(){
		DirectionCommand res = super.getActiveDirection();
		//FAUX : Difficlement testable en réalité : voir getActiveAttack
		
		//checkInvariant(); //appel de getActiveDirection dans le checkInvariant
		
		// \post: InputManagerService::isPressed(inputManager(p), c1) \and
	    //	InputManagerService::isPressed(inputManager(p), c2) \and
	    //	(\exists c, c \in ComplexeAttackCommand, (ComplexeAttackCommand::getC1(c, c1) \and ComplexeAttackCommand::getC2(c, c2)) \or
	    //	(ComplexeAttackCommand::getC2(c, c1) \and ComplexeAttackCommand::getC1(c, c2))) => getActiveAttack(p) = c
		SimpleDirectionCommand cmd = SimpleDirectionCommand.LEFT;
		SimpleDirectionCommand cmd2 = SimpleDirectionCommand.RIGHT;

		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeDirectionCommand.DOWN_LEFT.getC1()) && (cmd2 == ComplexeDirectionCommand.DOWN_LEFT.getC2())){
			if(!(res == ComplexeDirectionCommand.DOWN_LEFT))
				throw new PostConditionError("error commands");
		}
	
		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeDirectionCommand.DOWN_LEFT.getC2()) && (cmd2 == ComplexeDirectionCommand.DOWN_LEFT.getC1())){
			if(!(res == ComplexeDirectionCommand.DOWN_LEFT))
				throw new PostConditionError("error commands");
		}
		cmd = SimpleDirectionCommand.DOWN;
		cmd2 = SimpleDirectionCommand.LEFT;
		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeDirectionCommand.DOWN_LEFT.getC1()) && (cmd2 == ComplexeDirectionCommand.DOWN_LEFT.getC2())){
			if(!(res == ComplexeDirectionCommand.DOWN_LEFT))
				throw new PostConditionError("error commands");
		}
	
		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeDirectionCommand.DOWN_LEFT.getC2()) && (cmd2 == ComplexeDirectionCommand.DOWN_LEFT.getC1())){
			if(!(res == ComplexeDirectionCommand.DOWN_LEFT))
				throw new PostConditionError("error commands");
		}
		
		// \post: InputManagerService::isPressed(inputManager(p), c) \and c \in DirectionCommand => getActiveDirection(p) = c
		if(inputManager().isPressed(cmd)){
			if(!(res == cmd))
				throw new PostConditionError("error commands");
		}
	return res;
	}
	
	public AttackCommand getActiveAttack(){
		AttackCommand res = super.getActiveAttack();

		/*SimpleAttackCommand  cmd = SimpleAttackCommand.KICK;
		SimpleAttackCommand  cmd2 = SimpleAttackCommand.PUNCH;
		inputManager().setPressed(cmd2);
		inputManager().setPressed(cmd);
		
		
		AttackCommand res = super.getActiveAttack();
		//checkInvariant(); //appel de getActiveDirection dans le checkInvariant
		
//		InputManagerService::isPressed(inputManager(p), c1) \and
//	    InputManagerService::isPressed(inputManager(p), c2) \and
//	    (\exists c, c \in ComplexeAttackCommand, (ComplexeAttackCommand::getC1(c, c1) \and ComplexeAttackCommand::getC2(c, c2)) \or
//	    (ComplexeAttackCommand::getC2(c, c1) \and ComplexeAttackCommand::getC1(c, c2))) => getActiveAttack(p) = c
		
		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeAttackCommand.PUNCH_N_KICK.getC1()) && (cmd2 == ComplexeAttackCommand.PUNCH_N_KICK.getC2())){
			if(!(res == ComplexeAttackCommand.PUNCH_N_KICK))
				throw new PostConditionError("error commands 1");
		}
	
		if(inputManager().isPressed(cmd) && inputManager().isPressed(cmd2) && (cmd == ComplexeAttackCommand.PUNCH_N_KICK.getC2()) && (cmd2 == ComplexeAttackCommand.PUNCH_N_KICK.getC1())){
			if(!(res == ComplexeAttackCommand.PUNCH_N_KICK))
				throw new PostConditionError("error commands 2");
		}
		
		
	    // \post: InputManagerService::isPressed(inputManager(p), c) \and c \in AttackCommand => getActiveAttack(p) = c
		if(inputManager().isPressed(cmd) && res instanceof SimpleAttackCommand){
			if(!(res == cmd))
				throw new PostConditionError("error commands 5");
		}
		*/
	return res;
	}
}
