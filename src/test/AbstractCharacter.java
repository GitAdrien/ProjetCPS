package test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.CharacterContract;
import contract.EngineContract;
import contract.FrameCounterContract;
import contract.HitboxContract;
import contract.PlayerContract;
import contract.TechnicContract;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import enums.Command;
import enums.direction.SimpleDirectionCommand;
import impl.CharacterImpl;
import impl.EngineImpl;
import impl.FrameCounterImpl;
import impl.HitboxImpl;
import impl.PlayerImpl;
import impl.TechnicImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.HitboxService;
import interfaceservice.PlayerService;
import interfaceservice.TechnicService;

public abstract class AbstractCharacter {
	private final static int MAX_FRAME_VALUE = 5000; // Le frameCounter fonctionne avec un compteur circulaire, c'est juste la taille de la boucle
	
	private CharacterService charact;
	private EngineService engine;
	private CharacterService other;
	private PlayerService p1;
	private PlayerService p2;
	private FrameCounterService fc;
	
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
		charact = new CharacterContract(new CharacterImpl());
		other = new CharacterContract(new CharacterImpl());
		p1 = new PlayerContract(new PlayerImpl());
		p2 = new PlayerContract(new PlayerImpl());
		engine = new EngineContract(new EngineImpl());
		fc = new FrameCounterContract(new FrameCounterImpl());
		fc.init(MAX_FRAME_VALUE);
		engine.init(100, 1000, 100, p1 ,p2 ,fc);
		//p1.init(10, charact, im1.init());
		//p2.init(10, other.init(100, 10, false, engine),im2.init());

	}

	@After
	public final void afterTests(){
		charact = null;
	}


	//PRE
	@Test
	public void testInit(){ // le character regarde à droite
		charact.init(10, 10, 1, 5, true, engine);
		Assert.assertTrue(true);
	}

	@Test
	public void testInit2(){ // le character regarde à gauche
		charact.init(10, 10, 1, 5, false, engine);
		Assert.assertTrue(true);
	}
	@Test
	public void testInitFail2(){ //life inferieur à zero
		try{
			charact.init(-2, 10, 1, 5,true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail3(){ //speed inferieur à zero
		try{
			charact.init(10, -2, 1, 5,  true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail4(){ //life egale zero
		try{
			charact.init(0, 10, 1, 5, true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitFail5(){ //speed egale zero
		try{
			charact.init(10, 0, 1, 5,  true, engine);
		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	//Post

	@Test
	public void testPostInitLife(){
		try{
			charact.init(10, 10,1, 5, true, engine);
			Assert.assertTrue(charact.life() == 10);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostInitSpeed(){
		try{
			charact.init(10, 10, 1, 5, true, engine);
			Assert.assertTrue(charact.speed() == 10);
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostInitFaceRight(){
		try{
			charact.init(10, 10, 1, 5, true, engine);
			Assert.assertTrue(charact.faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostInitFaceRight2(){
		try{
			charact.init(10, 10, 1, 5,false, engine);
			Assert.assertTrue(!charact.faceRight());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	@Test
	public void testPostInitEngine(){
		try{
			charact.init(10, 10, 1, 5,true, engine);
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
			charact.init(10, 10, 1, 5, true, engine);
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
			charact.init(10, 10, 1, 5, true, engine);
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
			charact.init(10, 10, 1, 5, true, engine);
			int posX_at_pre = charact.positionX();
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(!charact.charBox().collidesWith(other.charBox()))
				if(charact.positionX() > charact.speed())
					Assert.assertTrue(charact.moveLeft().positionX() == posX_at_pre - charact.speed());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostMoveLeftFaceRight(){ // bonne face
		try {
			charact.init(10, 10, 1, 5, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveLeftFaceRightFail(){ // mauvaise face
		try {
			charact.init(10, 10, 1, 5, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveLeftLife(){ // vie pas modifié
		try {
			charact.init(10, 10, 1, 5, true, engine);
			charact.moveLeft();
			Assert.assertTrue(charact.life() == 10);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveLeftPositionY(){ // positionY non modifié
		try {
			charact.init(10, 10, 1, 5, true, engine);
			int posY_at_pre = charact.positionY();
			charact.moveLeft();
			Assert.assertTrue(charact.positionY() == posY_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	// fonction MoveRight
	// POST
	public void testPostMoveRight(){ //Collision avec un autre joueur
		try {
			charact.init(10, 10, 1, 5, true, engine);
			int posX_at_pre = charact.positionX();
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(charact.charBox().collidesWith(other.charBox()))
				Assert.assertTrue(charact.moveRight().positionX() == posX_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveRight2(){ //Collision avec le mur de gauche sans collison avec un autre joueur
		try {
			charact.init(10, 10, 1, 5, true, engine);
			int posX_at_pre = charact.positionX();
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(!charact.charBox().collidesWith(other.charBox()))
				if(charact.positionX() <= charact.speed())
					Assert.assertTrue(charact.moveRight().positionX() == posX_at_pre +charact.speed());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostMoveRight3(){ //ni collison avec un autre joueur, ni avec le mur de gauche
		try {
			charact.init(10, 10, 1, 5, true, engine);
			if (charact.engine().character(0).equals(charact))
				other = charact.engine().character(1);
			else
				other = charact.engine().character(1);
			if(!charact.charBox().collidesWith(other.charBox()))
				if(charact.positionX() > charact.speed())
					Assert.assertTrue(charact.moveRight().positionX() == engine.width());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostMoveRightFaceRight(){ // bonne face
		try {
			charact.init(10, 10, 1, 5, true, engine);
			charact.moveRight();
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveRightFaceRightFail(){ // mauvaise face
		try {
			charact.init(10, 10, 1, 5, true, engine);
			charact.moveRight();
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveRightLife(){ // vie pas modifié
		try {
			charact.init(10, 10,1, 5, true, engine);
			charact.moveRight();
			Assert.assertTrue(charact.life() == 10);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testPostMoveRightPositionY(){ // positionY non modifié
		try {
			charact.init(10, 10, 1, 5, true, engine);
			int posY_at_pre = charact.positionY();
			charact.moveRight();
			Assert.assertTrue(charact.positionY() == posY_at_pre);
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}

	}

	//fonction switchSide
	// POST

	@Test
	public void testPostSwitchSideFaceRight(){
		try {
			charact.init(10, 10, 1, 5, true, engine);
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostSwitchSideFaceRight2(){
		try {
			charact.init(10, 10, 1, 5, false, engine);
			Assert.assertTrue(!charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPostSwitchSideFaceRightFail(){
		try {
			charact.init(10, 10, 1, 5, true, engine);
			Assert.assertTrue(charact.faceRight());
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	//Fonction step
	//Pre

	@Test
	public void testPreStep(){
		try {
			SimpleDirectionCommand com = SimpleDirectionCommand.RIGHT;
			charact.init(10, 10, 1, 5, true, engine);
			if(!charact.dead()){
				charact.step(com);
				Assert.assertTrue(true);
			}

		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testPreStep2(){
		try {
			SimpleDirectionCommand com = SimpleDirectionCommand.RIGHT; //Je sais pas comment créer une commande
			charact.init(10, 10, 1, 5, true, engine);
			if(charact.dead()){
				charact.step(com);
			}
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}

	//POST

	//Je ne sais pas comment tester : 	// \post : step(c, RIGHT) = moveRight(c)


	//Fonction  addTechnic
	//POST
	@Test
	public void testPreAddTechnic(){
		try {
			TechnicService t = new TechnicContract(new TechnicImpl());
			charact.init(100, 100, 1, 5, true, engine);
			//TODO
			SimpleDirectionCommand com = SimpleDirectionCommand.DOWN;
			ArrayList<Command> coms = new ArrayList<>();
			coms.add(com);
			HitboxService h = new HitboxContract(new HitboxImpl());
			h.init(10, 10, 10, 10);
			t.init("attaque1", coms,10 ,10, 10, h); // TODO mettre le recovery (frame) et la durée du stun.
			charact.addTechnic(t);
			Assert.assertTrue(charact.technics().contains(t));
		} catch (PostConditionError p) {
			Assert.assertTrue(false);
		}
	}
}
