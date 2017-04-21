package interfaceservice;

import java.util.List;

import enums.Command;
import enums.SimpleCommand;

public interface CombinaisonCommandService {
	/* Observator */
	public List<SimpleCommand> commands(); 
	public List<Command> complexeCommands();
	public int player();
	
	
	
	/* Invariants */
	
	
	/* Constructors */
	// \pre  : p >= 0 \and p < 2
	// \post : player(init(p)) = p
	// \post : commands(init(p)) = \empty
	// \post : complexeCommands(init(p)) = \empty
	public CombinaisonCommandService init(int p);
	
	/* Operators */
	
	// \post TODO 
	public void parseCommands();
	
	// \post addCommand(ccs, c) \implique c \in commands(ccs)
	public void addCommand(SimpleCommand c);
	
}
