package test;

import impl.HitboxImpl;
import contract.HitboxContract;

public class HitboxTest extends AbstractHitbox{
	@Override
	public void beforeTests(){
		super.beforeTests();
		setHitbox(new HitboxContract(new HitboxImpl()));
	}
}
