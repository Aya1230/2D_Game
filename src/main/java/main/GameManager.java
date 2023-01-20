package main;

import javax.swing.*;
import java.awt.*;

public class GameManager extends JPanel implements Runnable {


    // Screen-Settings
    private int originalTileSize = 16;
    private int scale = 4;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenwidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    private int FPS = 60;

    // Objects
    TileManager tilem = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread(this);
    Player player = new Player(this,keyH);


    // Distance from border at which we consider the player to be "near" the border
    int borderDistance = 25;



    public GameManager() {
        this.setPreferredSize(new Dimension(screenwidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

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
                player.update();
                repaint();
                delta--;
                drawCount++;
            }

        }

    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D gD2 = (Graphics2D) g;

        tilem.draw(gD2);
        player.draw(gD2);

        gD2.dispose();

    }

}