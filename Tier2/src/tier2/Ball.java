package tier2;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A ball that can be drawn and moved on the screen.
 */

public class Ball {
	
	// TODO: Add private fields for x,y, radius 
	
	private int startX; // we use this for storing initial values
	private int startY; // we use this for storing initial values
	/**
	 * Creates a new ball at the given position.
	 *
	 * @param x starting x-coordinate
	 * @param y starting y-coordinate
	 * @param radius radius of the ball
	 */
	public Ball(int x, int y, int radius) {
		// TODO: Initialize fields
		
		this.startX = x; // storing the starting position
		this.startY = y; // storing the starting position
		
	}
	
	/**
	 * Draws the ball.
	 *
	 * @param g2 graphics object used for drawing
	 */
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		// TODO: Draw the ball with fillOval(...)
		// fillOval uses (x, y, width, height) where width and height are 2 * radius
		

	}
	
	/**
	 * Shifts the ball horizontally by the given amount.
	 *
	 * @param dx amount to move in the x-direction
	 */
	
	public void shift(int dx) {
		// TODO: Update the ball's x-position
		  
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
	}

}
