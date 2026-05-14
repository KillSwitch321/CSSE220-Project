package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.Timer;

import model.Coin;
import model.GameModel;
import model.Zombie;

public class GameComponent extends JComponent implements KeyListener, ActionListener {
    private GameModel model;
    private Timer gameTimer;
    private Set<Integer> pressedKeys = new HashSet<>();

    public GameComponent(GameModel model) {
        this.model = model;
        setFocusable(true);
        addKeyListener(this);
        
        this.gameTimer = new Timer(16, this);
        this.gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (model.getPlayer() != null) {
            model.getPlayer().drawOn(g2);
        }

        if (model.getZombies() != null) {
            for (Zombie z : model.getZombies()) {
                z.drawOn(g2);
            }
        }
        if (model.getCoins() != null) {
            for (Coin c : model.getCoins()) {
                c.drawOn(g2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateMovement();
        model.moveZombies();
        model.checkPlayerCollides();
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
