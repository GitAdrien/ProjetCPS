package contract.decorator;

import java.util.List;

import enums.Command;
import interfaceservice.CharacterService;
import interfaceservice.PlayerService;

public class PlayerDecorator implements PlayerService {
	private PlayerService delegate;
	
	public PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public int window() {
		return delegate.window();
	}

	@Override
	public List<Command> commandsWithinWindow() {
		return delegate.commandsWithinWindow();
	}

	@Override
	public CharacterService character() {
		return delegate.character();
	}

	@Override
	public PlayerService init(int w, CharacterService c) {
		return delegate.init(w, c);
	}

}
