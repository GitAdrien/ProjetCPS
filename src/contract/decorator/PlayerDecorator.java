package contract.decorator;

import java.util.List;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.InputManagerService;
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
	public PlayerService init(int w, CharacterService c, InputManagerService im) {
		return delegate.init(w, c, im);
	}
	
	@Override
	public SimpleCommand getActiveCommand() {
		return delegate.getActiveCommand();
	}

	@Override
	public InputManagerService inputManager() {
		return delegate.inputManager();
	}
	
}
