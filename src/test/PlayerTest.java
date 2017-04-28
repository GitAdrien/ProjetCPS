package test;

import impl.PlayerImpl;
import contract.PlayerContract;

public class PlayerTest extends AbstractPlayer {
	@Override
	public void beforeTests(){
		super.beforeTests();
		setPlayer(new PlayerContract(new PlayerImpl()));
	}

}
