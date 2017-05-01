package factory;

import impl.CharacterImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;

public class CharacterFactory {
	private static int CHAR_LIFE = 100;
	private static int CHAR_SPEED = 15;
	private static int CHAR_H_WIDTH = 70;
	private static int CHAR_H_HEIGHT = 100;
	
	public static CharacterService newCharacterOnLeftSide(EngineService engine, int spacing) {
		CharacterService result = new CharacterImpl();
		
		result.init(CHAR_LIFE, CHAR_SPEED, true, engine);
		int w = engine.width();
		int h = engine.height();
		int middle = w / 2;
		
		result.charBox().init((middle - (spacing/2)), h - CHAR_H_HEIGHT, CHAR_H_WIDTH, CHAR_H_HEIGHT);
		
		return result;
	}
	
	public static CharacterService newCharacterOnRightSide(EngineService engine, int spacing) {
		CharacterService result = new CharacterImpl();
		
		result.init(CHAR_LIFE, CHAR_SPEED, false, engine);
		int w = engine.width();
		int h = engine.height();
		int middle = w / 2;
		
		result.charBox().init((middle + (spacing/2)), h - CHAR_H_HEIGHT, CHAR_H_WIDTH, CHAR_H_HEIGHT);
		
		return result;
	}
}
