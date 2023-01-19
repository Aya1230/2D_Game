package main;

import javax.swing.*;

public class Main {
    /**
     * "Main" Class
     * @param args
     */
    public static void main (String[] args){
        JFrame window = new JFrame();
        GameManager gamePanel = new GameManager();

        // Set the Window title
        window.setTitle("The Quest for the Lost City");
        //Initialise Game-Components
        window.add(gamePanel);
        window.pack();


        //Setablels
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);


    }
}
