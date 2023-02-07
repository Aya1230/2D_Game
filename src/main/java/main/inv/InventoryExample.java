package main.inv;

import java.util.ArrayList;
import java.util.Iterator;

public class InventoryExample {
    public static void main(String[] args) {
        // Create a new Inventory instance
        Inventory inventory = new Inventory();

        /* Create some Item instances */
        Item sword = new Item("Sword", "A sharp blade for slicing through enemies.");
        Item shield = new Item("Shield", "A sturdy shield for blocking attacks.");
        Item slingshot = new Item("Slingshot", "A simple ranged weapon for launching projectiles.");
        Item bow = new Item("Bow", "A flexible ranged weapon for launching arrows.");
        Item arrows = new Item("Arrows", "Projectiles for use with a bow.");
        Item potion = new Item("Healing potion", "A magical potion that restores health.");

        // Add the items to the inventory
        inventory.addItem(sword);
        inventory.addItem(shield);
        inventory.addItem(slingshot);
        inventory.addItem(bow);
        inventory.addItem(arrows);
        inventory.addItem(potion);

        // Print the number of items in the inventory
        System.out.println("Number of items: " + inventory.getNumItems());

    }
}

