package test;

import impl.PlayerImpl;
import interfaceservice.PlayerService;
import contract.PlayerContract;

public class PlayerTest extends AbstractPlayer {
	@Override
	public void beforeTests(){
		super.beforeTests();
		PlayerService play = new PlayerImpl();
		System.out.println("on est passé");
		setPlayer(new PlayerContract(play));
	}

}
