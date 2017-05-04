package impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.AttackCommand;
import enums.Command;
import enums.DirectionCommand;
import enums.attack.ComplexeAttackCommand;
import enums.attack.SimpleAttackCommand;
import enums.direction.ComplexeDirectionCommand;
import enums.direction.SimpleDirectionCommand;
import interfaceservice.CharacterService;
import interfaceservice.FrameCounterService;
import interfaceservice.InputManagerService;
import interfaceservice.PlayerService;

public class PlayerImpl implements PlayerService {
	private int window;
	private CharacterService character;
	private List<Command> commands;
	private InputManagerService inputManager;
	private FrameCounterService frameCounter;
	
	private int lastInput;
	private DirectionCommand lastDirection;
	private AttackCommand lastAttack;
	
	@Override
	public PlayerService init(int w, CharacterService c, InputManagerService im) {
		commands = new ArrayList<>();
		window = w;
		character = c;
		inputManager = im;
		frameCounter = c.engine().frameCounter();

		lastInput = 0;

		return this;
	}

	@Override
	public int window() {
		return window;
	}

	@Override
	public List<Command> commandsWithinWindow() {
		if (frameCounter.difference(lastInput) > window) {
			commands.clear();
		}
		return commands;
	}

	@Override
	public CharacterService character() {
		return character;
	}

	@Override
	public DirectionCommand getActiveDirection() {
		DirectionCommand result  = null;
		
		result = getComplexDirection();
		
		if (result == null)
			result = getSimpleDirection();
		
		if (!result.equals(SimpleDirectionCommand.NEUTRAL)) {
			if (frameCounter.difference(lastInput) > window) {
				commands.clear();
			}
			
//			lastInput = frameCounter.frame();
			if (result != lastDirection)
				commands.add(0, result);
		}
		
		lastDirection = result;
		
		return result;
	}

	private ComplexeDirectionCommand getComplexDirection() {
		ArrayList<SimpleDirectionCommand> activeDir = new ArrayList<>();
		
		Arrays.asList(SimpleDirectionCommand.values()).forEach(cmd -> {
			if (inputManager.isPressed(cmd))
				activeDir.add(cmd);
		});
		
		if (activeDir.size() != 2 ) // XXX : Ne prend pas en compte quand il y a plus de trois input de direction simultanés.
			return null;
		
		for (ComplexeDirectionCommand cdc : ComplexeDirectionCommand.values()) {
			if (cdc.getC1() == activeDir.get(0) || cdc.getC2() == activeDir.get(0) &&
					cdc.getC1() == activeDir.get(1) || cdc.getC2() == activeDir.get(1)) {
				return cdc;
			}
		}
		
		
		
		return null;
	}
	
	
	private SimpleDirectionCommand getSimpleDirection() {
		SimpleDirectionCommand result = SimpleDirectionCommand.NEUTRAL;

		for (SimpleDirectionCommand cm : SimpleDirectionCommand.values()) {
			if (inputManager.isPressed(cm) && cm.getPriority() > result.getPriority()) {
				result = cm;
			}
		}
		
		return result;
	}
	
	@Override
	public InputManagerService inputManager() {
		return inputManager;
	}
	

	@Override
	public AttackCommand getActiveAttack() {
		if (character.usingTechnic())
			return SimpleAttackCommand.NONE;
		
		
		AttackCommand result = null;
		
		result = getComplexAttack();
		
		if (result == null)
			result = getSimpleAttack();
		
		
		if (!result.equals(SimpleAttackCommand.NONE)) {
			if (frameCounter.difference(lastInput) > window) {
				commands.clear();
			}
			
			lastInput = frameCounter.frame();
			
			if (lastAttack != result)
				commands.add(0, result);
		}
		
		lastAttack = result;
		
		return result;
	}
	
	private ComplexeAttackCommand getComplexAttack() {
		ArrayList<SimpleAttackCommand> activeAtt = new ArrayList<>();
		
		Arrays.asList(SimpleAttackCommand.values()).forEach(cmd -> {
			if (inputManager.isPressed(cmd))
				activeAtt.add(cmd);
		});
		
		if (activeAtt.size() != 2 ) // XXX : Ne prend pas en compte quand il y a plus de trois input de direction simultanés.
			return null;
		
		for (ComplexeAttackCommand cdc : ComplexeAttackCommand.values()) {
			if (cdc.getC1() == activeAtt.get(0) || cdc.getC2() == activeAtt.get(0) &&
					cdc.getC1() == activeAtt.get(1) || cdc.getC2() == activeAtt.get(1)) {
				return cdc;
			}
		}
		
		return null;
	}
	
	private SimpleAttackCommand getSimpleAttack() {
		SimpleAttackCommand result = SimpleAttackCommand.NONE;

		for (SimpleAttackCommand cm : SimpleAttackCommand.values()) {
			if (inputManager.isPressed(cm) && cm.getPriority() > result.getPriority()) {
				result = cm;
			}
		}
		
		return result;
	}

}
