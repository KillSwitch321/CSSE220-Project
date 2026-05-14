package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin implements Collidable{
    private int posX;
    private int posY;
    BufferedImage sprite;
    private static int VALUE;
    private static int height = 20;
    private static int width = 20;

    public Coin(int x, int y) {
        this.posX = x;
        this.posY = y;
        try {
			sprite = ImageIO.read(Player.class.getResource("CoinSprite.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public void collect() {
    }

    public void drawOn(Graphics g) {
    	 if (sprite != null) {
 			g.drawImage(sprite, this.posX, this.posY, 30, 45, null);
 		} else {
 			g.setColor(Color.YELLOW);
 	        g.fillRect(posX, posY, this.width, this.height);
 	}
        
    }

	@Override
	public Rectangle getBounds() {
	    return new Rectangle(this.posX, this.posY, this.width, this.height);
	}

	@Override
	public boolean collidesWith(Collidable other) {
		return this.getBounds().intersects(other.getBounds());
	}
	public int getValue() {
		return this.VALUE;
	}
}