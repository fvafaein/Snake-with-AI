package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 * @author Farzad
 * This is the red creature (extends Creature.java) which follows the Snake.
 */
public class BadGuy extends Creature {
	private int count;
	private double size;
	
	/**
	 * creates a red badguy which has init size 10 and a counter of 0
	 */
	public BadGuy(){
		size=10;
		count=0;
	}
	
	/**
	 * @param rad This constructor is when a radius is given.
	 */
	public BadGuy(double rad){
		size=rad;
		count=0;
	}
	
	/* (non-Javadoc)
	 * @see game.Creature#move()
	 * Moves the red creature close to snake's head when it is closer than 40 distance, also the creature continually gets bigger till size 70.
	 */
	public void move(){
		Point other = Game.snake.getHead();
		Point point = super.getPoint();
		int realSpeed=1;
		if(count%5==0){
		if(other.distance(point)<40){
			if(other.getX()>point.getX()) point.translate(realSpeed,0);
			if(other.getY()>point.getY()) point.translate(0, realSpeed);	
			if(other.getX()<point.getX()) point.translate(-realSpeed,0);
			if(other.getY()<point.getY()) point.translate(0, -realSpeed);
			
			if(count%20==0&&size<70){
				size++;
		}
		}	
		
		}
		
	}
	
	/* (non-Javadoc)
	 * @see game.Creature#draw(java.awt.Graphics2D)
	 * Overrides creature class' draw method, and draws the red circled enemy.
	 */
	public void draw(Graphics2D g2){
		  g2.setColor(Color.RED);
		  Ellipse2D.Double circle = new Ellipse2D.Double(super.getPoint().x * Game.SCALE, super.getPoint().y * Game.SCALE, size, size);
		  g2.fill(circle);
	}
	
	/**
	 * Halves the size of the enemy circle (for when it's hit)
	 */
	public void halfSize(){
		size=size/2;
	}
	
	/**
	 * @return the size of the circle
	 */
	public double getSize(){
		return size;
	}
	
	/* (non-Javadoc)
	 * @see game.Creature#collide(game.MoveableShape)
	 * Checks to see if there is a collision with the snake head.
	 * @return true or false depending on collision
	 */
	public boolean collide(MoveableShape other){
		//System.out.println(super.getPoint().distance(((Snake) other).getHead()));
		if(super.getPoint().distance(((Snake) other).getHead()) < size/Game.SCALE){
			return (super.getPoint().distance(((Snake) other).getHead()) < size/Game.SCALE);
		}
		else return false;
	}
	
}
