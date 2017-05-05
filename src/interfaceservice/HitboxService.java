package interfaceservice;

public interface HitboxService {
	/* Observator */
	public int positionX();
	public int positionY();
	public int width();
	public int height();
	public boolean belongsTo(int x, int y);
	public boolean collidesWith(HitboxService h);
	public boolean equalsTo(HitboxService h);

	/* Invariants */
	// \inv : (min) CollidesWith(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) \and BelongsTo(h2, x, y)
	// \inv : (min) EqualsTo(h1, h2) = \exists x, y \in BelongsTo(h1, x, y) = BelongsTo(h2, x, y)

	/* Constructors */
	// \pre init(x, y, w, h) \require w > 0 \and h > 0 \and x >= 0 \and y >= 0
	// \post PositionX(init(x, y, w, h)) = x
	// \post PositionY(init(x, y, w, h)) = y
	// \post width(init(x, y, w, h)) = w
	// \post height(init(x, y, w, h)) = h
	public HitboxService init(int x, int y, int width, int height);

	/* Operators */
	// \pre : MoveTo(h, x, y) \require x>=0 \and y>=0
	// \post : PositionX(MoveTo(h, x, y)) = x
	// \post : PositionY(MoveTo(h, x, y)) = y
	// \post : u, v, BelongsTo(MoveTo(h, x, y), u, v) = 
	//							BelongsTo(h, u-(x-PositionX(h)), v-(y-PositionY(h))
	public HitboxService moveTo(int x, int y);	
	
	// \pre  : setWidth(hb, w) \require w > 0
	// \post : width(setWidth(hb, w)) = w
	public HitboxService setWidth(int width);
	
	// \pre  : setHeight(hb, h) \require h > 0
	// \post : width(setHeight(hb, h)) = h
	public HitboxService setHeight(int height);
	
	
	// \post : positionX(h) = positionX(copy(h))
	// \post : positionY(h) = positionY(copy(h))
	// \post : width(h) = width(copy(h))
	// \post : height(h) = height(copy(h))
	public HitboxService copy();
}
