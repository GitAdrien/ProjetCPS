package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.CharacterContract;
import contract.InputManagerContract;
import contract.PlayerContract;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.direction.SimpleDirectionCommand;
import impl.CharacterImpl;
import impl.InputManagerImpl;
import impl.PlayerImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;

public abstract class AbstractEngine {
	private EngineService engine;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;
	private SimpleDirectionCommand com1;
	private SimpleDirectionCommand com2;
	private InputManagerService im1;
	private InputManagerService im2;
	
	
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
		
	}

	@After
	public final void afterTests(){

	}


	//fonction init
	//PRE

	@Test
	public void testInitPre(){
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(100, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(true);
		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPreFail(){// h < 0
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(100, c2, im2.init());
			engine.init(-1, 1000, 100, p1, p2);
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitPreFail2(){ //w < s
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 5, 100, p1, p2);
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitPreFail3(){ // p1 == p2
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p2, p2);
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail4(){ // s<0
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, -5, 100, p2, p2);
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
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.height() == 300);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostWidth(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.width() == 1000);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayer(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.player(0) == p1);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayer2(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
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
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(!(engine.player(0) == p2));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayerFail2(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(!(engine.player(1) == p1));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosPlayer(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(0).positionX() == (1000/2) - (100/2)); //joueurs 1
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosPlayer2(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(1).positionX() == (1000/2) + (100/2)); // joueurs 2
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosY(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(0).positionY() == 0);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosY2(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(1).positionY() == 0);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostFaceRight(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(0).faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostFaceRight2(){ 
		try{
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			engine.init(300, 1000, 100, p1, p2);
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
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			com1 = SimpleDirectionCommand.RIGHT;
			com2 = SimpleDirectionCommand.LEFT;	
			im1.setPressed(com1);
			im2.setPressed(com2);
			engine.init(300, 1000, 100, p1, p2);
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
			c1.init(100, 100, true, engine);
			c2.init(100, 100, false, engine);
			p1.init(10, c1, im1.init());
			p2.init(10, c2, im2.init());
			com1 = SimpleDirectionCommand.RIGHT;
			com2 = SimpleDirectionCommand.LEFT;	
			im1.setPressed(com1);
			im2.setPressed(com2);
			
			engine.init(300, 1000, 100, p1, p2);
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
}
