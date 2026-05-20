package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Wall {
    private int x, y, size;
    private BufferedImage sprite;

    public Wall(int x, int y, int size, BufferedImage sharedSprite) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.sprite = sharedSprite;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void drawOn(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, size, size, null);
        } else {
            g2.setColor(Color.GRAY);
            g2.fillRect(x, y, size, size);
        }
    }
}