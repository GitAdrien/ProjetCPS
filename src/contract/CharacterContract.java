package contract;

import contract.decorator.CharacterDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreconditionError;
import interfaceservice.CharacterService;
import interfaceservice.EngineService;
import interfaceservice.HitboxService;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(CharacterService service) {
		super(service);
	}
	
	public void checkInvariant() {
		
		// Position > 0
		// \inv : positionX(c) > 0 \and positionY(c) < Engine::width(engine)
		// \inv : positionY(c) > 0 \et positionX(c) < Engine::height(engine)
		if (!(positionX() > 0))
			throw new InvariantError("X <= 0");
		if (!(positionY() > 0))
			throw new InvariantError("Y <= 0");
		// Position within engine size
		if (!(positionX() < engine().width()))
			throw new InvariantError("X >= engine's width");
		if (!(positionY() < engine().height()))
			throw new InvariantError("X >= engine's height");
		
		// Death status
		// \inv : dead(c) = non(life > 0)
		if (!(dead() && !(life() > 0))) 
			throw new InvariantError("Incoherent death status");
		
	}

	
	@Override
	public CharacterService init(int l, int s, boolean f, EngineService e) {
		// \pre init(l, s, f, e) \with l > 0 \and s > 0
		if (!(l > 0))
			throw new PreconditionError("l <= 0");
		if (!(s > 0))
			throw new PreconditionError("s <= 0");
		
		
		super.init(l, s, f, e);
		
		// Post-init invariant
		checkInvariant();
		
		// \post : life(init(l, s, f, e)) = l
		if (!(l == life()))
			throw new PostConditionError("l != life");
		// \post : speed(init(l, s, f, e)) = s
		if (!(s == speed()))
			throw new PostConditionError("s != speed");
		// \post : faceRight(init(l, s, f, e)) = f
		if(!(f == faceRight()))
			throw new PostConditionError("f != faceRight");
		// \post : engine(init(l, s, f, e)) = e
		if (!(e.equals(engine())))
			throw new PostConditionError("e != engine");
		// \post : \exists h \in Hitbox \with charBox(init(l, s, f, e)) = h
		if (!(charBox() != null))
			throw new PostConditionError("h == null");
		
		return this;
	}
	
	@Override
	public CharacterService switchSide() {
		// No pre
		
		// Pre invariant
		checkInvariant();
		
		// Capture
		boolean faceright_pre = faceRight();
		int x_pre = positionX();
		
		super.switchSide();
		
		// Post invariant
		checkInvariant();
		
		// \post faceRight(switchSide(c)) != faceRight(c)
		if (!(faceright_pre != faceRight()))
			throw new PostConditionError("faceRigt_pre == faceRight");
		// \post positionX(switchSide(c)) = positionX(c)
		if (!(positionX() == x_pre))
			throw new PostConditionError("x_pre != positionX");
		
		return this;
	}

	

	@Override
	public CharacterService moveLeft() {
		
		
		// Pre invariant
		checkInvariant();
		
		// Capture
		int x_pre = positionX();
		int y_pre = positionY();
		int life_pre = life();
		boolean faceRight_pre = faceRight();
		
		
		
		super.moveLeft();
		
		// Post invariant
		checkInvariant();

		
		CharacterService other;
		HitboxService newHb;
		
		if (engine().character(0).equals(this))
			other = engine().character(1);
		else
			other = engine().character(0);
		
		newHb = charBox().clone();
		
		newHb.moveTo(x_pre - speed(), y_pre);

		// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
		//				Hitbox::collidesWith(charBox(moveLeft(c)), charBox(Engine::player(engine(c), i)))
		//				\implique positionX(moveLeft(c)) = positionX(c)
		if (!(newHb.collidesWith(other.charBox()) && (positionX() == (x_pre)))) // TODO vérifier ça.
			throw new PostConditionError("moved while there was collision");
		
		// \post positionX(c) <= speed(c) \and
		//			(\exists i \with Engine::player(engine(c), i) != c \implique  
		//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
		//			\implique positionX(moveLeft(c)) = 0
		if (!(!newHb.collidesWith(other.charBox()) && (positionX() == (x_pre - speed())))) // TODO vérifier ça.
				throw new PostConditionError("hasn't moved while there was no collision");
		
		
		// \post positionX(c) > speed(c) \and
		//			(\exists i \with Engine::player(engine(c), i) != c \implique  
		//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
		//			\implique positionX(moveLeft(c)) = positionX(c) - speed(c)
		if (!((x_pre - speed() > 0) && (positionX() == 0))) // TODO vérifier ça.
			throw new PostConditionError("position x < 0 : out of bounds");
		
		
		// \post faceRight(moveLeft(c)) = faceRight(c) \and life(moveLeft(c)) = life(c)
		if (!(life_pre == life()))
			throw new PostConditionError("life_pre != life");
		if (!(faceRight_pre == faceRight()))
			throw new PostConditionError("faceRight_pre != faceRight");
		
		// \post positionY(moveLeft(c)) = positionY(c)
		if (!(y_pre == positionY()))
			throw new PostConditionError("y_pre != positionY");
		
		return this;
	}
	
	// TODO
	
	
	
}
