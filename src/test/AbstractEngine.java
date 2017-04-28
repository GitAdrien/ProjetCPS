package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.Command;
import enums.SimpleCommand;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public abstract class AbstractEngine {
	private EngineService engine;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;
	private Command com1;
	private Command com2;

	public final EngineService getEngine() {
		return engine;
	}

	public final void setEngine(EngineService setEngine){
		engine = setEngine;
	}

	@Before
	public void beforeTests(){
		c1.init(100, 100, true, engine);
		c2.init(100, 100, false, engine);
		p1.init(10, c1);
		p2.init(100, c2);
		
	}

	@After
	public final void afterTests(){

	}


	//fonction init
	//PRE

	@Test
	public void testInitPre(){
		try{
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
			engine.init(-1, 1000, 100, p1, p2);
			Assert.assertTrue(false);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitPreFail2(){ //w<s
		try{
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
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.height() == 300);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostHeightFail(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.height() != 300);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostWidth(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.width() == 1000);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostWidthFail(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.width() != 1000);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayer(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.player(1) == p1);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayer2(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.player(2) == p2);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayerFail(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(!(engine.player(1) == p2));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPlayerFail2(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(!(engine.player(2) == p1));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosPlayer(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(1).positionX() == (1000/2) - (100/2));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosPlayer2(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(2).positionX() == (1000/2) + (100/2));
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosY(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(1).positionY() == 0);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostPosY2(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(2).positionY() == 0);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostFaceRight(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(engine.character(1).faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPostFaceRight2(){ 
		try{
			engine.init(300, 1000, 100, p1, p2);
			Assert.assertTrue(!engine.character(2).faceRight());
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
			engine.init(300, 1000, 100, p1, p2);
			if(engine.gameOver()){
				engine.step(com1, com2);
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
			engine.init(300, 1000, 100, p1, p2);
			if(!engine.gameOver()){
				engine.step(com1, com2);
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
