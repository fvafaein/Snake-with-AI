package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**Super Snake! Play with arrow keys or mouse.
 * @author Farzad Vafaeinezhad 500583434
 * @version 2.0
 */
public class Game implements ActionListener, MouseListener, KeyListener {

	public JFrame jframe;
	public GamePanel gamePanel;
	public Timer timer = new Timer(20,this);
	ArrayList<Creature> creature = new ArrayList<Creature>();
	public static Game game;
	public static int SCALE=10;
	public static int count = 0;
	public static int highScore = 0;
	public static int ticks;
	public static int time, score, tail;
	public Random random;
	public static Dimension dim;
	public static boolean paused, gameStart, gameOver;
	public static CPSound sound = new CPSound();
	public static Snake snake;
	
	/**
	 * Create the JFrame and then starts the game.
	 */
	public Game() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Super Snake");
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setSize(805,700);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		//jframe.setLocationRelativeTo(null);
		jframe.add(gamePanel = new GamePanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		start();
				
	}
	
/**
 * Creates a fresh game
 */
public void start(){
	creature.clear();
	time=0;
	tail=10;
	ticks=0;
	score=0;
	gameStart=true;
	timer.start();
	snake = new Snake(tail);
	creature.add(new BadGuy());
	for(int i=0; i<5; i++){
	creature.add(new SlowBadGuy());
	}
	gameStart=true;
	gameOver=false;
	paused=false;
}
	
/**
 * @param args
 * This main method just starts an instance of Game class, and runs the music.
 */
public static void main(String[] args){
	game = new Game();
	
	do
	if(ticks%1==0) sound.run();
	while(true);
}



/* (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */
public void actionPerformed(ActionEvent e) {
ticks++;
gamePanel.repaint();
boolean iterated=true;

if(ticks%10==0) iterated=false;

if(ticks%1==0&&!snake.isOver()&&!gameStart&&!paused){

for(Creature cre : creature){
score++;
	  	if(cre.collide((MoveableShape)snake)){
	  		
	  		if(cre.getClass()==BadGuy.class&&cre.collide((MoveableShape)snake)&&!iterated){
	  			System.out.println("hi");
	  			((BadGuy) cre).halfSize();
	  			for(int i=0; i<10; i++){
	  			snake.addTail(snake.getHead());
	  			}
	  			tail+=10;
	  			score-=1000;
	  			creature.add(new BadGuy(((BadGuy)cre).getSize()));
	  			iterated = true;
	  			break;
	  		}
	  		
	  		else if(cre.getClass()==SlowBadGuy.class&&cre.collide((MoveableShape)snake)){
	  			for(int i=0; i<5; i++){
	  			snake.addTail(snake.getHead());
	  			}
	  			tail+=5;
	  			score+=5000;
	  			creature.remove(cre);
	  			creature.add(new SlowBadGuy());
	  			break;
	  		}
	  		
	  	
	  	}
	}
}

 if(ticks%1==0&&!snake.isOver()&&!gameStart&&!paused){
	 snake.move();
	 time++;
	 
	 
 }
 if(ticks%10==0&&!snake.isOver()&&!gameStart&&!paused){
	 for(Creature cre : creature){
		 cre.move();
	 }
 }
 
}

/**
 * @return returns the highscore
 */
public int highScore(){
	if(snake.isOver()){
		if (highScore<score){
			highScore=score;
		}
	}
	return highScore;
}


/**
 * @author Farzad
 *This is the GamePanel class, embedded in Game.java, it shows the graphics.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 700);
		snake.draw(g2);
		for(Creature badguy : creature){
			 badguy.draw(g2);
		 }
		
		String string = "Score: " + score + ", Length: " + (tail) + ", Time: " + time / 20 + ", High Score: " + highScore();
		g2.setColor(Color.white);
		g2.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f),10);
		
		g2.setColor(Color.red);
		Font f = new Font("Dialog", Font.BOLD, 12);
		g2.setFont(f);
		string = "Welcome to Super Snake by Farzad! Arrowkeys/Mouse to move! Space or Click to start!";
					
			if (Game.gameStart){
				g.drawString(string,
                (int) (getWidth() / 2 - string.length() * 2.5f - 20),
                (int) Game.dim.getHeight() / 2 - 30);
				g.setColor(Color.white);
				string="Green: 5000pt + 5 tailsize || Red: -1000pt + 10 tailsize";
				g.drawString(string,
                (int) (getWidth() / 2 - string.length() * 2.5f),
                (int) Game.dim.getHeight() / 2);
				
				g.setColor(Color.red);
				string="CAUTION: Eating the red will cause bacterial dupliciation!";
				g.drawString(string,
                (int) (getWidth() / 2 - string.length() * 2.5f),
                (int) Game.dim.getHeight() / 2+20);
		     }
		
		g2.setColor(Color.cyan);
					string = "Game Over!        HighScore:" + highScore() + "       Press Space / Mouse Click for another run.";
					if (snake.isOver())
					g.drawString(string,
                    (int) (super.getWidth() / 2 - string.length() * 2.5f),
                    (int) Game.dim.getHeight() / 2);
	
	
				string = "Paused! Press Space / Mouse Click to Continue";
					if (Game.paused && !snake.isOver())
					g2.drawString(string,
                    (int) (super.getWidth() / 2 - string.length() * 2.5f),
                    (int) Game.dim.getHeight() / 2);
	}
}

/* (non-Javadoc)
 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
 * using the mouse, if you click left mouse, snake goes clockwise, and vive-versa for rightclick.(or to start game)
 */
@Override
public void mouseClicked(MouseEvent arg0) {	
	
		if(SwingUtilities.isLeftMouseButton(arg0)) {
			
			snake.changeDir("left");
				if(gameStart)
					gameStart=!gameStart;
				else
            		if (snake.isOver())
						start();
            
		} 
		if(SwingUtilities.isRightMouseButton(arg0)) {
			snake.changeDir("right");
			
			if(gameStart)
            	gameStart=!gameStart;
            		else            	
            			if (snake.isOver())
            				start();
		}
}


/* (non-Javadoc)
 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
 */
@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


/* (non-Javadoc)
 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
 */
@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


/* (non-Javadoc)
 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
 */
@Override
public void mousePressed(MouseEvent arg0) {
	
}


/* (non-Javadoc)
 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
 */
@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
 * arrow keys move snake respectively, also space starts/pauses game.
 */
@Override
public void keyPressed(KeyEvent e) {
	int i = e.getKeyCode();
    if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)  && snake.getDir() != Snake.RIGHT)
            snake.setDir(Snake.LEFT);
    if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && snake.getDir() != Snake.LEFT)
    	snake.setDir(Snake.RIGHT);
    if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP)  && snake.getDir() != Snake.DOWN)
    	snake.setDir(Snake.UP);
    if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && snake.getDir() != Snake.UP)
    	snake.setDir(Snake.DOWN);
    if (i == KeyEvent.VK_SPACE)
            if(gameStart)
            	gameStart=!gameStart;
            else
            	
    		if (snake.isOver())
                    start();
            else
                    if(paused){
                    	paused=false;
                    }
                    else paused=true;
	
}

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
 */
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

/* (non-Javadoc)
 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
 */
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}



}