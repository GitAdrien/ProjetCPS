package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.HitboxContract;
import contract.errors.PostConditionError;
import contract.errors.PreConditionError;
import impl.HitboxImpl;
import interfaceservice.HitboxService;

public abstract class AbstractHitbox {
	private HitboxService hitbox;

	public final HitboxService getHitbox() {
		return hitbox;
	}

	public final void setHitbox(HitboxService setHitbox){
		hitbox = setHitbox;
	}

	@Before
	public void beforeTests(){
		hitbox = new HitboxContract(new HitboxImpl());
	}

	@After
	public final void afterTests(){

	}

	@Test
	public void testInitPre(){
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}
	@Test
	public void testInitPre2(){ //x = 0
		try{
			hitbox.init(0, 5, 20, 50);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPre3(){ //y = 0
		try{
			hitbox.init(10, 0, 20, 50);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){	
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPreFail(){ //x < 0
		try{
			hitbox.init(-2, 5, 20, 50);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail2(){ //y < 0
		try{
			hitbox.init(10, -5, 20, 50);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail3(){ //w = 0
		try{
			hitbox.init(10, 5, 0, 50);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail4(){ //w < 0
		try{
			hitbox.init(10, 5, -7, 50);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail5(){ //h = 0
		try{
			hitbox.init(10, 5, 20, 0);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testInitPreFail6(){ //h < 0
		try{
			hitbox.init(10, 5, 20, -10);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	//POST

	@Test
	public void testInitPost(){ // \post PositionX(init(x, y, w, h)) = x
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.positionX() == 10);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInitPost2(){ // \post PositionY(init(x, y, w, h)) = y
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.positionY() == 5);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPost3(){ // \post width(init(x, y, w, h)) = w
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.width() == 20);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testInitPost4(){ // \post height(init(x, y, w, h)) = h
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.height() == 50);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	// Fonction moveTo
	// PRE
	
	@Test
	public void testMoveToPre(){ // x>=0 \and y>+0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.moveTo(10, 15);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testMoveToPreFail(){ // x<0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.moveTo(-1, 10);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testMoveToPreFail2(){ // y<0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.moveTo(15, -5);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}
	// POST
	
	@Test
	public void testMoveToPost(){ 	// \post : PositionX(MoveTo(h, x, y)) = x
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.moveTo(39, 45).positionX() == 39);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testMoveToPost2(){ 	// \post : PositionY(MoveTo(h, x, y)) = y
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.moveTo(39, 45).positionY() == 45);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}



	// Fonction SetWidth
	// PRE
	
	@Test
	public void testsetWidthPre(){ //w>0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setWidth(10);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testsetWidthPreFail(){ //w=0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setWidth(0);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testsetWidthPreFail2(){ //w<0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setWidth(-5);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	//POST
	
	@Test
	public void testsetWidthPost(){	// \post : width(setWidth(hb, w)) = w
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.setWidth(10).width() == 10);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	//Fonction setHeight
	//PRE
	@Test
	public void testSetHeightPre(){ //h>0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setHeight(10);
			Assert.assertTrue(true);

		}
		catch(PreConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testSetHeightPreFail(){ //h=0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setHeight(0);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testSetHeightPreFail2(){ //h<0
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.setHeight(-5);
			Assert.assertTrue(false);

		}
		catch(PreConditionError p){
			Assert.assertTrue(true);
		}
	}

	//POST
	
	@Test
	public void testSetHeightPost(){		// \post : width(setHeight(hb, h)) = h
		try{
			hitbox.init(10, 5, 20, 50);
			Assert.assertTrue(hitbox.setHeight(10).height() == 10);

		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
	}
	
	//Step
	//POST
	
	@Test
	public void testCopyPost(){	// \post : positionX(h) = positionX(copy(h))
		try{
			hitbox.init(10, 5, 20, 50);
			HitboxService copy = new HitboxContract(new HitboxImpl());
			copy = hitbox.copy();
			Assert.assertTrue(copy.positionX() == hitbox.positionX());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testCopyPost2(){	// \post : positionY(h) = positionY(copy(h))
		try{
			hitbox.init(10, 5, 20, 50);
			HitboxService copy = new HitboxContract(new HitboxImpl());
			copy = hitbox.copy();
			Assert.assertTrue(copy.positionY() == hitbox.positionY());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testCopyPost3(){	// \post : width(h) = width(copy(h))
		try{
			hitbox.init(10, 5, 20, 50);
			HitboxService copy = new HitboxContract(new HitboxImpl());
			copy = hitbox.copy();
			Assert.assertTrue(copy.width() == hitbox.width());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void testCopyPost4(){	// \post : height(h) = height(copy(h))
		try{
			hitbox.init(10, 5, 20, 50);
			HitboxService copy = new HitboxContract(new HitboxImpl());
			copy = hitbox.copy();
			Assert.assertTrue(copy.height() == hitbox.height());
		}
		catch(PostConditionError p){
			Assert.assertTrue(false);
		}
		
	}
	
	
}

