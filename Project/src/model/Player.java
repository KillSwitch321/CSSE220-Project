package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import ui.GameComponent;


public class Player implements Collidable{
    private int posX;
    private int posY;
    BufferedImage sprite;
    int height = 45;
    int width = 30;
    private int lives = 3;
    private int score = 0;
    
    public Player(int x, int y) {
        this.posX = x;
        this.posY = y;
        
		try {
			sprite = ImageIO.read(Player.class.getResource("PlayerSprite.png"));
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
			g.drawImage(sprite, this.posX, this.posY, this.width, this.height, null);
		} else {
		g.setColor(Color.BLACK);
		g.fillRect(this.posX, this.posY, 30, 45);
	}
    }
    public void loseLife() {
    	this.lives--;
    }

    public Rectangle getBounds() {
	    return new Rectangle(this.posX, this.posY, this.width, this.height);
	}

	@Override
	public boolean collidesWith(Collidable other) {
		return this.getBounds().intersects(other.getBounds());
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLives() {
		return this.lives;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int n) {
		this.score = n;
	}
	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

}
