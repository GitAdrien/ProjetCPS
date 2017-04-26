package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;
import interfaceservice.PlayerService;

public abstract class AstractCharacter {
	private CharacterService charact;
	private EngineService engine;
	private CharacterService other;
	private PlayerService p1;
	private PlayerService p2;
	/**
	 * @return the Character
	 */
	public final CharacterService getCharacter() {
		return charact;
	}
	
	/**
	 * @param Charact
	 * set the Character
	 */
	public final void setCharacter(CharacterService character){
			charact = character;
	}
	
	@Before
	public void beforeTests(){
		engine.init(100, 1000, 100, p1.init(10, charact), p2.init(10, other.init(100, 10, false, engine)));
		 
	}
	
	@After
	public final void afterTests(){
		charact = null;
	}
	
	
	//PRE
	@Test
	public void testInit(){ // le character regarde à droite
		charact.init(10, 10, true, engine);
		Assert.assertTrue(true);
	}
	
	@Test
	public void testInit2(){ // le character regarde à gauche
		charact.init(10, 10, false, engine);
		Assert.assertTrue(true);
	}
	@Test
	public void testInitFail2(){ //life inferieur à zero
		try{
			charact.init(-2, 10, true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail3(){ //speed inferieur à zero
		try{
			charact.init(10, -2, true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail4(){ //life egale zero
		try{
			charact.init(0, 10, true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitFail5(){ //speed egale zero
		try{
			charact.init(10, 0, true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	//Post
	
	@Test
	public void testPostInitLife(){
		try{
			charact.init(10, 10, true, engine);
			Assert.assertTrue(charact.life() == 10);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPostInitSpeed(){
		try{
			charact.init(10, 10, true, engine);
			Assert.assertTrue(charact.speed() == 10);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPostInitFaceRight(){
		try{
			charact.init(10, 10, true, engine);
			Assert.assertTrue(charact.faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPostInitFaceRight2(){
		try{
			charact.init(10, 10, false, engine);
			Assert.assertTrue(!charact.faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	@Test
	public void testPostInitEngine(){
		try{
			charact.init(10, 10, true, engine);
			Assert.assertTrue(charact.engine() == engine);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	
	//Fonction moveLeft
	//Post
	@Test
	public void testPostMoveLeft(){ //Collision avec un autre joueur
		try {
			charact.init(10, 10, true, engine);
			int posX_at_pre = charact.positionX();
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(charact.charBox().collidesWith(other.charBox()))
				Assert.assertTrue(charact.moveLeft().positionX() == posX_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testPostMoveLeft2(){ //Collision avec le mur de gauche sans collison avec un autre joueur
		try {
			charact.init(10, 10, true, engine);
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(!charact.charBox().collidesWith(other.charBox()))
				if(charact.positionX() <= charact.speed())
				Assert.assertTrue(charact.moveLeft().positionX() == 0);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPostMoveLeft3(){ //ni collison avec un autre joueur, ni avec le mur de gauche
		try {
			charact.init(10, 10, true, engine);
			int posX_at_pre = charact.positionX();
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(!charact.charBox().collidesWith(other.charBox()))
				if(charact.positionX() <= charact.speed())
				Assert.assertTrue(charact.moveLeft().positionX() == posX_at_pre - charact.speed());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testPostMoveLeftFaceRight(){ // bonne face
		try {
			charact.init(10, 10, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
		
	}

	@Test
	public void testPostMoveLeftFaceRightFail(){ // mauvaise face
		try {
			charact.init(10, 10, true, engine);
			charact.moveLeft();
			Assert.assertTrue(!charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(true);
		}
		
	}

	@Test
	public void testPostMoveLeftLife(){ // vie pas modifié
		try {
			charact.init(10, 10, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.life() == 10);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
		
	}

	@Test
	public void testPostMoveLeftLifeFail(){ // vie modifié
		try {
			charact.init(10, 10, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.life() != 10);
		} catch (PostConditionError p) {
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void testPostMoveLeftPositionY(){ // positionY non modifié
		try {
			charact.init(10, 10, true, engine);
			int posY_at_pre = charact.positionY();
			charact.moveLeft();
			Assert.assertTrue(charact.positionY() == posY_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
		
	}

	@Test
	public void testPostMoveLeftPositionYFail(){ // positionY modifié
		try {
			charact.init(10, 10, true, engine);
			int posY_at_pre = charact.positionY();
			charact.moveLeft();
			Assert.assertTrue(charact.positionY() != posY_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(true);
		}
		
	}

}
