package test;

import org.junit.After;
import org.junit.Before;

import interfaceservice.CharacterService;

public abstract class AstractCharacter {
	private CharacterService charact;
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
	public abstract void beforeTests();
	
	@After
	public final void afterTests(){
		charact = null;
	}
	
}
