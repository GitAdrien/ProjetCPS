package contract.decorator;

import enums.Command;
import enums.direction.SimpleDirectionCommand;
import interfaceservice.InputManagerService;

public class InputManagerDecorator implements InputManagerService {
	private InputManagerService delegate;
	
	public InputManagerDecorator(InputManagerService delegate) {
		this.delegate = delegate;
	}
	
	
	@Override
	public InputManagerService init() {
		return delegate.init();
	}

	@Override
	public void setPressed(Command cmd) {
		delegate.setPressed(cmd);
	}

	@Override
	public void setReleased(Command cmd) {
		delegate.setReleased(cmd);
	}

	@Override
	public boolean isPressed(Command cmd) {
		return delegate.isPressed(cmd);
	}

}
