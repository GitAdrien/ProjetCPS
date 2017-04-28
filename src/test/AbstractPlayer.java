package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import contract.errors.PreConditionError;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public abstract class AbstractPlayer {
	private PlayerService player;
	private CharacterService charact;
	private EngineService engine;
	private PlayerService p2;
	
	public final PlayerService getPlayer(){
		return player;
	}
	
	public final void setPlayer(PlayerService setPlayer){
		player = setPlayer;
	}
	
	@Before
	public void beforeTests(){
		//charact et player se morde la queue... comment ont faite...
		charact.init(100, 100, true, engine.init(1000, 2000, 200, player, p2));
	}
	
	@After
	public final void afterTests(){
		
	}
	
	//Fonction init
	//PRE
	
	public void testInitPre(){ //w > 0
		try{
			player.init(100, charact);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	
	public void testInitPre2(){ //w > 0
		try{
			player.init(100, charact);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	public void testInitPreFail(){ //w < 0
		try{
			player.init(-10, charact);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(true);
		}
	}
	
	//POST
	public void testInitPost(){ 	// \post : window(init(w, c)) = w
		try{
			player.init(100, charact);
			Assert.assertTrue(player.window()==100);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	
	public void testInitPost2(){ 	// \post : character(init(w, c)) = c
		try{
			player.init(100, charact);
			Assert.assertTrue(player.character()==charact);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}

	public void testInitPost3(){ // \post : commandsWithinWindow(init(w,c)) = \empty
		try{
			player.init(100, charact);
			Assert.assertTrue(player.commandsWithinWindow().isEmpty());

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
}
