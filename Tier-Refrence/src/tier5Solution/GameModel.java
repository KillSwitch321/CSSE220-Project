package tier5Solution;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the core game state (the "model").
 *
 * Responsibilities:
 *   - Store all game data (ball, walls, and other objects)
 *   - Load a level from a text file
 *   - Update the game state over time (movement, collisions)
 *   - Provide data for drawing (but does NOT handle graphics directly)
 */
 
public class GameModel {
	public static final int TILE_SIZE = 40;
	// What game objects do we need to store (walls, enemies, player, coins?
	// TODO: store multiple balls
    // private ArrayList<Ball> balls;
	private Ball ball;

	public GameModel() {

		// TODO: load a level file (e.g., "level1.txt")
		loadLevel("level1.txt");
	}
	
	/**
	 * Loads a level from a text file. 
	 * Reads file and throws exceptions if invalid
	 *
	 * @param filename name of the level file
	 * @throws IllegalStateException if the level is invalid
	 */
	public void loadLevel(String filename) {
		  int row = 0;

		InputStream stream = GameModel.class.getResourceAsStream(filename);
		
		if (stream == null) {
			throw new IllegalStateException("Level file not found: " + filename);
		}
		Scanner scanner = new Scanner(stream);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int col = 0; col < line.length(); col++) {
	            char ch = line.charAt(col);
	            
	            if (ch == 'B') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                ball = new Ball(x, y, 14);

	                scanner.close();
	                return; // stop after first ball
		}
			}
			row++;
		}
		scanner.close();
		throw new IllegalStateException("No B found in level file");
	    // TODO: read file and build game objects
	}
	
	public void update() {
		ball.update();
        // TODO: update each ball
    }

    public void draw(Graphics2D g2) {
        // TODO: draw each ball
    	ball.draw(g2);
    }
    
    public void movePlayer(int dx) {
        if (ball != null) {
            ball.shift(dx);
        }
    }
    
    public void resetPlayer() {
        if (ball != null) {
            ball.reset();
        }
    }
    
    
    
	
}
