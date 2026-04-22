package tier2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/**
 * Controller class for the game.
 * Handles user input (buttons + keys).
 */


public class GamePanel extends JPanel{
	private GameComponent canvas;
	private GameMenu menu;

	public GamePanel() {
		canvas = new GameComponent();
		menu = new GameMenu();
	
		this.setLayout(new BorderLayout());
		this.add(canvas,BorderLayout.CENTER);
		this.add(menu, BorderLayout.SOUTH);
		
		ButtonListener listener = new ButtonListener();
		this.menu.getRightButton().addActionListener(listener);
		this.menu.getLeftButton().addActionListener(listener);
		this.menu.getCenterButton().addActionListener(listener);

		
		this.setFocusable(true); // allow this panel to receive keyboard input
		this.requestFocusInWindow(); // ask Swing to give this panel the focus now
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				int key = e.getKeyCode();
				if (key==KeyEvent.VK_A) {
					canvas.move(-10);
				}
				else if (key==KeyEvent.VK_D) {
					canvas.move(+10);
				}	
				else {
					canvas.center();
				}
			}
			
			
		});

	}
	
	// inner class
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String label = e.getActionCommand();
			if (label.equals("Left")) {
				canvas.move(-10);
			}
				
			else if (label.equals("Right")) {
				canvas.move(+10);
			}
				
			else {
				canvas.center();
			}
			GamePanel.this.requestFocusInWindow(); // give focus back to GamePanel after button click (buttons steal focus)
		}
		
	}
	
	
	

}
