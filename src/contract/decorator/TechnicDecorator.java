package contract.decorator;

import java.util.List;

import enums.Command;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class TechnicDecorator implements TechnicService {
	private TechnicService delegate;
	
	public TechnicDecorator(TechnicService ts) {
		this.delegate = ts;
	}
	
	@Override
	public String name() {
		
		return delegate.name();
	}

	@Override
	public List<Command> commands() {
		return delegate.commands();
	}

	@Override
	public int damage() {
		return delegate.damage();
	}

	@Override
	public HitboxService hitbox() {
		return delegate.hitbox();
	}

	@Override
	public TechnicService init(String n, List<Command> c, int d, int f, int s, HitboxService h) {
		return delegate.init(n, c, d, f, s, h);
	}

	@Override
	public int frame() {
		return delegate.frame();
	}

	@Override
	public int stun() {
		return delegate.stun();
	}

}
