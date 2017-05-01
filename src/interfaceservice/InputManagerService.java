package interfaceservice;

import enums.Command;
import enums.direction.SimpleDirectionCommand;

public interface InputManagerService {
	
	public InputManagerService init();
	
	public void setPressed(Command cmd);
	
	public void setReleased(Command cmd);
	
	public boolean isPressed(Command cmd);
	
}
