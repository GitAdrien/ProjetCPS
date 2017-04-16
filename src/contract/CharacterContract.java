package contract;

import contract.decorator.CharacterDecorator;
import contract.errors.InvariantError;
import contract.errors.PostConditionError;
import contract.errors.PreconditionError;
import interfaceservice.Character;
import interfaceservice.Engine;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(Character service) {
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
	public Character init(int l, int s, boolean f, Engine e) {
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
	public Character switchSide() {
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
	public Character moveLeft() {
		
		
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

		
		// \post : (\exists i \with Engine::player(engine(c),i) != c \and  
		//				Hitbox::collidesWith(charBox(moveLeft(c)), charBox(Engine::player(engine(c), i)))
		//				\implique positionX(moveLeft(c)) = positionX(c)
		// TODO ne pas bouger quand il y a collision avec un autre joueur.
		
		// \post positionX(c) <= speed(c) \and
		//			(\exists i \with Engine::player(engine(c), i) != c \implique  
		//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
		//			\implique positionX(moveLeft(c)) = positionX(c) - speed(c)
		// TODO bouger quand il n'y a pas de collision.
		
		// \post positionX(c) > speed(c) \and
		//			(\exists i \with Engine::player(engine(c), i) != c \implique  
		//				non(Hitbox::collidesWith(hitbox(moveLeft(c)),charBox(Engine::player(engine(c),i))))
		//			\implique positionX(moveLeft(c)) = 0
		// TODO Se bloquer à 0 losque le mouvement nous fait se déplacer à une valeur < 0.
		
		
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
