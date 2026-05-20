package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import model.GameModel;
import model.GameModel.GameState;

public class GameOverlay {
    private final GameModel model;
    private final Font mainFont;

    public GameOverlay(GameModel model) {
        this.model = model;
        this.mainFont = new Font("SansSerif", Font.BOLD, 18);
    }

    public void draw(Graphics2D g2, int width, int height, int currentTicks, int maxTicks) {
        GameState state = model.getCurrentState();

        if (state == GameState.START_SCREEN) {
            drawStartScreen(g2, width, height);
            return;
        }

        if (state == GameState.PLAYING) {
            drawHUD(g2, width, height);
        }

        if (state == GameState.LEVEL_INTERMISSION) {
            drawIntermissionScreen(g2, width, height, currentTicks, maxTicks);
        }

        if (state == GameState.GAME_OVER) {
            drawGameOver(g2, width, height);
        }
    }
    
    private void drawHUD(Graphics2D g2, int width, int height) {
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
    }
    
    private void drawStartScreen(Graphics2D g2, int width, int height) {
        g2.setColor(new Color(15, 15, 25));
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

    private void drawIntermissionScreen(Graphics2D g2, int width, int height, int currentTicks, int maxTicks) {
        g2.setColor(new Color(0, 0, 0, 220));
        g2.fillRect(0, 0, width, height);

        g2.setFont(new Font("Arial", Font.BOLD, 45));
        String title;
        if (model.getPlayer() != null && model.getPlayer().getLives() <= 0) {
            title = "GAME OVER";
            g2.setColor(Color.RED);
        } else if (model.getCoinsInCurrentLevel() == 0 && model.getZombies().isEmpty()) {

            title = "WATCH OUT!";
            g2.setColor(Color.ORANGE);
        } else {
            title = "LEVEL COMPLETED!";
            g2.setColor(Color.GREEN);
        }
        
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (width - titleWidth) / 2, height / 2 - 120);

        g2.setFont(new Font("Arial", Font.PLAIN, 22));
        g2.setColor(Color.WHITE);
        String scoreText = "Current Score: " + (model.getPlayer() != null ? model.getPlayer().getScore() : 0);
        int scoreWidth = g2.getFontMetrics().stringWidth(scoreText);
        g2.drawString(scoreText, (width - scoreWidth) / 2, height / 2 - 60);

        String livesText = "Lives Remaining: " + (model.getPlayer() != null ? model.getPlayer().getLives() : 0);
        int livesWidth = g2.getFontMetrics().stringWidth(livesText);
        g2.drawString(livesText, (width - livesWidth) / 2, height / 2 - 20);

        int secondsLeft = model.getIntermissionSecondsLeft(currentTicks, maxTicks);
        
        g2.setFont(new Font("Arial", Font.BOLD, 90));
        g2.setColor(Color.YELLOW);
        String countdownText = String.valueOf(secondsLeft);
        int countdownWidth = g2.getFontMetrics().stringWidth(countdownText);
        g2.drawString(countdownText, (width - countdownWidth) / 2, height / 2 + 60);

        g2.setFont(new Font("Arial", Font.ITALIC, 20));
        g2.setColor(Color.LIGHT_GRAY);
        String sub = "Respawning assets... Get ready!";
        int subWidth = g2.getFontMetrics().stringWidth(sub);
        g2.drawString(sub, (width - subWidth) / 2, height / 2 + 130);
    }

    private void drawGameOver(Graphics2D g2, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, width, height);

        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "GAME OVER";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        g2.drawString(text, (width - textWidth) / 2, height / 2);
        
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        String subText = "Final Score: " + (model.getPlayer() != null ? model.getPlayer().getScore() : 0);
        int subWidth = g2.getFontMetrics().stringWidth(subText);
        g2.drawString(subText, (width - subWidth) / 2, (height / 2) + 50);
    }
}