package interfaceService;

public interface Engine {
	/* Observator */
	public int height(Engine e); //const
	public int width(Engine e); //const
	
	// \pre : character(e, i) \with i \in {1,2}
	public Character character(Engine e, int i);
	
	// \pre : player(e, i) \with i \in {1,2}
	public Player player(Engine e, int i);
	public boolean gameOver(Engine e);
	
	/* Invariants */
	// \inv : (min) gameOver(e) = \exists i \in {1,2} Character::dead(player(e,i))
	
	/* Constructors */
	// \pre : init(h, w, s, p1, p2) \with h > 0 \and s > 0 \et w > s \et p1 != p2 
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
	public Engine init(int h, int w, int s, Player p1, Player p2);
	
	/* Operators */
	// \pre : step(e) \with non(gameOver(e))
	// \post : character(step(e, C1, C2), 1) = step(character(e, 1), C1)
	// \post : character(step(e, C1, C2), 2) = step(character(e, 2), C2)
	public Engine step(Engine e, Commande C1, Commande C2);	
}
