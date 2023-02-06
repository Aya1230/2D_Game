package main;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2 , down1, down2, left1, left2, right1, right2;
    public BufferedImage player;
    public BufferedImage attack1, attack2, attack3;
    public String direction;
    public String attacke;
    public String action;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean CollisionON;

}
