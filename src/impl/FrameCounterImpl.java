package impl;

import interfaceservice.FrameCounterService;

public class FrameCounterImpl implements FrameCounterService {
	private int max;
	private int frame;
	
	public FrameCounterImpl() {}
	
	@Override
	public int frame() {
		return frame;
	}

	@Override
	public int max() {
		return max;
	}
	
	@Override
	public FrameCounterService init(int maxframeValue) {
		max = maxframeValue;
		frame = 0;
		
		return this;
	}

	@Override
	public FrameCounterService nextFrame() {
		frame = (frame + 1) % max;
		
		return this;
	}

	@Override
	public int difference(int f) {
		return difference(frame, f);
	}

	@Override
	public int difference(int start, int now) {
		if (start < now) 
			return ((max - start) + now);
		else 
			return (start - now);
	
	}

	
}
