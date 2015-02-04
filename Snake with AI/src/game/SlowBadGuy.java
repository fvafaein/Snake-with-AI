package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author Farzad
 *This is the slow bad guy, which move random/retreats from snake.
 */
public class SlowBadGuy extends Creature {
	
	
  /* (non-Javadoc)
 * @see game.Creature#move()
 * moves the green slower bad guy away from the snake.
 */
public void move(){
	  Point other = Game.snake.getHead();
	  Point point = super.getPoint();
		int speed = 1;
		
		if (point.x>70) point.translate(-speed, 0);
		if (point.x<10) point.translate(speed, 0);
		if (point.y>20) point.translate(0, -speed);
		if (point.y<10) point.translate(0, speed);
		
		if(other.distance(point)<15) {
	  		if (point.x>78) point.translate(-speed, 0);
			if(other.getX()>point.getX()) {point.translate(-speed,0);}
			if(other.getY()>point.getY()) {point.translate(0, -speed);}	
			if(other.getX()<point.getX()) {point.translate(speed,0);}
			if(other.getY()<point.getY()) {point.translate(0, speed);}
	  	}
	}
  
  
  /* (non-Javadoc)
 * @see game.Creature#draw(java.awt.Graphics2D)
 * draws the green slower creature
 */
public void draw(Graphics2D g2){
	  g2.setColor(Color.GREEN);
	  g2.fillRect(super.getPoint().x * Game.SCALE, super.getPoint().y * Game.SCALE, Game.SCALE, Game.SCALE);
  }
  
  /* (non-Javadoc)
 * @see game.Creature#collide(game.MoveableShape)
 * @return returns true or false depending on collision
 */
public boolean collide(MoveableShape other){
		if(super.getPoint().distance(((Snake) other).getHead()) <=1){
			return (super.getPoint().distance(((Snake) other).getHead()) <=1);
		}
		else return false;
	}


}