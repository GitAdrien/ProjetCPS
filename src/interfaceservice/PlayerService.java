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
	
	/* Invariants */
	//TODO
	
	/* Constructors */
	// \pre : w >= 0
	// \post : window(init(w, c)) = w
	// \post : character(init(w, c)) = c
	// \post : commandsWithinWindow(init(w,c)) = \empty
	public PlayerService init(int w, CharacterService c);
	
	/* Operators */
	
	public SimpleCommand getLastCommand();
	public void addCommand(SimpleCommand c);
	
	
	// TODO
	// TODO
}
