package interfaceservice;

import java.util.List;

import enums.Command;

public interface CharacterService {
	/* Observator */
	public int positionX();
	public int positionY();
	public EngineService engine();
	public HitboxService charBox();
	public int life();
	public int speed();
	public boolean faceRight();
	public boolean dead();
	public List<TechnicService> technics();
	

	/* Invariants */
	// \inv : positionX(c) > 0 \and positionY(c) < Engine::width(engine)
	// \inv : positionY(c) > 0 \and positionX(c) < Engine::height(engine)
	// \inv : dead(c) = non(life() > 0)

	/* Constructors */
	// \pre init(l, s, f, e) \with l > 0 \and s > 0
	// \post : life(init(l, s, f, e)) = l
	// \post : speed(init(l, s, f, e)) = s
	// \post : faceRight(init(l, s, f, e)) = f
	// \post : engine(init(l, s, f, e)) = e
	// \post : \exists h \in Hitbox \with charBox(init(l, s, f, e)) = h
	/**
	 * @param l life
	 * @param s speed
	 * @param f faceRight
	 * @param e engine
	 */
	public CharacterService init(int l, int s, boolean f, EngineService e);

	/* Operators */
	// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
	//				Hitbox::collidesWith(charBox(moveLeft(c)), charBox(Engine::player(engine(c), i)))
	//				\implique positionX(moveLeft(c)) = positionX(c)
	// \post : positionX(c) <= speed(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = 0
	// \post positionX(c) > speed(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = positionX(c) - speed(c)
	// \post faceRight(moveLeft(c)) = faceRight(c) \and life(moveLeft(c)) = life(c)
	// \post positionY(moveLeft(c)) = positionY(c)
	public CharacterService moveLeft();

	// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
	//				Hitbox::collidesWith(charBox(moveRight(c)), charBox(Engine::player(engine(c), i)))
	//				\implique positionX(moveRight(c)) = positionX(c)
	// \post Engine::width(engine(c)) <= speed(c) + positionX(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveRight(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveRight(c)) = positionX(c) + speed(c)
	// \post Engine::width(engine(c)) > speed(c) + positionX(c) \and
	//			(\exists i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveRight(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveRight(c)) = Engine::width(engine(c))
	// \post faceRight(moveLeft(c)) = faceRight(c) \and life(moveRight(c)) = life(c)
	// \post positionY(moveLeft(c)) = positionY(c)
	public CharacterService moveRight();

	// \post faceRight(switchSide(c)) != faceRight(c)
	// \post positionX(switchSide(c)) = positionX(c)
	public CharacterService switchSide();

	// \pre : step(c) \require \not dead(c)
	// \post : step(c, LEFT) = moveLeft(c)
	// \post : step(c, RIGHT) = moveRight(c)
	// \post : step(c, NEUTRAL) = c
	// TODO ajouté les commandes avancées 
	public Character step(Command com);
	
	// \pre : addTechnic(c, t) \require t \not \in technics(c) //TODO je suis pas convaicu
	// \post addTechnic(c, t) \implique t \in technics(c)
	public void addTechnic(TechnicService t);
	
}
