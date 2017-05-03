package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.CharacterContract;
import contract.EngineContract;
import contract.FrameCounterContract;
import contract.InputManagerContract;
import contract.PlayerContract;
import contract.errors.PreConditionError;
import impl.CharacterImpl;
import impl.EngineImpl;
import impl.FrameCounterImpl;
import impl.InputManagerImpl;
import impl.PlayerImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;

public abstract class AbstractPlayer {
	private PlayerService player;
	private CharacterService charact;
	private EngineService engine;
	private PlayerService p2;
	private InputManagerService im;
	private FrameCounterService fc;
	
	public final PlayerService getPlayer(){
		return player;
	}
	
	public final void setPlayer(PlayerService setPlayer){
		player = setPlayer;
	}
	
	@Before
	public void beforeTests(){
		player = new PlayerContract(new PlayerImpl());
		charact = new CharacterContract(new CharacterImpl());
		engine = new EngineContract(new EngineImpl());
		p2 = new PlayerContract(new PlayerImpl());
		im = new InputManagerContract(new InputManagerImpl());
		//Possibilite d'erreurs
		fc = new FrameCounterContract(new FrameCounterImpl());
		fc.init(30);
		charact.init(100, 100, 1, 20, true, engine.init(1000, 2000, 200, player, p2, fc)); // TODO ajouter le frameCounter
	}
	
	@After
	public final void afterTests(){
		
	}
	
	//Fonction init
	//PRE
	
	@Test
	public void testInitPre(){ //w > 0
		try{
			player.init(100, charact, im.init());
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPre2(){ //w > 0
		try{
			player.init(100, charact, im.init());
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPreFail(){ //w < 0
		try{
			player.init(-10, charact, im.init());
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(true);
		}
	}
	
	//POST
	
	@Test
	public void testInitPost(){ 	// \post : window(init(w, c)) = w
		try{
			player.init(100, charact, im.init());
			Assert.assertTrue(player.window()==100);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
	
	
	@Test
	public void testInitPost2(){ 	// \post : character(init(w, c)) = c
		try{
			player.init(100, charact, im.init());
			Assert.assertTrue(player.character()==charact);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPost3(){ // \post : commandsWithinWindow(init(w,c)) = \empty
		try{
			player.init(100, charact, im.init());
			Assert.assertTrue(player.commandsWithinWindow().isEmpty());

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}
}
