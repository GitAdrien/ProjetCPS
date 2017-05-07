package gui;

import contract.EngineContract;
import contract.InputManagerContract;
import contract.decorator.EngineDecorator;
import factory.TechnicsFactory;
import impl.EngineImpl;
import impl.InputManagerImpl;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.FrameCounterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;


public class EngineThread implements Runnable {
	private final static long PAUSE_BETWEEN_FRAMES = 25; 
	private final static int INPUT_WINDOW_LENGHT = 25;
	
	
	private EngineService engine;
	private EngineImpl engineI;
	
	
	private InputManagerImpl im1I, im2I;
	
	private boolean isOn;
	
	
	public EngineThread() {
		engineI = new EngineImpl();
		engine = new EngineDecorator(new EngineContract(engineI));
		
		isOn = true;
	}
	
	public void init(int h, int w, int s, PlayerService p1, PlayerService p2, FrameCounterService fc) {
		engine.init(h, w, s, p1, p2, fc);
		
		im1I = new InputManagerImpl();
		im2I = new InputManagerImpl();
		
		InputManagerService im1 = new InputManagerContract(new InputManagerContract(im1I));
		InputManagerService im2 = new InputManagerContract(new InputManagerContract(im2I));
		
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
	
	public EngineImpl getEngineI() {
		return engineI;
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
				System.out.println("Interrupt");
			}
		}
		
		System.out.println("End");
	}
	
	
	private void addTechnics(CharacterService c) {
		c.addTechnic(TechnicsFactory.newKick(0, (int) (c.charBox().height()*0.5)));
		c.addTechnic(TechnicsFactory.newPunch(0, (int) (c.charBox().height()*0.15)));
		c.addTechnic(TechnicsFactory.newHeavyPunch(0, (int) (c.charBox().height()*0.15)));
	}
	
	
}
