package contract;

import interfaceservice.InputManagerService;
import contract.decorator.InputManagerDecorator;

public class InputManagerContract extends InputManagerDecorator {

	public InputManagerContract(InputManagerService delegate) {
		super(delegate);
	}
	
	// TODO
	
}
