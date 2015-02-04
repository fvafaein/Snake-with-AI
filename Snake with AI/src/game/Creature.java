package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

/**
 * @author Farzad
 * Creature super class which implements MoveableShape interface.
 */
public class Creature implements MoveableShape {

	private Random random;
	private Point point;

	/**
	 * Creates a new random which is used in making a random Point on screen.
	 */
	public Creature(){
		random = new Random();
		point = new Point(random.nextInt(79), random.nextInt(66));
	}
	
	/* (non-Javadoc)
	 * @see game.MoveableShape#move()
	 */
	public void move() {
	}

	/**
	 * @return returns the Point (java.awt.Point)
	 */
	public Point getPoint(){
		return point;
	}
	
	
	
	/* (non-Javadoc)
	 * @see game.MoveableShape#collide(game.MoveableShape)
	 */
	public boolean collide(MoveableShape other) {
		return false;
	}

	
	/* (non-Javadoc)
	 * @see game.MoveableShape#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g2) {
			
	}

}
