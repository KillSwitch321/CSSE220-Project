package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Coin implements Collidable {
	/**
	* @param  posX the location of the coin, relative to screen 0,0
	* @param  posY the location of the coin, relative to screen 0,0
	* @param  sprite the image of the sprite
	* @return      the image at the specified URL
	*/
    private int posX;
    private int posY;
    BufferedImage sprite;
    private static final int VALUE = 10;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

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

    public void drawOn(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, this.posX, this.posY, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(posX, posY, WIDTH, HEIGHT);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, WIDTH, HEIGHT);
    }

    @Override
    public boolean collidesWith(Collidable other) {
        return this.getBounds().intersects(other.getBounds());
    }

    public int getValue() {
        return VALUE;
    }
}