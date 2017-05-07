package test;

import impl.HitboxImplError;
import contract.HitboxContract;

public class HitboxTestError extends AbstractHitboxError{
	@Override
	public void beforeTests(){
		super.beforeTests();
		setHitbox(new HitboxContract(new HitboxImplError()));
	}
}
