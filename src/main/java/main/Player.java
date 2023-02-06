package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private GameManager gm;
    private KeyHandler keyH;
    private MouseHandler mH;

    public int screenY;
    public int screenX;
    
    

    public Player(GameManager gp, KeyHandler keyH) {
        this.gm = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gm.tileSize * 23;
        worldY = gm.tileSize * 21;

        screenX = gm.screenwidth/2 - (gm.tileSize/2);
        screenY = gm.screenHeight/2 - (gm.tileSize/2);

        speed = 6;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
            attack1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/attack/attack_1.png")));
            attack2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/attack/attack_2.png")));
            attack3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/attack/attack_3.png")));
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der 'Spieler-Assets'");
        }
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || mH.mPressed || mH.mReleased) {
            if (keyH.upPressed) {
                action = "up";
                worldY -= speed;
            }
            else if (keyH.downPressed) {
                action = "down";
                worldY += speed;
            }
            else if (keyH.leftPressed) {
                action = "left";
                worldX -= speed;
            }
            else if (keyH.rightPressed) {
                action = "right";
                worldX += speed;
            }
            else if (mH.mPressed) {
                action = "attack";
            }

            spriteCounter++;
            spriteCounter++;
            if (spriteCounter > 13) {
                spriteNum = spriteNum == 1 ? 2 : 1;
                spriteCounter = 0;
            }

        }

    }


    public void draw(Graphics g2){

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "attack" -> {
                if (spriteNum == 1) {
                    image = attack1;
                }
                if (spriteNum == 2) {
                    image = attack2;
                }
                if (spriteNum == 3) {
                    image = attack3;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);

    }
}
