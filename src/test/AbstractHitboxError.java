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

public abstract class AbstractHitboxError {
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
	public void testMoveTo(){ // test avec un implentation buggé, une exception est bien lancé
		try{
			hitbox.init(10, 5, 20, 50);
			hitbox.moveTo(10, 15);
			Assert.assertTrue(false);

		}
		catch(PostConditionError p){
			System.out.println("erreur attendu: "+p.getMessage());
			Assert.assertTrue(true);
		}
	}
}