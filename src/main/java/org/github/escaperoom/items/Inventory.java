
//Katelynn Prater - 7/18/25
// Inventory class for inv logic for player & to store items
package org.github.escaperoom.items;

import java.util.ArrayList;

import org.github.escaperoom.TextMethods;

public class Inventory {
    private final ArrayList<Item> inv;
    private final TextMethods txt = TextMethods.getInstance(); 

    public Inventory() {
        inv = new ArrayList<>(); // only one instance of each item, otherwise hashmaps
    }

    public ArrayList<Item> getInventory() {return inv;}

    public void addItem(Item i) {
        inv.add(i);
        txt.typeWriterNormal("You put the " + i.getName().toLowerCase() + "  in your inventory. You'll probably need it later.");
    }

    public void removeItem(Item i) { //different from below for different dialogue
        inv.remove(i);
        txt.typeWriterNormal("In a haze of effervescent particles, the " + i.getName().toLowerCase() + " disintegrates in your hand. It is gone seemingly forever.");
    }

    public void removeFirstItem(Item i) {
        inv.remove(i);
        txt.typeWriterNormal("To your shock, the " + i.getName().toLowerCase() + " disintegrates in your hand into a cloud of glimmers. It is gone seemingly forever.");
    }

    @Override
    public String toString() { //for dev reasons
        StringBuilder sb = new StringBuilder();
        for (Item i : inv) {
            sb.append ("name: ");
            sb.append(i.getName());
            sb.append(", desc: ");
            sb.append(i.getDesc());
        }
        return sb.toString();
    }
} // end inventory class
