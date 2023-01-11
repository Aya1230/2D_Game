package main.inv;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Integer num) {
        try {
            items.remove(num);
        } catch (Exception e) {
            System.out.println("Number is out range");
        }
    }

    public int getNumItems() {
        return items.size();
    }

    @Override
    public String toString() {
        Iterator<Item> it =  items.iterator();
        String itemIT = null;

        while (it.hasNext()) {
            itemIT += it.next() + "\n\r";
        }
        return itemIT;
    }
}

