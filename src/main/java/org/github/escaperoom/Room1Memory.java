package org.github.escaperoom;

import java.util.Random;
import java.util.Scanner;

public class Room1Memory {
    private boolean finished;
    private boolean initiated;
    private String[] solution;
    private final Scanner scnr;
    private final Random rand;
    private final TextMethods txt;

    public Room1Memory() {
        finished = false;
        initiated = false;
        solution = new String[4];
        scnr = new Scanner(System.in);
        rand = new Random();
        txt = TextMethods.getInstance();
    }

    public boolean isFinished() {return finished;}
    public void setFinished(boolean finished) {this.finished = finished;}
    public boolean isInitiated() {return initiated;}
    public void setInitiated(boolean initiated) {this.initiated = initiated;}

    public void initiateMemoryPuzzle() {
        txt.typeWriterNormal("A keypad lights up, displaying three steadily blinking lights.");
        txt.waitFor(500);
        if (initiated) {
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time.");
        }
        else {
            txt.typeWriterNormal("You hear a pattern... try to remember it.");
            initiated = true;
        } 
        while (true) {
            if (finished) {break;}
            solution = generateSolution();
            printSolution();
            generateBlackout(5);
            getPlayerArr();
        }
    }

    private String[] generateSolution() {
        String[] arr = new String[] {"BUZZ", "BOOP", "TAP", "DING", "GONG"};
        for (int i = 0; i < 5; i++) {
            int randNum = rand.nextInt(6);
            solution[i] = arr[randNum];
        }
        return arr;
    }

    private void printSolution() {
        for (int i = 0; i < 5; i++) {
            txt.waitFor(600);
            if (i == 4) {
                System.out.println(solution[i]);
            }
            else {
                System.out.println(solution[i] + ", ");
            }
        }
    }

    private void generateBlackout(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
        txt.waitFor(400);
        txt.typeWriterNormal("I was told to inform you to not scroll up. *Apparently*, you must think of the children, whatever that means.");
    }

    private void getPlayerArr() {
        txt.typeWriterNormal("Enter the pattern: (e.g., ding buzz click gong):");
        String input = scnr.nextLine();
        if (input.equals("help")) {
            // put fixed help here
        }
        if (input.equals("exit")) {
            
        }
        String[] playerInput = input.split(" ");
        if (playerInput.length != 5) {
             txt.typeWriterNormal("Three sounds. Three. That's not hard. Isn't it?");
        }
        else if (solution == playerInput) {
            txt.typeWriterNormal("The keypad chirps. Not bad, memory master.");
            finished = true;
        }
        else {
             txt.typeWriterNormal("Wrong. Maybe next time use your ears.");
        } // put alternative, prob make state tracking class for all this!
    }
}
