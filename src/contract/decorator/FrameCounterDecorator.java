package contract.decorator;

import interfaceservice.FrameCounterService;

public class FrameCounterDecorator implements FrameCounterService {
	private FrameCounterService delegate;
	
	public FrameCounterDecorator(FrameCounterService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int frame() {
		return delegate.frame();
	}

	@Override
	public int max() {
		return delegate.max();
	}

	@Override
	public FrameCounterService init(int maxframeValue) {
		return delegate.init(maxframeValue);
	}

	@Override
	public FrameCounterService nextFrame() {
		return delegate.nextFrame();
	}

	@Override
	public int difference(int f) {
		return delegate.difference(f);
	}

	@Override
	public int difference(int current, int start) {
		return delegate.difference(current, start);
	}
	
}
