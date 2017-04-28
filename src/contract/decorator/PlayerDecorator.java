package contract.decorator;

import java.util.List;

import enums.Command;
import enums.SimpleCommand;
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
	
	@Override
	public SimpleCommand getLastCommand() {
		return delegate.getLastCommand();
	}
	
	@Override
	public void addCommand(SimpleCommand c) {
		delegate.addCommand(c);
	}
}
