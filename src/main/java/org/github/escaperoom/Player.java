
package org.github.escaperoom;
import java.util.Scanner;

import org.github.escaperoom.items.Inventory;
import org.github.escaperoom.items.Item;

public class Player {
    public enum Move {FIRST, SECOND, THIRD, FOURTH, CENTER}; //enum for corners (ubiquitous in rooms)
    private Move location; //in room
    private int currRoom; //in facility
    private Item heldItem; //no holding multiple, no duping
    private final TextMethods txt;
    private final Scanner scnr;
    private final Inventory inv;
    private static final Player player = new Player();

    private Player() {
        location = Move.CENTER;
        currRoom = 1;
        heldItem = null;
        txt = TextMethods.getInstance();
        scnr = new Scanner(System.in);
        inv = new Inventory();
    }

    public static Player getInstance() {
        return player;
    }

    public Move getLocation() {return location;} 
    public void setLocation(Move location) {this.location = location;}
    public int getCurrRoom() {return currRoom;}
    public void setCurrRoom(int currRoom) {this.currRoom = currRoom;}
    public Item getHeldItem() {return heldItem;}
    public void setHeldItem(Item heldItem) {this.heldItem = heldItem;}
    public Inventory getInventory() {return inv;}
    
    public void move() { //move within rooms
        switch(location) {
            case CENTER -> { //if in center, have options for all corners
                txt.typeWriterNormal("Choose which corner you'd like to go to:");
                System.out.println("1: First");
                System.out.println("2: Second");
                System.out.println("3: Third");
                System.out.println("4: Fourth");
                Move choice = chooseLocation(); // prompt choice
                setLocation(choice);
            }
            default -> { //if in corner, only move to center
                setLocation(Move.CENTER);
            }
        }
    }

    private Move chooseLocation() {
        while (true) { 
            String input = scnr.nextLine();
            if (input.equalsIgnoreCase("help")) {
                txt.printHelp();
                txt.typeWriterNormal("Now, select a corner. 1, 2, 3, or 4.");
                continue; //help command
            }
            if (input.equalsIgnoreCase("exit")) {
                txt.typeWriterNormal("There is no exiting from here. Believe me, I've tried. Now, select a corner.");
                continue; //exit command doesn't work, only from corners
            }
            if (input.equalsIgnoreCase("inv")) {
                openInventory();
                txt.typeWriterNormal("Now, select a corner. 1, 2, 3, or 4.");
                continue; //inv command
            }
            if (input.equalsIgnoreCase("use")) {
                useItem();
                txt.typeWriterNormal("Now, select a corner. 1, 2, 3, or 4.");
                continue; //use command
            }
            try {
                int locNum = Integer.parseInt(input);
                if (isValidInput(locNum)) { //if proper input
                    Move move = getLocFromNum(locNum);
                    txt.typeWriterNormal("You decide to move to the " + move.toString().toLowerCase() + " corner.");
                    return move; //returns choice
                }
                else {
                    txt.typeWriterNormal("You walk straight into a wall, as this is a square room, not an arbitrary n-sided room. Nice job.");
                } //goes back to start if integer is out of range
            } 
            catch (NumberFormatException e) {
                txt.typeWriterNormal("State a valid number, next time.");
            } // end catch
        } // end while
    } // end chooseLocation

    private boolean isValidInput(int i) { //for chooseLocation
        return (i == 1 || i == 2 || i == 3 || i == 4);
    }

    private Move getLocFromNum(int i) { //turns enum into int
        return switch(i) {
            case 1 -> Move.FIRST;
            case 2 -> Move.SECOND;
            case 3 -> Move.THIRD;
            case 4 -> Move.FOURTH;
            default -> null;
        };
    }

    public void moveRoom() { //incs room
        currRoom += 1;
        txt.typeWriterNormal("Walking through the exit, you enter a dark hallway, and at the other side, you enter Room " + currRoom);
    }

    public void openInventory() { 
        if (inv.getInventory().isEmpty()) { //don't open if empty
            txt.typeWriterNormal("You open your inventory. It's completely empty. Like your soul.");
            return;
        }

        txt.typeWriterNormal("You decide to open your inventory, inside, are...");
        int i = 1;
        for (Item it : inv.getInventory()) { //list items
            System.out.println(i + ": " + it.getName());
            i++;
        }

        System.out.println();
        txt.typeWriterNormal("Do you want to take out an item?");
        char c = txt.askYN(); //asks y/n to take out item
        boolean val = txt.ynToBool(c); // to bool, separate from above in case of printing char in future
        if (val) {
            heldItem = getItemChoice(); // if yes, get choice of item
        }
        else {
            txt.typeWriterNormal("You close your inventory, wondering why you even opened it in the first place.");
        }
    } // end openInventory

    private Item getItemChoice() {
        txt.typeWriterNormal("Input the number corresponding to the item you wish to take out.");
        while (true) { //until proper input
            String input = scnr.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (isValidInvIndex(num - 1)) { // n - 1 to account for indexing
                    if (heldItem != null) {
                        txt.typeWriterNormal("Before you take anything out, you put your" + heldItem.getName().toLowerCase() + " away.");
                        heldItem = null; //setting held item to null before pulling new item out
                    }
                    txt.typeWriterNormal("You take out your " + inv.getInventory().get(num - 1).getName().toLowerCase() + ".");
                    return inv.getInventory().get(num - 1); //returns item
                }
                else {
                    txt.typeWriterNormal("Did you think that would work? Input a correct number.");
                } // not valid index but int
            } 
            catch (NumberFormatException e) { // not int
                txt.typeWriterNormal("That's... not even a number. If I had a physical form, I'd facepalm.");
            }
        } // end while       
    } // end getItemChoice

    private boolean isValidInvIndex(int i) {
        for (int j = 0; j < inv.getInventory().size(); j++) {
            if (i == j) {return true;}
        }
        return false;
    } //determines if valid input

    public void useItem() { //uses item class, player uses heldItem 
        if (heldItem == null) {
            if (StateTracker.getInstance().getGlobalIters() == 0 || StateTracker.getInstance().savedCompanion()) {
                txt.typeWriterNormal("You are not holding anything, genius.");
            }
            else {
                txt.typeWriterNormal("... it seems you are not holding anything.");
            }
        }
        else {
            heldItem.use(); //from item abstract class
        }
    } // end useItem
} // end player class
