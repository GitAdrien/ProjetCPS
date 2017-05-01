package contract.decorator;

import enums.SimpleCommand;
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
	public void setPressed(SimpleCommand cmd) {
		delegate.setPressed(cmd);
	}

	@Override
	public void setReleased(SimpleCommand cmd) {
		delegate.setReleased(cmd);
	}

	@Override
	public boolean isPressed(SimpleCommand cmd) {
		return delegate.isPressed(cmd);
	}

}
