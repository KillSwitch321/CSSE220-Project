package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

public class Zombie implements Collidable {
    private int posX;
    private int posY;
    BufferedImage sprite;
    private static int width = 20;
    private static int height = 35;

    private int currentDx = 0;
    private int currentDy = 0;
    private int speed = 2;

    public Zombie(int x, int y) {
        this.posX = x;
        this.posY = y;
        try {
            sprite = ImageIO.read(Player.class.getResource("ZombieSprite.png"));
        } catch (IOException | IllegalArgumentException e) {
            sprite = null;
        }
        
        this.currentDy = speed;
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

    public void chase(Player player, ArrayList<Wall> walls) {

        this.moveBy(currentDx, currentDy);
        
        if (hitsWall(walls)) {

            this.moveBy(-currentDx, -currentDy);
            

            ArrayList<int[]> validDirections = new ArrayList<>();
            
            int[][] optionalDirs = {
                {speed, 0},
                {-speed, 0},
                {0, speed},
                {0, -speed}
            };
            
            for (int[] dir : optionalDirs) {
                int testDx = dir[0];
                int testDy = dir[1];
                
                if (testDx == -currentDx && testDy == -currentDy) {
                    continue; 
                }
                
                this.moveBy(testDx, testDy);
                if (!hitsWall(walls)) {
                    validDirections.add(dir);
                }
                this.moveBy(-testDx, -testDy);
            }
            
            if (!validDirections.isEmpty()) {

                Collections.shuffle(validDirections);
                int[] selectedDir = validDirections.get(0);
                this.currentDx = selectedDir[0];
                this.currentDy = selectedDir[1];
            } else {

                this.currentDx = -currentDx;
                this.currentDy = -currentDy;
            }
            
            this.moveBy(currentDx, currentDy);
        }
    }
    
    public void scatterFromTwins(java.util.List<Zombie> otherZombies) {
        for (Zombie other : otherZombies) {
            if (other != this && this.getX() == other.getX() && this.getY() == other.getY()) {
                if (other.currentDy != 0) {
                    this.currentDy = -other.currentDy;
                    this.currentDx = 0;
                } else {
                    this.currentDx = -other.currentDx;
                    this.currentDy = 0;
                }
                break;
            }
        }
    }

    private boolean hitsWall(ArrayList<Wall> walls) {
        for (Wall w : walls) {
            if (this.getBounds().intersects(w.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void drawOn(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, this.posX, this.posY, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(posX, posY, 20, 35);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, width, height);
    }

    @Override
    public boolean collidesWith(Collidable other) {
        return this.getBounds().intersects(other.getBounds());
    }
}