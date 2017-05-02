package contract;

import interfaceservice.FrameCounterService;
import contract.decorator.FrameCounterDecorator;

public class FrameCounterContract extends FrameCounterDecorator {

	public FrameCounterContract(FrameCounterService delegate) {
		super(delegate);
	}

	
	// TODO
}
