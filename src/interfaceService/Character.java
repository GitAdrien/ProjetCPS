package interfaceService;

public interface Character {
	/* Observator */
	public int positionX(Character c);
	public int positionY(Character c);
	public Engine engine(Character c);
	public Hitbox charBox(Character c);
	public int life(Character c);
	public int speed(Character c);
	public boolean faceRight(Character c);
	public boolean dead(Character c);

	/* Invariants */
	// \inv : positionX(c) > 0 \and positionY(c) < Engine::width(engine)
	// \inv : positionY(c) > 0 \et positionX(c) < Engine::height(engine)
	// \inv : dead(c) = non(life > 0)

	/* Constructors */
	// \pre init(l, s, f, e) \with l > 0 \et s > 0
	// \post : life(init(l, s, f, e)) = l
	// \post : speed(init(l, s, f, e)) = s
	// \post : faceRight(init(l, s, f, e)) = f
	// \post : engine(init(l, s, f, e)) = e
	// \post : \exists h \in Hitbox \with charBox(init(l, s, f, e)) = h
	public Character init(int l, int s, boolean f, int e);

	/* Operators */
	// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
	//				Hitbox::collidesWith(charBox(moveLeft(c)), charBox(Engine::player(engine(c), i)))
	//				\implique positionX(moveLeft(c)) = positionX(c)
	// \post positionX(c) <= speed(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = positionX(c) - speed(c)
	// \post positionX(c) > speed(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = 0
	// \post faceRight(moveLeft(c)) = faceRight(c) \and life(moveLeft(c)) = life(c)
	// \post positionY(moveLeft(c)) = positionY(c)
	public Character moveLeft(Character c);
	
	//TODO
	public Character moveRight(Character c);
	
	// \post faceRight(switchSide(c)) != faceRight(c)
	// \post positionX(switchSide(c)) = positionX(c)
	public Character switchSide(Character c);
	
	// \post step(c, LEFT) = moveLeft(c)
	// \post step(c, RIGHT) = moveRight(c)
	// \post step(C, NEUTRAL) = c
}
