package Tier4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A custom game drawing area.
 * 
 * This component is responsible for drawing simple game objects.
 */

public class GameComponent extends JPanel {

	// TODO: Create a Ball object
	private Ball ball;

	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.BLACK;
	BufferedImage background;
	private Timer timer;
	
	public GameComponent() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(BG); // note this only works with JPanels and will not work with JComponents
		this.setOpaque(true); // we want our own background here. If false - it will be see-through
	
		ball = new Ball(WIDTH/2,100,14);
		
		try {
			background = ImageIO.read(this.getClass().getResource("background.png"));
		} catch (IOException | IllegalArgumentException e) {
			background = null;
		}
		
		timer = new Timer(30, e -> {
		    ball.update();
		    repaint();
		  });
		
		timer.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if (background!= null) {
			g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		} else {
		g2.setColor(FG);
		}
		// TODO: Draw the ball here
		ball.draw(g2);
	}
	
    /**
	* Moves the ball.
	*/
	
	public void move(int x) {
		// TODO: Move the ball horizontally by the given amount (x)
		// Hint: call the ball's shift(...) method
		ball.shift(x);
		repaint();
	}
	
	/**
	 * Moves the ball to its starting position
	 */
	public void center() {
		// TODO: Reset the ball to its starting position
		ball.reset();
		repaint();
	}	
}
