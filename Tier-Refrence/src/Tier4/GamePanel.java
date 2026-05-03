package Tier4;

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
				//System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
				int key = e.getKeyCode();
				if (key==KeyEvent.VK_A) {
					// TODO: Move the ball left
					canvas.move(-10);
				}
				else if (key==KeyEvent.VK_D) {
					// TODO: Move the ball right
					canvas.move(10);
				}	
				else {
					// TODO: Reset the ball
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
				// TODO: Move the ball left
				canvas.move(-10);
			}
				
			else if (label.equals("Right")) {
				// TODO: Move the ball right
				canvas.move(+10);
			}
				
			else {
				// TODO: Reset the ball
				canvas.center();
				
			}
			GamePanel.this.requestFocusInWindow(); // give focus back to GamePanel after button click (buttons steal focus)
		}
		
	}
	
	
	

}
