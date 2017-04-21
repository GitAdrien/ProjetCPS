package contract;

import interfaceservice.PlayerService;
import contract.decorator.PlayerDecorator;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

}
