package test;

import impl.CharacterImpl;
import contract.CharacterContract;

public class CharacterTest extends AbstractCharacter{
	@Override
	public void beforeTests(){
		super.beforeTests();
		setCharacter(new CharacterContract(new CharacterImpl()));
	}

}
