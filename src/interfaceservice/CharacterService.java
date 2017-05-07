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
	public int maxLife();
	public int speed(); //const
	public int gravity();
	public int jumpSpeed();
	public List<TechnicService> technics();
	
	public boolean faceRight();
	public boolean dead();
	public boolean crouched();
	public boolean stunned();
	public boolean usingTechnic();
	public boolean jumping();
	public boolean blocking();

	public HitboxService currentTechnicHitbox();

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
	 * @param g gravity : vitesse du saut en gros
	 * @param js jumpSpeed
	 * @param f faceRight
	 * @param e engine
	 */
	public CharacterService init(int l, int s, int g, int js, boolean f, EngineService e);

	/* Operators */
	// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
	//				Hitbox::collidesWith(charBox(moveLeft(c)), charBox(Engine::player(engine(c), i)))
	//				\implique positionX(moveLeft(c)) = positionX(c)
	// \post : positionX(c) <= speed(c) \and
	//			(\some i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = 0
	// \post positionX(c) > speed(c) \and
	//			(\some i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveLeft(c)) = positionX(c) - speed(c)
	// \post faceRight(moveLeft(c)) = faceRight(c) \and life(moveLeft(c)) = life(c)
	// \post positionY(moveLeft(c)) = positionY(c)
	public CharacterService moveLeft();

	// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
	//				Hitbox::collidesWith(charBox(moveRight(c)), charBox(Engine::player(engine(c), i)))
	//				\implique positionX(moveRight(c)) = positionX(c)
	// \post Engine::width(engine(c)) <= speed(c) + positionX(c) \and
	//			(\some i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveRight(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveRight(c)) = positionX(c) + speed(c)
	// \post Engine::width(engine(c)) > speed(c) + positionX(c) \and
	//			(\some i \with Engine::player(engine(c), i) != c \implique  
	//				non(Hitbox::collidesWith(hitbox(moveRight(c)),charBox(Engine::player(engine(c),i))))
	//			\implique positionX(moveRight(c)) = Engine::width(engine(c))
	// \post faceRight(moveRight(c)) = faceRight(c) \and life(moveRight(c)) = life(c)
	// \post positionY(moveRight(c)) = positionY(c)
	public CharacterService moveRight();

	// \post faceRight(switchSide(c)) != faceRight(c)
	// \post positionX(switchSide(c)) = positionX(c)
	public CharacterService switchSide();

	// \post :  \no(crouched(c)) \implique ((positionX(crouch(c) = positionX(c)) \and (positionY(crouch(c)) = positionY(c) - Hitbox::height(charBox(c))/2) \and
	// Hitbox::height(charBox(c))/2 = height(charBox(crouch(c)))
	// \post : (crouched(c)) \implique c = crouch(c)
	public CharacterService crouch();
	
	// \post : crouched(c) \implique ((positionX(standUp(c) = positionX(c)) \and (positionY(standUp(c)) = positionY(c) + Hitbox::height(charBox(c))) \and
	// Hitbox::height(charBox(c))*2 = Hitbox::height(charBox(standUp(c)))
	// \post : \no(crouched(c)) \implique c = standUp(c)
	public CharacterService standUp();

	// TODO direction : -1=gauche, 0=pas de direction, 1=droite
	// \post : \some i \in {-1, 0, 1} jumping(c) \implique c = jump(c, i)
	public CharacterService jump(int direction);
	
	// TODO
	public CharacterService useTechnic(List<Command> commands);
	
	// TODO Permet de decaler le joueur en cas de collision avec un autre joueur.
	//collision venant de la gauche
	// \post : positionX(bumpLeft(c)) = min(Engine::width, positionX(C) + speed(C)/2) 
	public CharacterService bumpLeft();
	
	// TODO
	// \post : positionX(bumpRight(c)) = max(0, positionX(C) - speed(C)/2) 
	public CharacterService bumpRight();
	
	
	// \pre : step(c) \require \not dead(c)
	// \post : step(c, LEFT) = moveLeft(c)
	// \post : step(c, RIGHT) = moveRight(c)
	// \post : step(c, NEUTRAL) = c
	// \post : step(c, DOWN) = crouch(c)
	// \post : step(c, UP) = jump(c, 0)
	// \post : step(c, DOWN_LEFT) = crouch(c)
	// \post : step(c, DOWN_RIGHT) = crouch(c)
	// \post : step(c, UP_LEFT) = jump(c, -1)
	// \post : step(c, UP_RIGHT) = jump(c, 1)
	//TODO : commande d'attaque???
	public CharacterService step(Command com);
	
	// TODO
	public CharacterService stepState();
	
	// \pre : addTechnic(c, t) \require t \not \in technics(c) //TODO je suis pas convaicu
	// \post addTechnic(c, t) \implique t \in technics(c)
	public void addTechnic(TechnicService t);

	// \pre : takeDamages(a, s) \require a >=0 \and s >= 0
	// \post : life(takeDamages(c, a, s)) = life(c) - a
	// \post : ((s > 0) \and stunned(takeDamages(c, a, s))) = true
	//TODO
	public CharacterService takeDamages(int amount, int stun);
	
	
	// TODO
	public CharacterService block();
}
