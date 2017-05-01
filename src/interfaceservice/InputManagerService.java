package interfaceservice;

import enums.SimpleCommand;

public interface InputManagerService {
	
	public InputManagerService init();
	
	public void setPressed(SimpleCommand cmd);
	
	public void setReleased(SimpleCommand cmd);
	
	public boolean isPressed(SimpleCommand cmd);
	
}
