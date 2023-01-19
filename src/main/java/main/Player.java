package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GameManager gm;
    KeyHandler keyH;

    public int screenY;
    public int screenX;

    public Player(GameManager gp, KeyHandler keyH, int screenY, int screenX) {
        this.gm = gp;
        this.keyH = keyH;
        this.screenY = screenY;
        this.screenX = screenX;

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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            else {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 13) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
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
        }
        g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);

    }
}
