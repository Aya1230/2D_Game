package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Room {


    private String roomPathIMG;

    public Room(String roomPathIMG) {
        this.roomPathIMG = roomPathIMG;
    }

    public Room(){

    }

    public void draw(Graphics g2)  {
        Image door = null;
        try {
            door = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_room.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.drawImage(door, 0, 0, 1025, 770, null);
    }

}

