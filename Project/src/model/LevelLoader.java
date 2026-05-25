package model;

import java.awt.Point;
import java.io.InputStream;
import java.util.Scanner;

/**
* @param  MaxRows the amount of rows the level has
* @param  Max Cols the amount of columns the level has
* @param TileSize the size in pixels of each tile
*/
public class LevelLoader {
    private static final int TILE_SIZE = 40;
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 20;

    public static void loadLevel(String filename, GameModel model) {
        model.getZombies().clear();
        model.getCoins().clear();
        model.getWalls().clear();
        model.getValidCoinSlots().clear();
        model.getValidZombieSlots().clear();

        InputStream stream = GameModel.class.getResourceAsStream(filename);
        if (stream == null) {
            throw new IllegalStateException("Level file not found: " + filename);
        }
        
        Scanner scanner = new Scanner(stream);
        int row = 0;
        
        while (scanner.hasNextLine() && row < MAX_ROWS) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length() && col < MAX_COLS; col++) {
                char ch = line.charAt(col);
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                
                if (ch == '#') {
                    model.getWalls().add(new Wall(x, y, TILE_SIZE, model.getCachedWallSprite()));
                } else if (ch == 'P') {
                    if (!model.hasStarted()) {
                        model.setPlayer(new Player(x, y));
                    } else {
                        model.getPlayer().setPosition(x, y);
                    }
                } else if (ch == 'Z') {
                    model.getValidZombieSlots().add(new Point(x + 10, y + 2));
                } else if (ch == 'C') {
                    model.getValidCoinSlots().add(new Point(x + 10, y + 10));
                }  
            }
            row++;
        }
        scanner.close();

        model.spawnRandomCoin();
        model.spawnRandomZombie();
    }
}