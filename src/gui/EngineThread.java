package gui;

import enums.SimpleCommand;
import impl.EngineImpl;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;



// TODO utiliser les contrats et non directement les impémentations.
public class EngineThread implements Runnable {
	private final static long PAUSE_BETWEEN_FRAMES = 0; // was 166
	
	private EngineService engine;
	private PlayerService p1, p2;
	
	
	public EngineThread() {
		engine = new EngineImpl();
	}
	
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		engine.init(h, w, s, p1, p2);
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public EngineService getEngine() {
		return engine;
	}
	
	
	@Override
	public void run() {
		
		
		while (!engine.gameOver()) {
			engine.step(p1.getLastCommand(), p2.getLastCommand());
			try {
				Thread.sleep(PAUSE_BETWEEN_FRAMES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
