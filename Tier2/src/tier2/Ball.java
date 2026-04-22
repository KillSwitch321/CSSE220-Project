package tier2;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A ball that can be drawn and moved on the screen.
 */

public class Ball {
	
	// TODO: Add fields for position and size
	private int x;
	private int y;
	private int radius;
	private int startX;
	private int startY; 
	
	/**
	 * Creates a new ball at the given position.
	 *
	 * @param x starting x-coordinate
	 * @param y starting y-coordinate
	 * @param radius radius of the ball
	 */
	public Ball(int x, int y, int radius) {
		// TODO: Initialize fields
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.startX = x; // the initial position
		this.startY = y; // the initial position
	}
	
	/**
	 * Draws the ball.
	 *
	 * @param g2 graphics object used for drawing
	 */
	public void draw(Graphics2D g2) {
		// TODO: Draw the ball with fillOval(...)
		g2.setColor(Color.RED);
		g2.fillOval(x, y, 2*radius, 2*radius);
	}
	
	/**
	 * Shifts the ball horizontally by the given amount.
	 *
	 * @param dx amount to move in the x-direction
	 */
	
	public void shift(int dx) {
		// TODO: Update the ball's x-position
		  x += dx;
		  
		// IMPORTANT: Do NOT call repaint() here.
			// The Ball should NOT control when the screen redraws.
			// The GameComponent (panel) is responsible for repainting.
		}
	
	/**
	 * Resets the ball to its starting position.
	 */
	public void reset() {
		// TODO: Move the ball back to where it was originally created
				// Hint: Use the stored starting position (startX and startY)
				// Replace the current x and y with those values
		this.x = this.startX;
	}

}
