package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import model.GameModel;

public class GameOverlay {
    private final GameModel model;
    private final Font mainFont;

    public GameOverlay(GameModel model) {
        this.model = model;
        this.mainFont = new Font("SansSerif", Font.BOLD, 18);
    }

    public void draw(Graphics2D g2, int width, int height) {
        g2.setFont(mainFont);
        g2.setColor(Color.WHITE);

        if (model.getPlayer() != null) {
            String scoreText = "Score: " + model.getPlayer().getScore();
            g2.drawString(scoreText, 20, 30);

            String levelText = "Level: " + model.getCurrentLevel();
            int levelWidth = g2.getFontMetrics().stringWidth(levelText);
            g2.drawString(levelText, (width / 2) - (levelWidth / 2), 30);

            String livesText = "Lives: " + model.getPlayer().getLives();
            int livesWidth = g2.getFontMetrics().stringWidth(livesText);
            g2.drawString(livesText, width - livesWidth - 20, 30);
        }

        if (!model.hasStarted()) {
            drawStartScreen(g2, width, height);
        } else if (model.isGameOver()) {
            drawGameOver(g2, width, height);
        }
    }
    
    private void drawStartScreen(Graphics2D g2, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String title = "ZOMBIE ATTACK";
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (width - titleWidth) / 2, height / 2 - 50);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        String sub = "Collect coins to level up!";
        int subWidth = g2.getFontMetrics().stringWidth(sub);
        g2.drawString(sub, (width - subWidth) / 2, height / 2 + 20);
    }

    private void drawGameOver(Graphics2D g2, int width, int height) {

        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "GAME OVER";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        g2.drawString(text, (width - textWidth) / 2, height / 2);
        
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        String subText = "Final Score: " + model.getPlayer().getScore();
        int subWidth = g2.getFontMetrics().stringWidth(subText);
        g2.drawString(subText, (width - subWidth) / 2, (height / 2) + 50);
    }
}