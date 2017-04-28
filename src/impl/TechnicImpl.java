package impl;

import java.util.List;

import enums.Command;
import interfaceservice.HitboxService;
import interfaceservice.TechnicService;

public class TechnicImpl implements TechnicService {
	private String name;
	private List<Command> commands;
	private int damage;
	private HitboxService hitbox;
	
	
	@Override
	public TechnicService init(String n, List<Command> c, int d, HitboxService h) {
		name = n;
		commands = c; // XXX Peut être transvaser plutot que copier ?
		damage = d;
		hitbox = h;
		
		return this;
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public List<Command> commands() {
		return commands;
	}

	@Override
	public int damage() {
		return damage;
	}

	@Override
	public HitboxService hitbox() {
		return hitbox;
	}


}
