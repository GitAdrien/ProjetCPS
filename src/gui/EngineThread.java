package gui;

import impl.EngineImpl;
import impl.InputManagerImpl;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;



// TODO utiliser les contrats et non directement les impémentations.
public class EngineThread implements Runnable {
	private final static long PAUSE_BETWEEN_FRAMES = 50; // was 166
	private final static int INPUT_WINDOW_LENGHT = 10;
	
	
	private EngineService engine;
	private PlayerService p1, p2;
	
	private boolean isOn;
	
	
	public EngineThread() {
		engine = new EngineImpl();
		isOn = true;
	}
	
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2) {
		engine.init(h, w, s, p1, p2);
		this.p1 = p1;
		this.p2 = p2;
		
		InputManagerImpl im1 = new InputManagerImpl();
		InputManagerImpl im2 = new InputManagerImpl();
		
		im1.init();
		im2.init();
		
		p1.init(INPUT_WINDOW_LENGHT, engine.character(0), im1);
		p2.init(INPUT_WINDOW_LENGHT, engine.character(1), im2);
		
	}
	
	public EngineService getEngine() {
		return engine;
	}
	
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	@Override
	public void run() {
		
		while (isOn && !engine.gameOver()) {
			engine.step(p1.getActiveCommand(), p2.getActiveCommand());
			try {
				Thread.sleep(PAUSE_BETWEEN_FRAMES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
