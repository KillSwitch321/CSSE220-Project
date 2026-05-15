package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

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
    private boolean isCurrentlyColliding = false;
    boolean touchingAnyZombie = false;
    private int currentLevel = 1;
    private int coinsInCurrentLevel = 0;
    
    private Random random = new Random();
    private final int WORLD_SIZE = 580;

    public GameModel() {
    	zombies = new ArrayList<>();
    	coins = new ArrayList<>();
    	
    	this.player = new Player(0, 0);
    }

    public void loadLevel(String filename) {
        int row = 0;
        zombies.clear();
        coins.clear();

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
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                
                if (ch == 'P') {
                    if (!gameStarted) {
                        gameStarted = true;
                        this.player = new Player(x, y);
                    } else {
                        this.player.setPosition(x, y);
                    }
                }  
                if (ch == 'Z') {
                    zombies.add(new Zombie(x, y));
                }  
                if (ch == 'C') {
                    coins.add(new Coin(x, y));
                }  
            }
            row++;
        }
        scanner.close();	
    }
    
    public void checkPlayerCollides() {
    	
    	if (player == null || !gameStarted) {
            return;
        }
    	
    	ArrayList<Coin> coinsCopy = new ArrayList<>(this.coins);
        
    	for (Coin c : coinsCopy) {
            if (player.collidesWith(c)) {
                player.setScore(player.getScore() + c.getValue());
                this.coins.remove(c);
                coinsInCurrentLevel++;

                if (coinsInCurrentLevel >= currentLevel) {
                    levelUp();
                } else {
                    spawnRandomCoin();
                }
            }
        }

        boolean touchingAnyZombie = false;
        for (Zombie z : zombies) {
            if (player.collidesWith(z)) {
                touchingAnyZombie = true;
                if (!isCurrentlyColliding) {
                    player.loseLife();
                    isCurrentlyColliding = true;
                }
                break;
            }
        }
        
        if (!touchingAnyZombie) {
            isCurrentlyColliding = false;
        }
    }

    public void spawnRandomCoin() {
        int x = random.nextInt(WORLD_SIZE);
        int y = random.nextInt(WORLD_SIZE);
        coins.add(new Coin(x, y));
    }

    public void spawnRandomZombie() {
        int x = random.nextInt(WORLD_SIZE);
        int y = random.nextInt(WORLD_SIZE);
        zombies.add(new Zombie(x, y));
    }
    
    private void levelUp() {
        currentLevel++;
        coinsInCurrentLevel = 0;
        loadLevel("Level1.txt"); 
    }

    public boolean hasStarted() {
        return gameStarted;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public void startGame() {
    	this.gameStarted = true;
        this.currentLevel = 1;
        this.coinsInCurrentLevel = 0;
        
        if (this.player != null) {
            this.player.setScore(0);
            this.player.setLives(3);
        }
        
        loadLevel("Level1.txt");
        this.isCurrentlyColliding = false;
    }

    public void resetGame() {
        player.setScore(0);
        player.setLives(3);
        currentLevel = 1;
        coinsInCurrentLevel = 0;
        isCurrentlyColliding = false;
        loadLevel("Level1.txt");
    }

    public boolean isGameOver() {
    	if (!gameStarted) return false;
        return player != null && player.getLives() <= 0;
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
}
