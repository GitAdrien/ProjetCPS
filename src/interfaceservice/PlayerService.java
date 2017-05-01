package interfaceservice;

import java.util.List;

import enums.Command;
import enums.SimpleCommand;

public interface PlayerService {
	//choper des inputs
	/* Observator */
	public int window(); // zone pour commande spéciale
	public List<Command> commandsWithinWindow();
	public CharacterService character();
	public InputManagerService inputManager();
	
	/* Invariants */
	//TODO
	
	/* Constructors */
	// \pre : w >= 0
	// \post : window(init(w, c)) = w
	// \post : character(init(w, c)) = c
	// \post : commandsWithinWindow(init(w,c)) = \empty
	public PlayerService init(int w, CharacterService c, InputManagerService inputManager);
	
	/* Operators */
	
	public SimpleCommand getActiveCommand();
	
	
	// TODO
	// TODO
}
