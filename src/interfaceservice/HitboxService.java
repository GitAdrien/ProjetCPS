package interfaceservice;

public interface HitboxService {
	/* Observator */
	public int positionX();
	public int positionY();
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService h);
	public boolean equalsTo(HitboxService h);

	/* Invariants */
	// \inv : (min) CollidesWith(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) \and BelongsTo(h2, x, y)
	// \inv : (min) EqualsTo(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) = BelongsTo(h2, x, y)

	/* Constructors */
	// \post PositionX(init(x, y)) = x
	// \post PositionY(init(x, y)) = y
	public HitboxService init(int x, int y);

	/* Operators */
	// \post : PositionX(MoveTo(h, x, y)) = x
	// \post : PositionY(MoveTo(h, x, y)) = y
	// \post : \exists u, v, BelongsTo(MoveTo(h, x, y), u, v) = 
	//							BelongsTo(h, u-(x-PositionX(h)), v-(y-PositionY(h))
	public HitboxService moveTo(int x, int y);	
	
	
	
	public HitboxService clone();
}
