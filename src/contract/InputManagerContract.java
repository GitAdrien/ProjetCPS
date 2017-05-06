package contract;

import interfaceservice.InputManagerService;
import contract.decorator.InputManagerDecorator;
import contract.errors.PostConditionError;
import enums.Command;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;

public class InputManagerContract extends InputManagerDecorator {

	public InputManagerContract(InputManagerService delegate) {
		super(delegate);
	}
	
	private void checkInvariant() {
		
	}
	
	public InputManagerService init(){
		super.init();
		checkInvariant();
		SimpleAttackCommand com1 = SimpleAttackCommand.KICK;
		SimpleAttackCommand com2 = SimpleAttackCommand.PUNCH;
		SimpleDirectionCommand com3 = SimpleDirectionCommand.DOWN;
		SimpleDirectionCommand com4 = SimpleDirectionCommand.LEFT;
		SimpleDirectionCommand com5 = SimpleDirectionCommand.RIGHT;
		SimpleDirectionCommand com6 = SimpleDirectionCommand.UP;
		// \post: isPressed(init(), c) = false
		if(!(!isPressed(com1)&&!isPressed(com2)&&!isPressed(com3)
				&&!isPressed(com4)&&!isPressed(com5)&&!isPressed(com6)))
			throw new PostConditionError("Button is pressed");

		return this;
	}
	
	public void setPressed(Command com){
		super.setPressed(com);
		checkInvariant();
		// Post: isPressed(setPressed(im, c), c) = true
		if(!isPressed(com))
			throw new PostConditionError("button not press");
	}
	
	public void setReleased(Command com){
		super.setReleased(com);
		checkInvariant();
		// Post: isPressed(setReleased(im, c), c) = false
		if(isPressed(com))
			throw new PostConditionError("button press");
	}

	
}
