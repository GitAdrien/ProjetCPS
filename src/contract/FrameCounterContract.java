package contract;

import interfaceservice.FrameCounterService;
import contract.decorator.FrameCounterDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;

public class FrameCounterContract extends FrameCounterDecorator {

	public FrameCounterContract(FrameCounterService delegate) {
		super(delegate);
	}

	public void checkInvariant(){
		if(!(frame() >= 0))
			throw new InvariantError("frame() < 0");
		if(!(frame() < max()))
			throw new InvariantError(" frame() < max()");
		
	}
	
	public FrameCounterService init(int m){
		// \pre: init(m) \requires m > 0
		if(!(m>0))
			throw new PreConditionError("m<=0");
		super.init(m);
		checkInvariant();
		
		
		// \post: max(init(m)) = m
		if(!(max() == m))
			throw new PostConditionError("frame() < 0");
		// \post: frame(init(m)) = 0
		if(!(frame() == 0))
			throw new PostConditionError(" frame() < max()");
		return this;
	}
	
	public FrameCounterService nextFrame(){
		int save_frame_at_pre = frame();
		super.nextFrame();
		checkInvariant();
		if(!((save_frame_at_pre + 1)%max() == frame()))
			throw new PostConditionError("frame_pre != frame() +1 ");
		
		return this;
	}
	
	public int difference(int f){
		//\pre:difference(fc,f) \requires  f >= 0 \and i < max(fc)
		int res;
		if(!(f>=0 && f < max()))
			throw new PreConditionError(" f < 0 or i >= max(fc)");
		res = super.difference(f);
		checkInvariant();
		
		//TODO : sans doute une petite erreur
		//\post: difference(fc, i) = difference1(fc, max(fc), i)
		//if(!(res == super.difference(max(),f)))
		//	throw new PostConditionError("difference error");
		return res;
	}
	
	public int difference(int i, int j){
		//\pre: difference(fc, i, j) \requires i>=0 \and i<max(fc) \and
		//							j>=0 \and j<max(fc)
		int res;
		if(!(i>=0&&i<max()&&j>=0&&j<max()))
			throw new PreConditionError("i>=0 or i<max() or j>=0 or j<max()");
		
		res = super.difference(i, j);
		
		//\post: i<j \implique difference(fc, i, j) = (max(fc) -i)+j
		if(i<j){
			if(!(res == max() - i + j))
				throw new PostConditionError("difference(fc, i, j) != (max(fc) -i)+j");
		}
		//\post: i>=j \implique difference(fc, i, j) = i-j
		else{
			if( res == i-j)
				throw new PostConditionError("difference(fc, i, j) != i-j");
		}
		return res;
	}
	
}
