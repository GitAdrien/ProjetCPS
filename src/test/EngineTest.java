package test;

import impl.EngineImpl;
import contract.EngineContract;

public class EngineTest extends AbstractEngine{
	@Override
	public void beforeTests(){
		super.beforeTests();
		setEngine(new EngineContract(new EngineImpl()));
	}

}
