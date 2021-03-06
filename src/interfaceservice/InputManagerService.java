package interfaceservice;

import enums.Command;

public interface InputManagerService {
	public boolean isPressed(Command cmd);
	
	public InputManagerService init();
	
	public void setPressed(Command cmd);
	
	public void setReleased(Command cmd);
	
	
}
