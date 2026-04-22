package tier2;

import javax.swing.SwingUtilities;

/**
 * Entry point for the game application.

 * 
 * Responsibilities:
 * - Start the Swing application</li>
 * - Ensure all UI code runs on the Event Dispatch Thread</li>
 * - Construct the GameApp and call its show() method</li>
 * 
 * @author Add your teams names
 * */


public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
				new GameApp().show();
		});

	}

}
