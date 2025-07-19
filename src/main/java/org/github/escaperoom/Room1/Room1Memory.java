// Katelynn Prater - 7/18/25
// second puzzle for room1 ; memory

package org.github.escaperoom.Room1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.github.escaperoom.Player;
import org.github.escaperoom.Player.Move;
import org.github.escaperoom.StateTracker;
import org.github.escaperoom.TextMethods;

public class Room1Memory { // puzzle index 1
    private final String[] solution; //random sol for puzzle, NOT static when made
    private final Scanner scnr;
    private final Random rand;
    private final TextMethods txt;
    private final Player player = Player.getInstance();

    public Room1Memory() { //constructor 
        solution = new String[4];
        scnr = new Scanner(System.in);
        rand = new Random();
        txt = TextMethods.getInstance();
    }

    public String[] getSolution() {return solution;}

    public void initiateMemoryPuzzle() { //starts puzzle
        if (StateTracker.getInstance().isPuzzleFinished(0, 1)) {
            txt.typeWriterNormal("You already completed this puzzle, you don't need to be over here.");
            txt.waitFor(300);
            txt.typeWriterNormal("You return back to the center.");
            return; //goes back to center if completed
        }

        player.setLocation(Move.SECOND); //moves only if not done
        txt.typeWriterNormal("A keypad lights up, displaying three steadily blinking lights.");
        txt.waitFor(500);
        if (StateTracker.getInstance().isPuzzleStarted(0, 1)) {
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time."); //if started, new dialogue
        }
        else {
            txt.typeWriterNormal("You hear a pattern... try to remember it.");
            StateTracker.getInstance().setPuzzleStarted(0, 1);
        } 

        while (true) { //loop until exit or completed successfully
            if (StateTracker.getInstance().isPuzzleFinished(0, 1)) {break;} //breaks if complete
            if (player.getLocation().equals(Move.CENTER)) {break;} //breaks if moves to center from exit
            generateSolution(); //make new random sol
            printSolution(); //prints to memorize
            txt.waitFor(1500);
            generateBlackout(30); //no scrolling up
            getPlayerPattern(); //gets player input
        } // end while loop
    } // end initiateMemoryPuzzle

    private void generateSolution() { //generates new rand sol
        String[] arr = new String[] {"BUZZ", "BOOP", "TAP", "DING", "GONG"};
        for (int i = 0; i < 4; i++) {
            int randNum = rand.nextInt(5);
            solution[i] = arr[randNum];
        }
    }

    private void printSolution() { // prints rand sol
        for (int i = 0; i < 4; i++) {
            txt.waitFor(400);
            if (i == 3) {
                System.out.print(solution[i]); // no extra comma
            }
            else {
                System.out.print(solution[i] + ", "); 
            }
        }
        System.out.println();
    }

    private void generateBlackout(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(); // many printlns
        }
        txt.waitFor(400);
        txt.typeWriterNormal("I was told to inform you to not scroll up. *Apparently*, you must think of the children, whatever that means.");
    }

    private void getPlayerPattern() { //player input
        txt.typeWriterNormal("Enter the pattern, separated by spaces: (e.g., ding buzz click gong):");
        String input = scnr.nextLine();

        if (input.equalsIgnoreCase("help")) {
            txt.printHelp();
            return; // goes back to while loop in initiateMemoryPuzzle
        }
        if (input.equalsIgnoreCase("exit")) {
            player.move(); // breaks loop in initiateMemory
            txt.typeWriterNormal("You shake your head, then return back to the center of the room.");
            return; // goes back to while loop in initiateMemoryPuzzle
        }
        if (input.equalsIgnoreCase("inv")) {
            player.openInventory();
            return; // goes back to while loop in initiateMemoryPuzzle
        }
        if (input.equalsIgnoreCase("use")) {
            player.useItem();
            return; // goes back to while loop in initiateMemoryPuzzle
        }

        String[] playerInput = input.toUpperCase().split(" ");
        if (playerInput.length != 4) { // !=4 tokens
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                txt.typeWriterNormal("Four sounds. Four. It's not rocket science. I think. Come on.");
                txt.waitFor(300);
            }
            else { // new dialogue iter
                txt.typeWriterNormal("Did you think that the number of noises would change from loop to loop? It wont.");
            }
            txt.typeWriterNormal("The correct solution was:");
            printSolution();
            txt.typeWriterNormal("Lets try that again, shall we?");
            txt.waitFor(300);
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time.");
        } //goes back to while loop
        else if (Arrays.equals(solution, playerInput)) {
            txt.typeWriterNormal("The keypad chirps. Not bad.");
            StateTracker.getInstance().setPuzzleFinished(0, 1); //breaks while loop
            txt.typeWriterNormal("Nice work, you completed the second puzzle in this room. You have " + StateTracker.getInstance().getNumRemainingPuzzles(0) + "/3 remaining.");
        } //complete successfully
        else {
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                txt.typeWriterNormal("Wrong. Maybe next time use your ears.");
            }
            else { //more dialogue
                txt.typeWriterNormal("Come on, I know you got this. Maybe.");
            }
            txt.waitFor(300);
            txt.typeWriterNormal("The correct solution was:");
            printSolution();
            System.out.println();
            txt.typeWriterNormal("Lets try that again, shall we?");
            txt.waitFor(300);
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time.");
        }
    } // end getPlayerPattern

    public static void main(String[] args) {
        Room1Memory r1 = new Room1Memory();
        StateTracker.getInstance().setHelpInitiated();
        r1.initiateMemoryPuzzle();
    }
} // end room1memory class
