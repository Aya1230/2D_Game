package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * "Main" Class
     * @param args
     */
    public static void main (String[] args){
        // Erstellen der Fenster/Game Objekt
        JFrame window = new JFrame();
        GameManager gamePanel = new GameManager();

        // Setzt den Titel des Fensters
        window.setTitle("The Quest for the Lost City");
        // Füge das Spiel-Panel dem Fenster hinzu
        window.add(gamePanel);
        // Passe die Größe des Fensters an die Größe des Panels an
        window.pack();


        //Setablels
        window.setLocationRelativeTo(null);
        window.setBackground(Color.BLACK);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
    }
}
