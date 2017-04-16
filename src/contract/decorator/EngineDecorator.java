package contract.decorator;

import enums.Commande;
import interfaceservice.Character;
import interfaceservice.Engine;
import interfaceservice.Player;

public class EngineDecorator implements Engine {
	private Engine delegate;
	
	public EngineDecorator(Engine delegate) {
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
	public Character character(int i) {
		return delegate.character(i);
	}

	@Override
	public Player player(int i) {
		return delegate.player(i);
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver();
	}

	@Override
	public Engine init(int h, int w, int s, Player p1, Player p2) {
		return delegate.init(h, w, s, p1, p2);
	}

	@Override
	public Engine step(Commande C1, Commande C2) {
		return delegate.step(C1, C2);
	}
}
