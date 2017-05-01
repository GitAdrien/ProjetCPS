package factory;

import java.util.Arrays;

import enums.Command;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;
import impl.TechnicImpl;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class TechnicsFactory {
	public static TechnicService newPunch(int x, int y) {
		return newTechnic("Punch coco", 5, HitboxFactory.newHitbox(x, y, 25, 25), SimpleAttackCommand.PUNCH);
	}
	
	public static TechnicService newKick(int x, int y) {
		return newTechnic("Kick kat", 10, HitboxFactory.newHitbox(x, y, 50, 25), SimpleAttackCommand.KICK);
	}
	
	
	private static TechnicService newTechnic(String name, int damage, HitboxService hitbox, Command... controls) {
		TechnicService result = new TechnicImpl();
		
		result.init(name, Arrays.asList(controls), damage, hitbox);
		
		return result;
	}
}
