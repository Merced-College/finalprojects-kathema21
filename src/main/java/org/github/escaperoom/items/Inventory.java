package org.github.escaperoom.items;

import java.util.ArrayList;

import org.github.escaperoom.TextMethods;

public class Inventory {
    private final ArrayList<Item> inv;
    private final TextMethods txt = TextMethods.getInstance();

    public Inventory() {
        inv = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {return inv;}

    public void addItem(Item i) {
        inv.add(i);
        txt.typeWriterNormal("You decide to put the " + i.getName().toLowerCase() + "  in your inventory. You'll probably need it later.");
    }

    public void removeItem(Item i) {
        inv.remove(i);
        txt.typeWriterNormal("In a haze of effervescent particles, the " + i.getName().toLowerCase() + " disintegrates in your hand. It is gone seemingly forever.");
    }

    public void removeFirstItem(Item i) {
        inv.remove(i);
        txt.typeWriterNormal("To your shock, the " + i.getName().toLowerCase() + " disintegrates in your hand into a cloud of glimmers. It is gone seemingly forever.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : inv) {
            sb.append ("name: ");
            sb.append(i.getName());
            sb.append(", desc: ");
            sb.append(i.getDesc());
        }
        return sb.toString();
    }
}
