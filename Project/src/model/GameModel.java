package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class GameModel {
    public enum GameState {
        START_SCREEN,
        PLAYING,
        LEVEL_INTERMISSION,
        GAME_OVER
    }

    private GameState currentState = GameState.START_SCREEN;
    private Player player;
    private ArrayList<Zombie> zombies;
    private ArrayList<Coin> coins;
    private ArrayList<Wall> walls;
    private ArrayList<Point> validCoinSlots;
    private ArrayList<Point> validZombieSlots;
    
    private boolean isCurrentlyColliding = false;
    private int currentLevel = 1;
    private int coinsInCurrentLevel = 0;
    private Random random = new Random();
    private BufferedImage cachedWallSprite;

    public GameModel() {
        zombies = new ArrayList<>();
        coins = new ArrayList<>();
        walls = new ArrayList<>();
        validCoinSlots = new ArrayList<>();
        validZombieSlots = new ArrayList<>();
        this.player = new Player(0, 0);
        
        try {
            cachedWallSprite = ImageIO.read(Player.class.getResource("rocky_vine_V2.png"));
        } catch (IOException | IllegalArgumentException e) {
            cachedWallSprite = null;
        }
    }

    public void loadLevel(String filename) {
        LevelLoader.loadLevel(filename, this);
    }
    
    public void checkPlayerCollides() {
        CollisionHandler.checkPlayerCollides(this);
    }

    public void spawnRandomCoin() {
        if (validCoinSlots.isEmpty()) return;
        Point randomSlot = validCoinSlots.get(random.nextInt(validCoinSlots.size()));
        coins.add(new Coin(randomSlot.x, randomSlot.y));
    }

    public void spawnRandomZombie() {
    	if (validZombieSlots.isEmpty()) return;
        Point randomSlot = validZombieSlots.get(random.nextInt(validZombieSlots.size()));
        Zombie newZombie = new Zombie(randomSlot.x, randomSlot.y);
        
        newZombie.scatterFromTwins(this.zombies);
        zombies.add(newZombie);
    }
    
    public void levelUp() {
        currentLevel++;
        coinsInCurrentLevel = 0;
        zombies.clear();
        coins.clear();
        
        this.currentState = GameState.LEVEL_INTERMISSION;
    }
    
    public void startNextLevel() {
    	this.zombies.clear();
        this.coins.clear();
        if( Math.random() >= 0.5) {
        	loadLevel("Level2.txt");}
        else {
        	loadLevel("Level1.txt");}
        
        spawnRandomCoin();
        for (int i = 0; i < currentLevel; i++) {
            spawnRandomZombie();
        }
        this.currentState = GameState.PLAYING;
    }
    
    public int getIntermissionSecondsLeft(int currentTicks, int maxTicks) {
        int ticksLeft = maxTicks - currentTicks;
        int ticksPerSecond = maxTicks / 3; 
        
        int secondsLeft = (ticksLeft / ticksPerSecond) + 1;
        return Math.min(3, Math.max(1, secondsLeft));
    }

    public void startGame() {
        this.currentState = GameState.PLAYING;
        this.currentLevel = 1;
        this.coinsInCurrentLevel = 0;
        
        if (this.player != null) {
            this.player.setScore(0);
            this.player.setLives(3);
        }
        loadLevel("Level2.txt");
        
        //spawnRandomCoin();
        //spawnRandomZombie();
        
        this.isCurrentlyColliding = false;
    }

    public void resetGame() {
    	if (this.player != null) {
            player.setScore(0);
            player.setLives(3);
        }
        currentLevel = 1;
        coinsInCurrentLevel = 0;
        isCurrentlyColliding = false;
        
        spawnRandomCoin();
        spawnRandomZombie();
        
        this.currentState = GameState.PLAYING;
    }

    public void movePlayer(int dx, int dy) {
        this.player.moveBy(dx, dy, this.walls);
    }

    public void moveZombies() {
        for (Zombie z : zombies) {
            z.chase(this.player, this.walls);
        }
    }
    
    public void playerTakeDamage() {
        if (this.player != null) {
            this.player.setLives(this.player.getLives() - 1);
        }
        
        this.coinsInCurrentLevel = 0;
        this.zombies.clear();
        this.coins.clear();
        
        this.currentState = GameState.LEVEL_INTERMISSION;
    }

    public GameState getCurrentState() { return currentState; }
    public void setCurrentState(GameState state) { this.currentState = state; }
    public boolean hasStarted() { return currentState != GameState.START_SCREEN; }
    public boolean isGameOver() { return currentState == GameState.GAME_OVER; }
    
    public int getCurrentLevel() { return currentLevel; }
    public int getCoinsInCurrentLevel() { return coinsInCurrentLevel; }
    public void incrementCoinsInLevel() { this.coinsInCurrentLevel++; }
    public boolean isCurrentlyColliding() { return isCurrentlyColliding; }
    public void setCurrentlyColliding(boolean state) { this.isCurrentlyColliding = state; }
    
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player p) { this.player = p; }
    
    public List<Zombie> getZombies() { return this.zombies; }
    public List<Coin> getCoins() { return this.coins; }
    public ArrayList<Wall> getWalls() { return this.walls; }
    
    public ArrayList<Point> getValidCoinSlots() { return validCoinSlots; }
    public ArrayList<Point> getValidZombieSlots() { return validZombieSlots; }
    public BufferedImage getCachedWallSprite() { return cachedWallSprite; }
}