package interfaceservice;

import java.util.List;

import enums.Command;
import enums.direction.SimpleDirectionCommand;

public interface CombinaisonCommandService {
	/* Observator */
	public List<SimpleDirectionCommand> commands(); 
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
	
	// \post addCommand(ccs, c) \implique c \in commands(ccs)
	public void addCommand(SimpleDirectionCommand c);
	
}
