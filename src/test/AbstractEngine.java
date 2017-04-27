package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.errors.PreConditionError;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;

public abstract class AbstractEngine {
	private EngineService engine;
	private PlayerService p1;
	private PlayerService p2;
	private CharacterService c1;
	private CharacterService c2;

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
	public void testInit(){
		try{
		engine.init(300, 1000, 100, p1, p2);
		}
		catch(PreConditionError p){
			
		}
	}


}
