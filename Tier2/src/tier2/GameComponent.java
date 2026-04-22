package tier2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * A custom game drawing area.
 * 
 * This component is responsible for drawing simple game objects.
 */

public class GameComponent extends JPanel {

	// TODO: Create a Ball object
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.BLACK;

	public GameComponent() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(BG); // note this only works with JPanels and will not work with JComponents
		this.setOpaque(true); // we want our own background here. If false - it will be see-through
		
		// TODO Initialize Ball with these values
		// ball = new Ball(WIDTH/2,100,14);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(FG);
		
		// TODO: Draw the ball here
	}
	
	
    /**
	* Moves the ball left or right.
	*/
	
	public void move(int x) {
		// TODO: Move the ball horizontally by the given amount (x)
		// Hint: call the ball's shift(...) method\
		
		repaint();
	}
	
	/**
	 * Moves the ball to its starting position
	 */
	
	public void center() {
		// TODO: Reset the ball to its starting position

		repaint();
	}
	
	
}
