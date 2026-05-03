package tier1Solution;

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

	public static final int WIDTH = 400;
	public static final int HEIGHT = 150;
	public static final Color BG = Color.CYAN;
	public static final Color FG = Color.BLACK;
	
	private int x1 = WIDTH/2;
	private int x2 = WIDTH/2;
	private int y1 = HEIGHT/8; // the line starts with the offset from the edge
	private int y2 = HEIGHT/8*7; // where the line ends with the offset from the edge
	
	public GameComponent() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(BG); // note this only works with JPanels and will not work with JComponents
		this.setOpaque(true); // we want our own background here. If false - it will be see-through
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(FG);
		g2.drawLine(x1, y1, x2, y2);
	}
	
	public void move(int x) {
		this.x1+=x;
		this.x2+=x;
		repaint();
	}
	
	public void center() {
		this.x1 = WIDTH/2;
		this.x2 = WIDTH/2;
		repaint();
	}
	
	
}
