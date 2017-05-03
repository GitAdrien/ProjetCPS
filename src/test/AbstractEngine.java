package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.CharacterContract;
import contract.FrameCounterContract;
import contract.InputManagerContract;
import contract.PlayerContract;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;
import impl.CharacterImpl;
import impl.FrameCounterImpl;
import impl.InputManagerImpl;
import impl.PlayerImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;

public abstract class AbstractEngine {
	private final static int MAX_FRAME_VALUE = 5000;
	private EngineService engine;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;
	private SimpleDirectionCommand com1;
	private SimpleDirectionCommand com2;
	private InputManagerService im1;
	private InputManagerService im2;
	private FrameCounterService fc;


	public final EngineService getEngine() {
		return engine;
	}

	public final void setEngine(EngineService setEngine){
		engine = setEngine;
	}

	@Before
	public void beforeTests(){
		//engine = new EngineContract(new EngineImpl());
		c1 = new CharacterContract(new CharacterImpl());
		c2 = new CharacterContract(new CharacterImpl());
		p1 = new PlayerContract(new PlayerImpl());
		p2 = new PlayerContract(new PlayerImpl());
		im1 = new InputManagerContract(new InputManagerImpl());
		im2 = new InputManagerContract(new InputManagerImpl());
		fc = new FrameCounterContract(new FrameCounterImpl());

	}

	@After
	public final void afterTests(){

	}


	//fonction init
	//PRE

	@Test
	public void testInitPre(){
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20,true, engine);
			c2.init(100, 100, 1, 20,false, engine);
			p1.init(10, c1, im1.init());
			p2.init(100, c2, im2.init());
			Assert.assertTrue(true);
		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPreFail(){// h < 0
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(-1, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(100, c2, im2.init());
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail2(){ //w < s
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 5, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail3(){ // p1 == p2
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p2, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail4(){ // s<0
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, -5, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	//POST

	@Test
	public void testInitPostHeight(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.height() == 300);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostWidth(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.width() == 1000);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPlayer(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.player(0) == p1);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPlayer2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.player(1).equals(p2));
		}
		catch(PostConditionError p){
			System.out.println(p.getMessage());
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPlayerFail(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(!(engine.player(0) == p2));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPlayerFail2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(!(engine.player(1) == p1));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPosPlayer(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.character(0).positionX() == (1000/2) - (100/2)); //joueurs 1
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPosPlayer2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.character(1).positionX() == (1000/2) + (100/2)); // joueurs 2
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPosY(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.character(0).positionY() == engine.height() - 250);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostPosY2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.character(1).positionY() == engine.height() - 250);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostFaceRight(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(engine.character(0).faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPostFaceRight2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			Assert.assertTrue(!engine.character(1).faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	//TODO ??

	// Fonction Step
	// PRE

	@Test
	public void testStepPre(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			com1 = SimpleDirectionCommand.RIGHT;
			com2 = SimpleDirectionCommand.LEFT;	
			im1.setPressed(com1);
			im2.setPressed(com2);
			if(engine.gameOver()){
				engine.step();
				Assert.assertTrue(false);
			}

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testStepPre2(){ 
		try{
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			com1 = SimpleDirectionCommand.RIGHT;
			com2 = SimpleDirectionCommand.LEFT;	
			im1.setPressed(com1);
			im2.setPressed(com2);

			if(!engine.gameOver()){
				engine.step();
				Assert.assertTrue(true);
			}

		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}

	//POST

	//TODO : pas réussi à  tester les steps, je ne sais pas comment sauvgarder un état

	//Scenario

	@Test
	public void testScenario(){
		try{
			SimpleAttackCommand com3;
			fc.init(MAX_FRAME_VALUE);
			engine.init(300, 1000, 100, p1, p2, fc);
			c1.init(100, 100, 1, 20, true, engine);
			c2.init(100, 100, 1, 20, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			com1 = SimpleDirectionCommand.RIGHT;
			com2 = SimpleDirectionCommand.LEFT;	
			com3 = SimpleAttackCommand.KICK;
			im1.setPressed(com1);
			im2.setPressed(com2);
			int pos_x_at_pre = -1;
			while(pos_x_at_pre != engine.character(0).positionX()){
				pos_x_at_pre = engine.character(0).positionX();
				engine.character(0).step(com1);
			}
			while(engine.character(1).dead()){
				engine.character(0).step(com3);
			}
			Assert.assertTrue(true);
		}
		catch(Error e){
			Assert.assertTrue(false);
		}
	}
}
