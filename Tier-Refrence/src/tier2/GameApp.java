package tier2;

import javax.swing.JFrame;

/**
 * Top-level application window for the game.
 * 
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Create the game application frame</li>
 *   <li>Attach the GamePanel to the frame</li>
 *   <li>Configure window title, size, and close behavior</li>
 *   <li>Display the frame using show() method</li>
 * </ul>
 * 
 * */

public class GameApp {
	private JFrame frame;
	private GamePanel panel;
	
	public GameApp() {
		
		this.frame = new JFrame("Game");
		this.panel = new GamePanel();
		// size will be set up by Panel
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setContentPane(this.panel);
        this.frame.pack();                  // Fit to preferred component sizes
        this.frame.setLocationRelativeTo(null); // Center on screen
	}
	
	/**
     * Displays the game window on screen.
     */
	public void show() {
		this.frame.setVisible(true);

	}

}
