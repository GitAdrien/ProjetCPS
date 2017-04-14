package interfaceService;

public interface Hitbox {
	/* Observator */
	public int positionX(Hitbox h);
	public int positionY(Hitbox h);
	public boolean belongsTo(Hitbox h, int a, int b);
	public boolean collidesWith(Hitbox h1, Hitbox h2);
	public boolean equalsTo(Hitbox h1, Hitbox h2);

	/* Invariants */
	// \inv : (min) CollidesWith(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) \and BelongsTo(h2, x, y)
	// \inv : (min) EqualsTo(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) = BelongsTo(h2, x, y)

	/* Constructors */
	// \post PositionX(init(x, y)) = x
	// \post PositionY(init(x, y)) = y
	public Hitbox init(int x, int y);

	/* Operators */
	// \post : PositionX(MoveTo(h, x, y)) = x
	// \post : PositionY(MoveTo(h, x, y)) = y
	// \post : \exists u, v, BelongsTo(MoveTo(h, x, y), u, v) = 
	//							BelongsTo(h, u-(x-PositionX(h)), v-(y-PositionY(h))
	public Hitbox MoveTo(Hitbox h, int x, int y);	
}
