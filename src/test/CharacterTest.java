package test;

import contract.CharacterContract;

public class CharacterTest extends AbstractCharacter{
	@Override
	public void beforeTests(){
		super.beforeTests();
		setCharacter(new CharacterContract(new CharacterImpl()));
	}

}
