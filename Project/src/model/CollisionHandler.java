package model;

import java.util.ArrayList;

public class CollisionHandler {

    public static void checkPlayerCollides(GameModel model) {
        Player player = model.getPlayer();
        if (player == null || !model.hasStarted()) {
            return;
        }

        ArrayList<Coin> coinsCopy = new ArrayList<>(model.getCoins());
        for (Coin c : coinsCopy) {
            if (player.collidesWith(c)) {
                player.setScore(player.getScore() + c.getValue());
                model.getCoins().remove(c);
                model.incrementCoinsInLevel();

                if (model.getCoinsInCurrentLevel() >= model.getCurrentLevel()) {
                    model.levelUp();
                } else {
                    model.spawnRandomCoin();
                }
            }
        }

        for (Zombie z : model.getZombies()) {
            if (player.collidesWith(z)) {
                model.playerTakeDamage(); 
                break; 
            }
        }
    }
}