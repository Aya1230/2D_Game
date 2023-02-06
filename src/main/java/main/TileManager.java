package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GameManager gm;
    public Tile[] tile;
    public int mapTileNum[][];

    /**
     * Constructor
     * @param gm
     */
    public TileManager(GameManager gm) {
        this.gm = gm;

        tile = new Tile[10];
        mapTileNum = new int[gm.GAME_WIDTH][gm.GAME_HEIGHT];

        getTileImage();
        loadMap();
    }

    /**
     * Liest die Bilder ein und setzt sie ins Array "tile"
     */
    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Floor1.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Floor2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Floor3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Floor4.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Room1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Room2.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Room3.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Room4.png"));

        } catch (IOException e) {
            System.err.println("Fehler beim einlesen der Bilder.");
        }
    }

    /**
     * Methode um die Map einzulesen
     */
    public void loadMap() {
        try (InputStream stream = getClass().getResourceAsStream("/map/map.txt"); 
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream))) {

            for (int row = 0; row < gm.maxScreenRow; row++) {
                String line = buffer.readLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < gm.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
            }
        } catch (Exception e) {
            System.err.println("Fehler beim einlesen der Map");
        }


    }

    /**
     * Die Methode zeichnet die "Assets-Tiles" auf den Hintergrund
     * @param g2
     */
    public void draw(Graphics2D g2) {
        for (int worldRow = 0; worldRow < gm.maxScreenRow; worldRow++) {
            for (int worldCol = 0; worldCol < gm.maxScreenCol; worldCol++) {
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gm.tileSize;
                int worldY = worldRow * gm.tileSize;
                int screenX = worldX - gm.player.worldX + gm.player.screenX;
                int screenY = worldY - gm.player.worldY + gm.player.screenY;


                g2.drawImage(tile[tileNum].image, screenX, screenY, gm.tileSize, gm.tileSize, null);
            }
        }
    }
}