package model;

import java.util.ArrayList;
import java.util.List;

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
    private List<Zombie> zombies;

    public GameModel() {
        this.player = new Player(100, 100);
        this.zombies = new ArrayList<>();
        this.zombies.add(new Zombie(300, 300));
        this.zombies.add(new Zombie(400, 100));
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

    public Object getCoins() { return null; }
    public Object getTileMap() { return null; }
}
