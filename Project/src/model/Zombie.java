package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie {
    private int posX;
    private int posY;
    BufferedImage sprite;

    public Zombie(int x, int y) {
        this.posX = x;
        this.posY = y;
        try {
			sprite = ImageIO.read(Player.class.getResource("ZombieSprite.png"));
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

    public void moveBy(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public void drawOn(Graphics g) {
    	 if (sprite != null) {
 			g.drawImage(sprite, this.posX, this.posY, 30, 45, null);
 		} else {
 			g.setColor(Color.RED);
 	        g.fillRect(posX, posY, 30, 45);
 	}
        
    }
}