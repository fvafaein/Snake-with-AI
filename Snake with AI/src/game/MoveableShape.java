package game;

import java.awt.Graphics2D;

/**The MoveableShape interface.
 * @author Farzad
 *
 */
public interface MoveableShape {
	/**
	 * Moves the creature (overridden by each subclasses)
	 */
	
	void move(); 
	/**
	 * @param other the other creature being compared to.
	 * @return true if collided, else false.
	 */
	
	boolean collide(MoveableShape other); 
	/**Overridden method by each subclass of creature to draw graphics.
	 * @param g2 Graphics2D instance to be drawn
	 */
	
	void draw(Graphics2D g2); 
}
