package org.github.escaperoom.items;

import java.util.ArrayList;

import org.github.escaperoom.Room1.Room1Buttons;
import org.github.escaperoom.TextMethods;

public class Paper extends Item {
    private final TextMethods txt = TextMethods.getInstance();
    private boolean acquired; // to prevent repetition of "finding" dialogue
    private final ArrayList<Integer> sol; // solution from Room1Buttons will be printed in cipher on here

    public Paper(Room1Buttons r1Puzzle) { //constructor
        super("Paper", "a thin piece of paper, the kind you cheaply get from an office supply store. On it are pristinely written geometric shapes in a line.", true);
        sol = r1Puzzle.getSolution();
        acquired = false;
    }

    public ArrayList<Integer> getSolution() {return sol;}
    public boolean getAcquired() {return acquired;}

    @Override
    public void introduce() {
        txt.typeWriterNormal("You decide to put it into your inventory. In order to open your inventory, type 'inv' in most prompts when you can type.");
        txt.waitFor(300);
        txt.typeWriterNormal("If you wish to use your item, type 'use'. Make sure you're holding the correct item. Last time, someone accidentally set themselves on fire. It'd be funny, but smoke is hard to get out of the walls.");
        txt.waitFor(700);
        txt.typeWriterNormal("Also, you can swap what you're holding by taking out something new from your inventory.");
        acquired = true;
    } // end introduce

    @Override
    public void use() { //to reread paper if desired
        txt.typeWriterNormal("You decide to reread the contents of the paper once more. On it are three geometric shapes.");
        System.out.println();
        System.out.println("========================");
        for (int i = 0; i < sol.size(); i++) {
            if (i == sol.size() - 1) { //to prevent extra comma
                System.out.println(numberToDimension(sol.get(i)));
            }
            else {
                System.out.println(numberToDimension(sol.get(i)) + ", ");
            }
        } // end for
        System.out.println("========================");
        System.out.println();
    } // end use
    
    public void cornerReveal() {
        System.out.println();
        txt.typeWriterNormal("You inspect the corner, revealing a piece of paper lying innocently atop the wood. It reads...");
        System.out.println();
        System.out.println("========================");
        for (int i = 0; i < sol.size(); i++) {
            if (i == sol.size() - 1) {
                System.out.println(numberToDimension(sol.get(i)));
            }
            else {
                System.out.println(numberToDimension(sol.get(i)) + ", ");
            }
        } // end for
        System.out.println("========================");
        System.out.println();
    } // all same as above but different dialogue to account for first interaction, end cornerreveal

    public String numberToDimension(int num) { //puzzle involves dimension of shape -> number
        return switch (num) {
            case 1 -> "Line";
            case 2 -> "Square";
            case 3 -> "Cube";
            default -> null;
        };
    }
} // end paper class
