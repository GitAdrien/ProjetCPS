package factory;

import impl.HitboxImpl;
import interfaceservice.HitboxService;

public class HitboxFactory {
	public static HitboxService newHitbox(int x, int y, int width, int height) {
		HitboxService result = new HitboxImpl();
		
		result.init(x, y, width, height);
		return result;
	}
}
