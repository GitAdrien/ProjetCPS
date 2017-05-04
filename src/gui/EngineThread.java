package gui;

import factory.TechnicsFactory;
import impl.EngineImpl;
import impl.InputManagerImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.PlayerService;



// TODO utiliser les contrats et non directement les impémentations.
public class EngineThread implements Runnable {
	private final static long PAUSE_BETWEEN_FRAMES = 25; 
	private final static int INPUT_WINDOW_LENGHT = 25;
	
	
	private EngineService engine;
	
	private boolean isOn;
	
	
	public EngineThread() {
		engine = new EngineImpl();
		isOn = true;
	}
	
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2, FrameCounterService fc) {
		engine.init(h, w, s, p1, p2, fc);
		
		InputManagerImpl im1 = new InputManagerImpl();
		InputManagerImpl im2 = new InputManagerImpl();
		
		im1.init();
		im2.init();
		
		p1.init(INPUT_WINDOW_LENGHT, engine.character(0), im1);
		p2.init(INPUT_WINDOW_LENGHT, engine.character(1), im2);
		
		addTechnics(engine.character(0));
		addTechnics(engine.character(1));
		
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
			engine.step();
			try {
				Thread.sleep(PAUSE_BETWEEN_FRAMES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void addTechnics(CharacterService c) {
		c.addTechnic(TechnicsFactory.newKick(0, (int) (c.charBox().height()*0.5)));
		c.addTechnic(TechnicsFactory.newPunch(0, (int) (c.charBox().height()*0.15)));
		c.addTechnic(TechnicsFactory.newHeavyPunch(0, (int) (c.charBox().height()*0.15)));
	}
	
	
}
