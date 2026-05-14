package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores the current state of the game and controls the main game rules.
 * 
 * This is where the game keeps track of objects such as the player,
 * walls, gems, zombies, score, lives, and levels.
 * 
 * GameModel should update the game state, but it should not draw anything.
 * Drawing belongs in GameComponent.
 */

public class GameModel {
	
	private Player player;
    private ArrayList<Zombie> zombies;
    private ArrayList<Coin> coins;
    private int TILE_SIZE;
    private boolean gameStarted = false;

    public GameModel() {
    	zombies = new ArrayList<>();
    	coins = new ArrayList<>();
    	loadLevel("Level1.txt");
        
        
    }

    public void loadLevel(String filename) {
		  int row = 0;
		  

		InputStream stream = GameModel.class.getResourceAsStream(filename);
		
		if (stream == null) {
			throw new IllegalStateException("Level file not found: " + filename);
		}
		Scanner scanner = new Scanner(stream);
		TILE_SIZE = 20;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int col = 0; col < line.length(); col++) {
	            char ch = line.charAt(col);
	            
	            
				if (ch == '*') {
	                //Generate Wall
		}
	            
	            if (ch == 'P' ) {
	            	if(!gameStarted) {
	            	gameStarted = True;
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                this.player = new Player(x,y);}
	            	else {
	            		this.player.setPosition(x, y);
	            	}
	            }  
	            if (ch == 'Z') {
	            	//Zombie
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;
	                zombies.add(new Zombie(x,y));
	            }  
	            if (ch == 'C') {
	            	//Coin
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;
	                coins.add(new Coin(x,y));
	                System.out.println("drawing coin");
	                
	            }  
	            
			}
			row++;
		}
		scanner.close();	
	}
    public void checkPlayerCollides() {    
    for (Coin c : coins) {
	    if(player.collidesWith(c))
	    {
	    	player.setScore(c.getValue() + player.getScore());
	    	System.out.println("score = " + player.getScore());
	    	coins.remove(c);
	    	System.out.println("Player Hit Coin");
	    	
	    	break;
	    }
    }
    for (Zombie z : zombies) {
	    if(player.collidesWith(z))
	    {
	    	//Need to reset the level when this happens
	    	zombies.remove(z);
	    	System.out.println("Player Hit Zombie");
	    	player.loseLife();
	    	System.out.println(player.getLives());
	    	break;
	    }
    }
}

    
    public Player getPlayer() {
        return this.player;
    }

    public void movePlayer(int dx, int dy) {
        this.player.moveBy(dx, dy);
    }

    public List<Zombie> getZombies() {
        return this.zombies;
    }

    public void moveZombies() {
    	for (Zombie z : zombies) {
            int dx = (player.getX() > z.getX()) ? 1 : (player.getX() < z.getX() ? -1 : 0);
            int dy = (player.getY() > z.getY()) ? 1 : (player.getY() < z.getY() ? -1 : 0);
            z.moveBy(dx, dy);
        }
    }

    public List<Coin> getCoins() {
    	return this.coins; 
    	}
    public Object getTileMap() { return null; }
}
