package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private GameManager gm;
    private KeyHandler keyH;
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

        solidArea = new Rectangle(8, 16,32,32);

        speed =  1.5;
        direction = "down";
    }

    public void getPlayerImage() {

        String[] imagePaths = {
                "/player/boy_up_1.png",
                "/player/boy_up_2.png",
                "/player/boy_down_1.png",
                "/player/boy_down_2.png",
                "/player/boy_left_1.png",
                "/player/boy_left_2.png",
                "/player/boy_right_1.png",
                "/player/boy_right_2.png",
                "/player/attack/attack_1.png",
                "/player/attack/attack_2.png",
        };

        try {
            for (int i = 0; i < imagePaths.length-1; i++) {
                states[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePaths[i])));
            }
        } catch (IOException e) {
            System.err.println("Error loading 'Player assets'");
        }
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
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
            else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            else if (keyH.spacePressed) {
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
    public BufferedImage getDirectionIMG(String direction) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = states[0];
                }
                if (spriteNum == 2) {
                    image = states[1];
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = states[2];
                }
                if (spriteNum == 2) {
                    image = states[3];
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = states[4];
                }
                if (spriteNum == 2) {
                    image = states[5];
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = states[6];
                }
                if (spriteNum == 2) {
                    image = states[7];
                }
            }
        }
        return image;
    }

    public void draw(Graphics g2) throws InterruptedException {
        BufferedImage imageDir = getDirectionIMG(direction);

        if (action == "attack") {
            BufferedImage[] aImage = {states[8],states[8],states[8],states[8]};
            for (int i = 0; i <= aImage.length - 1; i++) {
                BufferedImage image = aImage[i];
                g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);
            }
            action = null;
        } else {
            g2.drawImage(imageDir, screenX, screenY, gm.tileSize, gm.tileSize, null);
        }
    }

}
