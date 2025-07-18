package org.github.escaperoom;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.github.escaperoom.Player.Move;

public class Room1Memory { // puzzle index 1
    private final String[] solution;
    private final Scanner scnr;
    private final Random rand;
    private final TextMethods txt;
    private final Player player = Player.getInstance();

    public Room1Memory() {
        solution = new String[4];
        scnr = new Scanner(System.in);
        rand = new Random();
        txt = TextMethods.getInstance();
    }

    public String[] getSolution() {return solution;}

    public void initiateMemoryPuzzle() {
        if (StateTracker.getInstance().isPuzzleFinished(0, 1)) {
            txt.typeWriterNormal("You already completed this puzzle, you don't need to be over here.");
            txt.waitFor(300);
            txt.typeWriterNormal("You return back to the center.");
            return;
        }

        player.setLocation(Move.SECOND);
        txt.typeWriterNormal("A keypad lights up, displaying three steadily blinking lights.");
        txt.waitFor(500);
        if (StateTracker.getInstance().isPuzzleVisited(0, 1)) {
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time.");
        }
        else {
            txt.typeWriterNormal("You hear a pattern... try to remember it.");
            StateTracker.getInstance().setPuzzleStarted(0, 1);
        } 

        while (true) {
            if (StateTracker.getInstance().isPuzzleFinished(0, 1)) {break;}
            if (player.getLocation().equals(Move.CENTER)) {break;}
            generateSolution();
            printSolution();
            txt.waitFor(1500);
            generateBlackout(5);
            getPlayerPattern();
        }
    }

    private void generateSolution() {
        String[] arr = new String[] {"BUZZ", "BOOP", "TAP", "DING", "GONG"};
        for (int i = 0; i < 4; i++) {
            int randNum = rand.nextInt(5);
            solution[i] = arr[randNum];
        }
    }

    private void printSolution() {
        for (int i = 0; i < 4; i++) {
            txt.waitFor(400);
            if (i == 3) {
                System.out.print(solution[i]);
            }
            else {
                System.out.print(solution[i] + ", ");
            }
        }
        System.out.println();
    }

    private void generateBlackout(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
        txt.waitFor(400);
        txt.typeWriterNormal("I was told to inform you to not scroll up. *Apparently*, you must think of the children, whatever that means.");
    }

    private void getPlayerPattern() {
        txt.typeWriterNormal("Enter the pattern, separated by spaces: (e.g., ding buzz click gong):");
        String input = scnr.nextLine();

        if (input.equalsIgnoreCase("help")) {
            txt.printHelp();
            return;
        }
        if (input.equalsIgnoreCase("exit")) {
            player.move();
            txt.typeWriterNormal("You shake your head, then return back to the center of the room.");
            return;
        }
        if (input.equalsIgnoreCase("inv")) {
            player.openInventory();
            return;
        }
        if (input.equalsIgnoreCase("use")) {
            player.useItem();
            return;
        }

        String[] playerInput = input.toUpperCase().split(" ");
        if (playerInput.length != 4) {
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                txt.typeWriterNormal("Four sounds. Four. It's not rocket science. I think. Come on.");
                txt.waitFor(300);
            }
            else {
                txt.typeWriterNormal("Did you think that the number of noises would change from loop to loop? It wont.");
            }
            txt.typeWriterNormal("The correct solution was:");
            printSolution();
            txt.typeWriterNormal("Lets try that again, shall we?");
            txt.waitFor(300);
            txt.typeWriterNormal("You hear a pattern... you hope to remember it this time.");
        }
        else if (Arrays.equals(solution, playerInput)) {
            txt.typeWriterNormal("The keypad chirps. Not bad.");
            StateTracker.getInstance().setPuzzleCompleted(0, 1);
            txt.typeWriterNormal("Nice work, you completed the second puzzle in this room. You have " + StateTracker.getInstance().getNumRemainingPuzzles(0) + "/3 remaining.");
        }
        else {
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                txt.typeWriterNormal("Wrong. Maybe next time use your ears.");
            }
            else {
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
    }

    public static void main(String[] args) {
        Room1Memory r1 = new Room1Memory();
        StateTracker.getInstance().setHelpInitiated();
        r1.initiateMemoryPuzzle();
    }
}
