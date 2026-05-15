package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;   // Add this import
import java.awt.Font;

import model.Coin;
import model.GameModel;
import model.Zombie;

public class GameComponent extends JComponent implements KeyListener, ActionListener {
	private GameModel model;
    private Timer gameTimer;
    private Set<Integer> pressedKeys = new HashSet<>();
    private GameOverlay overlay;
    private JButton restartButton;
    private JButton startButton;
    private int tickCounter = 0;

    public GameComponent(GameModel model) {
        this.model = model;
        this.overlay = new GameOverlay(model);
        setFocusable(true);
        addKeyListener(this);
        
        this.setLayout(null);
        this.restartButton = new JButton("Restart Game");
        this.restartButton.setBounds(250, 350, 100, 40);
        this.restartButton.setFocusable(false);
        this.restartButton.setVisible(false);
        this.restartButton.addActionListener(e -> restartGame());
        this.add(restartButton);
        
        this.startButton = new JButton("Start Game");
        this.startButton.setBounds(240, 350, 120, 40);
        this.startButton.setFocusable(false);
        this.startButton.addActionListener(e -> {
            model.startGame();
            startButton.setVisible(false);
            this.requestFocusInWindow();
        });
        this.add(startButton);

        this.gameTimer = new Timer(16, this);
        this.gameTimer.start();
    }
    
    private void restartGame() {
        model.resetGame();
        tickCounter = 0;
        restartButton.setVisible(false);
        
        this.requestFocusInWindow();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (model.hasStarted()) {
            if (model.getPlayer() != null) model.getPlayer().drawOn(g2);
            for (Zombie z : model.getZombies()) z.drawOn(g2);
            for (Coin c : model.getCoins()) c.drawOn(g2);
        }

        overlay.draw(g2, getWidth(), getHeight());

        if (model.isGameOver()) {
            restartButton.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (model.hasStarted() && !model.isGameOver()) {
            updateMovement();
            model.moveZombies();
            model.checkPlayerCollides();

            tickCounter++;
            if (tickCounter >= 625) {
                model.spawnRandomZombie();
                tickCounter = 0;
            }
        }
        repaint();
    }

    private void updateMovement() {
        int dx = 0;
        int dy = 0;
        int speed = 5;

        if (pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_UP)) dy -= speed;
        if (pressedKeys.contains(KeyEvent.VK_S) || pressedKeys.contains(KeyEvent.VK_DOWN)) dy += speed;
        if (pressedKeys.contains(KeyEvent.VK_A) || pressedKeys.contains(KeyEvent.VK_LEFT)) dx -= speed;
        if (pressedKeys.contains(KeyEvent.VK_D) || pressedKeys.contains(KeyEvent.VK_RIGHT)) dx += speed;

        if (dx != 0 || dy != 0) {
            model.movePlayer(dx, dy);
        }
    }
    
    public void restart() {
        model.resetGame();
        tickCounter = 0;
        this.requestFocusInWindow(); // Ensure keyboard focus returns to the game
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
