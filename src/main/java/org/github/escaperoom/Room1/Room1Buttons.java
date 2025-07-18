// Katelynn Prater - 7/18/25
// Logic for first puzzle in room 1

package org.github.escaperoom.Room1;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.github.escaperoom.Player;
import org.github.escaperoom.Player.Move;
import org.github.escaperoom.StateTracker;
import org.github.escaperoom.TextMethods;

public class Room1Buttons { // puzzle index 0
    private final ArrayList<Integer> solution; //solution shared to paper object
    private boolean revealedPaper; // to ensure paper isn't found in corner until reveal
    private final Scanner scnr;
    private final TextMethods txt;
    private final Random rand;
    private final Player player = Player.getInstance();

    public ArrayList<Integer> getSolution() {return solution;}
    public boolean hasRevealedPaper() {return revealedPaper;}

    public Room1Buttons() { // constructor
        rand = new Random();
        txt = TextMethods.getInstance();
        scnr = new Scanner(System.in);
        solution = generateButtonSol();
        revealedPaper = false;
    }
    
    public void initiateButtonPuzzle() {
        if (StateTracker.getInstance().isPuzzleFinished(0, 0)) { //room 1 = 0, puzzle 1 = 0
            txt.typeWriterNormal("You already completed this puzzle, you don't need to be over here.");
            txt.waitFor(300);
            txt.typeWriterNormal("You return back to the center.");
            return;
        }

        player.setLocation(Move.FIRST); //move after check
        if (!StateTracker.getInstance().isPuzzleStarted(0, 0)) { // ! changes dialogue if already visited
            txt.typeWriterNormal("In front of you, there seems to be three buttons. You stare at them like they hold all the secrets to the universe.");
            txt.waitFor(300);
            txt.typeWriterNormal("You wonder what would happen if you press a button.");
            StateTracker.getInstance().setPuzzleStarted(0, 0); // set started
        }
        else { // if visited
            txt.typeWriterNormal("You refocus on the three buttons, hopefully with an idea to complete the puzzle.");
        }

        completeButtonPuzzle(); // begins loop of button presses until 'exit'
    } // end initiateButtonPuzzle

    private void completeButtonPuzzle() {
        ArrayList<Integer> choices = new ArrayList<>(); //resets choices so is comparable to solution
        while (true) {
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                int choice = chooseButtonFirst(); // later vs first is again different dialogue
                if (choice == -1) { 
                    return; // exit cue
                }
                pressButton(choice);
                choices.add(choice); //adds choice to compare
                checkButtonPattern(choices); //always checks after each choice
            }
            else {
                int choice = chooseButtonLater(); //iter dialogue 
                if (choice == -1) { //exit cue
                    return;
                }
                pressButton(choice);
                choices.add(choice);
                checkButtonPattern(choices); //always checks after each choice
            }
            if (StateTracker.getInstance().isPuzzleFinished(0, 0)) return; //returns if puzzle finished successfully
        } // end while loop
    } //end completeButtonPuzzle

    private void checkButtonPattern(ArrayList<Integer> arr) {
        if (arr.equals(solution)) {
            if (StateTracker.getInstance().getRoomIter(0) == 0) {
                txt.typeWriterNormal("Huh. You got it. Guess you knew about dimensions.");
                StateTracker.getInstance().setPuzzleFinished(0, 0); //sets puzzle finished, returns completeButtonPuzzles
                txt.typeWriterNormal("Excellent. You completed the second puzzle in this room. You have " + StateTracker.getInstance().getNumRemainingPuzzles(0) + "/3 remaining.");
            }
            else {
                txt.typeWriterNormal("Not as impressive after the first time around, unfortunately.");
                StateTracker.getInstance().setPuzzleFinished(0, 0); //sets puzzle finished, returns completeButtonPuzzles
                txt.typeWriterNormal("Excellent. You completed the second puzzle in this room. You have " + StateTracker.getInstance().getNumRemainingPuzzles(0) + "/3 remaining.");
            }
        }
    } //end checkButtonPattern
    
    private ArrayList<Integer> generateButtonSol() { //generates random solution 
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            intList.add(rand.nextInt(3) + 1);
        }
        return intList;
    }

    public void printButtonSol() {
        for (int i : solution) {
            System.out.print(i + " ");
        }
    } //for dev

    private int chooseButtonFirst() {
        txt.waitFor(200);
        txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3 into the terminal to press the respective button.");
        String choice = scnr.nextLine();

        while (true) {
            if (choice.equalsIgnoreCase("exit")) {
                txt.waitFor(200);
                txt.typeWriterNormal("You decide to walk away from this puzzle, for now.");
                return -1; //exit cue in complete button puzzle
            }

            if (choice.equalsIgnoreCase("help")) {
                txt.waitFor(200);
                txt.printHelp(); //reprints controls
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue; //doesn't return to center
            }

            if (choice.equalsIgnoreCase("inv")) { //opens inventory
                player.openInventory();
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue; //doesn't return to center
            }

            if (choice.equalsIgnoreCase("use")) {
                player.useItem();
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue; //doesn't return to center
            }

            try { // if inproper input ie 1 2 3
                int numChoice = Integer.parseInt(choice);
                if (!validInt(numChoice)) {
                    int dialogueOption = rand.nextInt(5);
                    txt.waitFor(200);
                    selectButtonDialogueOptionsFirst(dialogueOption, choice); //selects random snarky comment
                }
                else {
                    txt.waitFor(200);
                    return Integer.parseInt(choice); //finally returns player input if proper input
                }
            }
            catch (NumberFormatException e) {
                int dialogueOption = rand.nextInt(5);
                selectButtonDialogueOptionsFirst(dialogueOption, choice); //selects random comment
            }
            choice = scnr.nextLine();
        }
    }

    private int chooseButtonLater() { //same method as above, probably needs to be consolidated + different dialogue
        txt.waitFor(200);
        txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
        String choice = scnr.nextLine();

        while (true) { 
            if (choice.equalsIgnoreCase("exit")) {
                txt.waitFor(200);
                txt.typeWriterNormal("You decide to walk away from this puzzle, for now.");
                return -1; // cue for exit
            }

            if (choice.equalsIgnoreCase("help")) {
                txt.waitFor(200);
                txt.printHelp();
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue;
            }

            if (choice.equalsIgnoreCase("inv")) {
                player.openInventory();
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue;
            }

            if (choice.equalsIgnoreCase("use")) {
                player.useItem();
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue;
            }

            try {
                int numChoice = Integer.parseInt(choice);
                if (!validInt(numChoice)) {
                    txt.waitFor(200);
                    txt.typeWriterNormal("Does this amuse you? I don't understand why you're doing this.");
                }
                else {
                    txt.waitFor(200);
                    return Integer.parseInt(choice); //returns button press choice 
                }
            }
            catch (NumberFormatException e) {
                txt.waitFor(200);
                txt.typeWriterNormal("For some reason, you decide to mime pressing a button in thin air.");
            }
            choice = scnr.nextLine();
        }
    } // end chooseButtonLater

    private boolean validInt(int num) { //input parsing
        return (num == 1 || num == 2 || num == 3);
    }

    private void selectButtonDialogueOptionsFirst(int num, String input) {
        switch (num) { //chooses random snarky comment for first iter if improper input
            case 0 -> {txt.typeWriterNormal("That's not a valid button. Clearly, you don't know what the numbers 1, 2, or 3 are. Try again.");}
            case 1 -> {txt.typeWriterNormal("What the hell is '" + input + "'? At least the others weren't as stupid as you.");}
            case 2 -> {txt.typeWriterNormal("You pressed the '" + input + "' input button. Nothing happened, because it's not a real button.");}
            case 3 -> {txt.typeWriterNormal("Is that a even real word?");}
            case 4 -> {txt.typeWriterNormal("If I had a wrong buzzer, I'd press it right about now.");}
        }
    }
    
    private void pressButton(int number) { //method to output text for each button press
        switch (number) {
            case 1 -> txt.typeWriterNormal("You press the first button. It made a satisfying click.");
            case 2 -> txt.typeWriterNormal("You press the second button. It stuck in the socket a little.");
            case 3 -> { //reveal paper if press third button
                if (!revealedPaper) {
                    txt.typeWriterNormal("You press the third button. In the fourth corner of the room, you hear a whirring...");
                    txt.typeWriterNormal("Type 'exit' to investigate.");
                    revealedPaper = true; //allows paper to be visible and grabable 
                }
                else { //changes dialogue if paper was revealed
                    txt.typeWriterNormal("You press the third button with apparently an abundance of purpose. ");
                }
            }
        }
    } // end pressButton method

    public static void main(String[] args) {
        Room1Buttons room1 = new Room1Buttons();
        StateTracker.getInstance().setHelpInitiated();
        room1.printButtonSol();
        room1.initiateButtonPuzzle();
    }
} // end room1buttons class
