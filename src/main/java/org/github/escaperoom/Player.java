package org.github.escaperoom;
import java.util.Scanner;

import org.github.escaperoom.items.Inventory;
import org.github.escaperoom.items.Item;

public class Player {
    public enum Move {FIRST, SECOND, THIRD, FOURTH, CENTER};
    private Move location;
    private int currRoom;
    private Item heldItem;
    private boolean gaveUpCompanion;
    private boolean helpInitiated;
    private boolean firstIteration;
    private final TextMethods txt;
    private final Scanner scnr;
    private final Inventory inv;


    public Player() {
        location = Move.CENTER;
        currRoom = 1;
        heldItem = null;
        txt = TextMethods.getInstance();
        scnr = new Scanner(System.in);
        inv = new Inventory();
        gaveUpCompanion = false;
        helpInitiated = false;
        firstIteration = true;
    }

    public Move getLocation() {return location;} 
    public void setLocation(Move location) {this.location = location;}
    public int getCurrRoom() {return currRoom;}
    public void setCurrRoom(int currRoom) {this.currRoom = currRoom;}
    public Item getHeldItem() {return heldItem;}
    public void setHeldItem(Item heldItem) {this.heldItem = heldItem;}
    public boolean getGaveUpCompanion() {return gaveUpCompanion;}
    public void setGaveUpCompanion(boolean gaveUpCompanion) {this.gaveUpCompanion = gaveUpCompanion;}
    public boolean getHelpInitiated() {return helpInitiated;}
    public void setHelpInitiated(boolean helpInitiated) {this.helpInitiated = helpInitiated;}
    public boolean isFirstIteration() {return firstIteration;}
    public void setFirstIteration(boolean firstIteration) {this.firstIteration = firstIteration;}
    
    public void move() {
        switch(location) {
            case CENTER -> {
                txt.typeWriterNormal("Choose which corner you'd like to move:");
                System.out.println("1: First");
                System.out.println("2: Second");
                System.out.println("3: Third");
                System.out.println("4: Fourth");
                Move choice = chooseLocation();
                setLocation(choice);
            }
            default -> {
                txt.typeWriterNormal("You decide to move to the center of the room.");
                setLocation(Move.CENTER);
            }
        }
    }

    private Move chooseLocation() {
        while (true) { 
            String input = scnr.nextLine();
            try {
                int locNum = Integer.parseInt(input);
                if (isValidInput(locNum)) {
                    Move move = getLocFromNum(locNum);
                    txt.typeWriterNormal("You decide to move to the " + move.toString().toLowerCase() + " corner.");
                    return move;
                }
                else {
                    txt.typeWriterNormal("You walk straight into a wall, as this is a square room, not an arbitrary n-sided room. Nice job.");
                }
            } 
            catch (NumberFormatException e) {
                txt.typeWriterNormal("State a valid number, next time.");
            }
        }
    }

    private boolean isValidInput(int i) {
        return (i == 1 || i == 2 || i == 3 || i == 4);
    }

    private Move getLocFromNum(int i) {
        return switch(i) {
            case 1 -> Move.FIRST;
            case 2 -> Move.SECOND;
            case 3 -> Move.THIRD;
            case 4 -> Move.FOURTH;
            default -> null;
        };
    }

    public void moveRoom() {
        currRoom += 1;
        txt.typeWriterNormal("Walking through the exit, you enter a dark hallway, and at the other side, you enter Room " + currRoom);
    }

    public void openInventory() {
        if (inv.getInventory().isEmpty()) {
            txt.typeWriterNormal("You open your inventory. It's completely empty. Like your soul.");
            return;
        }

        txt.typeWriterNormal("You decide to open your inventory, inside, are...");
        int i = 1;
        for (Item it : inv.getInventory()) {
            System.out.println(i + ": " + it.getName());
            i++;
        }

        System.out.println();
        txt.typeWriterNormal("Do you want to take out an item?");
        char c = txt.askYN();
        boolean val = txt.ynToBool(c);
        if (val) {
            heldItem = getItemChoice();
        }
        else {
            txt.typeWriterNormal("You close your inventory, wondering why you even opened it in the first place.");
        }
    }

    private Item getItemChoice() {
        if (heldItem != null) {
            txt.typeWriterNormal("Before you take anything out, you put your hold item away.");
            heldItem = null;
        }
        txt.typeWriterNormal("Input the number corresponding to the item you wish to take out.");
        while (true) { 
            String input = scnr.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (isValidInvIndex(num - 1)) {
                    txt.typeWriterNormal("You take out your " + inv.getInventory().get(num).getName().toLowerCase() + "...");
                    return inv.getInventory().get(num - 1);
                }
                else {
                    txt.typeWriterNormal("Did you think that would work? Input a correct number.");
                } 
            } 
            catch (NumberFormatException e) {
                txt.typeWriterNormal("That's... not even a number. If I had a physical form, I'd facepalm.");
            }
        }       
    }

    private boolean isValidInvIndex(int i) {
        for (int j = 0; j < inv.getInventory().size(); j++) {
            if (i == j) {return true;}
        }
        return false;
    }
}
