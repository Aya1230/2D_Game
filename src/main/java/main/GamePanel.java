package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private int originalTileSize = 32; // 16x16 tile
    private int scale = 2;
    public int tileSize = originalTileSize * scale;
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;
    private int screenwidth = tileSize * maxScreenCol;
    private int screenHeight = tileSize * maxScreenRow;

    // FPS
    private int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    Room room = new Room();

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // Distance from border at which we consider the player to be "near" the border
    int borderDistance = 50;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenwidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;//0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

        }

    }


    public void update() {
       player.update();
    }
        public void paintComponent (Graphics g){
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            room.draw(g2);
            player.draw(g2);

            g2.dispose();

        }

    }