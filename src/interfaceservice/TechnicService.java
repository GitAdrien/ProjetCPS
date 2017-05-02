package interfaceservice;

import java.util.List;

import enums.Command;

public interface TechnicService {
	/* Observators */
	public String name();
	public List<Command> commands();
	public int damage();
	public HitboxService hitbox();
	public int frame();
	public int stun();
	
	/* Invariants */
	
	/* Constructors */
	// \pre  : n != \empty \and c != \empty \and d >= 0
	// \post : name(init(n, c, d, h)) = n
	// \post : commands(init(n, c, d, h)) = c
	// \post : damage(init(n, c, d, h)) = d
	// \post : hitbox(init(n, c, d, h)) = h
	public TechnicService init(String n, List<Command> c, int d, int f, int s, HitboxService h);
	
}
