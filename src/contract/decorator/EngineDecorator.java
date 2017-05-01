package contract.decorator;

import enums.direction.SimpleDirectionCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public class EngineDecorator implements EngineService {
	private EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int height() {
		return delegate.height();
	}

	@Override
	public int width() {
		return delegate.width();
	}

	@Override
	public CharacterService character(int i) {
		return delegate.character(i);
	}

	@Override
	public PlayerService player(int i) {
		return delegate.player(i);
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver();
	}

	@Override
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		return delegate.init(h, w, s, p1, p2);
	}

	@Override
	public EngineService step() {
		return delegate.step();
	}
}
