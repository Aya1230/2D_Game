package main;

import javax.swing.*;
import java.awt.*;

public class GameManager extends JPanel implements Runnable {


    // Screen-Settings
    private int originalTileSize = 16;
    private int scale = 4;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 40;
    public final int screenwidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int TILES_DEFAULT_SIZE = 30;
    public final static float SCALE =  4.0f;
    public final static int TILES_WIDTH = 16;
    public final static int TILES_HEIGHT = 16;
    public final static int TILE_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILES_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILES_HEIGHT;


    // Objects
    TileManager tile = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread(this);
    Player player = new Player(this,keyH);


    public GameManager() {
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                repaint();
                frames++;
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }

    private void update() {
        player.update();
    }

    /**
     * Paint-Methode. Führt ein ".draw()" aus falls sich änderungen ereignen.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent (Graphics g){
        super.paintComponent(g);

        Graphics2D gD2 = (Graphics2D) g;

        tile.draw(gD2);

        try {
            player.draw(gD2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        gD2.dispose();

    }
}