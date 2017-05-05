package interfaceservice;

import java.util.List;

import enums.AttackCommand;
import enums.Command;
import enums.DirectionCommand;

public interface PlayerService {
	//choper des inputs
	/* Observator */
	public int window(); // zone pour commande spéciale
	public List<Command> commandsWithinWindow();
	public CharacterService character();
	public InputManagerService inputManager();
	public int lastInput();
	
	/* Invariants */
	//window() > 0
	
	/* Constructors */
	// \pre : w >= 0
	// \post : window(init(w, c)) = w
	// \post : character(init(w, c)) = c
	// \post : commandsWithinWindow(init(w,c)) = \empty
	public PlayerService init(int w, CharacterService c, InputManagerService inputManager);
	
	/* Operators */
	
	
	// \post: InputManagerService::isPressed(inputManager(p), c1) \and
    //	InputManagerService::isPressed(inputManager(p), c2) \and
    //	(\exists c, c \in ComplexeAttackCommand, (ComplexeAttackCommand::getC1(c, c1) \and ComplexeAttackCommand::getC2(c, c2)) \or
    //	(ComplexeAttackCommand::getC2(c, c1) \and ComplexeAttackCommand::getC1(c, c2))) => getActiveAttack(p) = c
	// \post: InputManagerService::isPressed(inputManager(p), c) \and c \in AttackCommand => getActiveAttack(p) = c
	public DirectionCommand getActiveDirection();
	
	// \post: InputManagerService::isPressed(inputManager(p), c1) \and
    //InputManagerService::isPressed(inputManager(p), c2) \and
    //(\exists c, c \in ComplexeDirectionCommand, (ComplexeDirectionCommand::getC1(c, c1) \and ComplexeDirectionCommand::getC2(c, c2)) \or
    //(ComplexeDirectionCommand::getC2(c, c1) \and ComplexeDirectionCommand::getC1(c, c2))) => getActiveDirection(p) = c
	// \post: InputManagerService::isPressed(inputManager(p), c) \and c \in DirectionCommand => getActiveDirection(p) = c
	public AttackCommand getActiveAttack();
	
	
}
