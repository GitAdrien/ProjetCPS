package interfaceservice;

public interface FrameCounterService {
	public int frame();
	public int max();
	
	public FrameCounterService init(int maxframeValue);
	
	
	
	
	public FrameCounterService nextFrame(); // frame++ en gros
	
	
	public int difference(int f); // Nombre de frame entre le conteur actuel et la valeur indiquée.
	
	
	public int difference(int current, int start);
	
}

