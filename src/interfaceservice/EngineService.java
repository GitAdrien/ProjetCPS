package interfaceservice;

import enums.Commande;

public interface EngineService {
	/* Observator */
	public int height(); //const
	public int width(); //const
	
	// \pre : character(e, i) \with i \in {1,2}
	public CharacterService character(int i);
	
	// \pre : player(e, i) \with i \in {1,2}
	public PlayerService player(int i);
	public boolean gameOver();
	
	/* Invariants */
	// \inv : (min) gameOver(e) = \exists i \in {1,2} Character::dead(player(e,i))
	
	/* Constructors */
	// \pre : init(h, w, s, p1, p2) \with h > 0 \and s > 0 \and w > s \and p1 != p2 
	// \post : height(init(h, w, s, p1, p2)) = h
	// \post : width(init(h, s, w, p1, p2) = w
	// \post : player(init(h, s, w, p1, p2), 1) = p1
	// \post : player(init(h, s, w, p1, p2), 2) = p2
	// \post : Character::positionX(character(init(h, w, s, p1, p2), 1)) = w//2 - s//2
	// \post : Character::positionX(character(init(h, w, s, p1, p2), 2)) = w//2 + s//2
	// \post : Character::positionY(character(init(h, w, s, p1, p2), 1)) = 0
	// \post : Character::positionY(character(init(h, w, s, p1, p2), 2)) = 0
	// \post : Character::faceRight(char(init(h, w, s, p1, p2), 1))
	// \post : Character::non(faceRight(char(init(h, w, s, p1, p2), 2)))
	/**
	 * @param h height
	 * @param w width
	 * @param s 
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	public EngineService init(int h, int w, int s, PlayerService p1, PlayerService p2);
	
	/* Operators */
	// \pre : step(e) \with non(gameOver(e))
	// \post : character(step(e, C1, C2), 1) = step(character(e, 1), C1)
	// \post : character(step(e, C1, C2), 2) = step(character(e, 2), C2)
	public EngineService step(Commande C1, Commande C2);	
}
