package contract.decorator;

import java.util.List;

import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CombinaisonCommandService;

public class CombinaisonCommandDecorator implements CombinaisonCommandService {
	private CombinaisonCommandService delegate;
	
	public CombinaisonCommandDecorator(CombinaisonCommandService delegate) {
		this.delegate = delegate;
	}

	@Override
	public List<SimpleCommand> commands() {
		return delegate.commands();
	}

	@Override
	public List<Command> complexeCommands() {
		return delegate.complexeCommands();
	}

	@Override
	public int player() {
		return delegate.player();
	}

	@Override
	public CombinaisonCommandService init(int p) {
		return delegate.init(p);
	}

	@Override
	public void parseCommands() {
		delegate.parseCommands();
	}

	@Override
	public void addCommand(SimpleCommand c) {
		delegate.addCommand(c);
	}
	
}
