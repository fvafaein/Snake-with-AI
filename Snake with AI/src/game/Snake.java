package game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Farzad
 *This is the Snake class which is the predator you control
 */
public class Snake extends Creature {
	public static int UP = 0, DOWN=1, LEFT=2, RIGHT=3, SCALE=10;
	private int direction;
	private Point head;
	private ArrayList<Point> snakeParts;
	private int tail;
	private boolean gameOver;
	
	/**A new snake is created, with direction being down.
	 * @param x the size of inital tail
	 */
	public Snake(int x){
		super.getPoint().setLocation(5,5);
		head = super.getPoint();
		direction = DOWN;
		snakeParts = new ArrayList<Point>();
		tail=x;
		gameOver=false;
		
	}
	
	/**
	 * @return the Point where the head of snake is.
	 */
	public Point getHead(){
		return head;
	}
	
	/**
	 * @return the direction of snake (as an int 0 1 2 3)
	 */
	public int getDir(){
		return direction;
	}
	
	/* (non-Javadoc)
	 * @see game.Creature#move()
	 * Moves the snake depending on direction and not on edges (game over) and not on hitting tail (Gameover)
	 */
	public void move(){
		
		snakeParts.add(new Point(head.x, head.y));
		if(head!=null){
            if (direction == UP)
                    if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                            head = new Point(head.x, head.y - 1);
                    else
                            gameOver(true);
            if (direction == DOWN)
                    if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
                            head = new Point(head.x, head.y + 1);
                    else
                    	    gameOver(true);
            if (direction == LEFT)
                    if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                            head = new Point(head.x - 1, head.y);
                    else
                    	gameOver(true);
            if (direction == RIGHT)
                    if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y))
                            head = new Point(head.x + 1, head.y);
                    else
                    	gameOver(true);
            if (snakeParts.size() > tail)
                    snakeParts.remove(0);
           }	
	}
	
	
/**
 * @return if the game is over or not (snake hit himself or border)
 */
public boolean isOver(){
	return gameOver;
}
	
/**
 * @param over sets gameOver to true if the game is over
 */
public void gameOver(boolean over){
	if(over){
		gameOver=true;
	}
	else gameOver=false;
}	
	
/* (non-Javadoc)
 * @see game.Creature#draw(java.awt.Graphics2D)
 * This draws the snake.
 */
public void draw(Graphics2D g2){
	Color gray = new Color(3552822);
	
	g2.setColor(gray);
	for (Point point : snakeParts)
	{
		g2.fillRect(point.x * Game.SCALE, point.y * Game.SCALE, Game.SCALE, Game.SCALE);
	}
	g2.fillRect(head.x * Game.SCALE, head.y * Game.SCALE,Game.SCALE, Game.SCALE);
}
	
/**
 * @param x the x cord of snake head
 * @param y the y cord of the snake head
 * @return true if the snake hit his own tail
 */
public boolean noTailAt(int x, int y) {
    for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                    return false;
            }
    }
    return true;
}

/**
 * @param point add new Point to snake array (makes it longer)
 */
public void addTail(Point point) {
	snakeParts.add(point);
}


/**Changes direction if using mouse
 * @param string "left" for leftclick (goes counter clockwise) and vice versa for right click.
 */
public void changeDir(String string) {
	if(string=="left"){
		if(direction==DOWN) {direction=RIGHT;}
		else if(direction==LEFT) {direction=DOWN;}
		else if(direction==UP) {direction=LEFT;}
		else if(direction==RIGHT) {direction=UP;}
	}
	if(string=="right")
	{
			if(direction==DOWN){direction=LEFT;}
			else if(direction==LEFT){direction=UP;}
			else if(direction==UP){direction=RIGHT;}
			else if(direction==RIGHT){direction=DOWN;}
	}
	
}

/**
 * @param dir sets the direction
 */
public void setDir(int dir) {
	direction = dir;	
}
	

}


