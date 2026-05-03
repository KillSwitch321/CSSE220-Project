package model;



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
	
	public GameModel() {
		player = new Player(5,5);
		
	}
	public void moveUp() {
	this.player.moveBy(0,-1);
	}
	public void moveDown() {
		this.player.moveBy(0,1);
	}
	public void moveLeft() {
		this.player.moveBy(-1,0);
	}
	public void moveRight() {
		this.player.moveBy(1,0);
	}
	public Player getPlayer() {
		return player;
	}
	
}
