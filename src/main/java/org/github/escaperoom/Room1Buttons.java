package org.github.escaperoom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.github.escaperoom.Player.Move;

public class Room1Buttons {
    private boolean finished;
    private boolean initiated;
    private final ArrayList<Integer> solution;
    private boolean revealedPaper;
    private final Scanner scnr;
    private final TextMethods txt;
    private final Random rand;
    private final Player player;

    public boolean getFinished() {return finished;}
    public boolean getInitiated() {return initiated;}
    public ArrayList<Integer> getSolution() {return solution;}
    public boolean hasRevealedPaper() {return revealedPaper;}

    public Room1Buttons(Player player) {
        rand = new Random();
        txt = TextMethods.getInstance();
        scnr = new Scanner(System.in);
        finished = false;
        initiated = false;
        solution = generateButtonSol();
        revealedPaper = false;
        this.player = player;
    }
    
    public void initiateButtonPuzzle() {
        if (finished) {
            txt.typeWriterNormal("You already completed this puzzle, you don't need to be over here.");
            return;
        }
        player.setLocation(Move.FIRST);
        if (!initiated) {
            txt.typeWriterNormal("In front of you, there seems to be three buttons. You stare at them like they hold all the secrets to the universe.");
            txt.waitFor(200);
            txt.typeWriterNormal("You wonder what would happen if you press a button.");
            initiated = true;
        }
        else {
             txt.typeWriterNormal("You refocus on the three buttons, hopefully with an idea to complete the puzzle.");
        }

        completeButtonPuzzle();
    }

    private void completeButtonPuzzle() {
        ArrayList<Integer> choices = new ArrayList<>();
        while (true) {
            if (player.isFirstIteration()) {
                int choice = chooseButtonFirst();
                if (choice == -1) {
                    return;
                }
                pressButton(choice);
                choices.add(choice);
                checkButtonPattern(choices, player.isFirstIteration());
            }
            else {
                int choice = chooseButtonLater();
                if (choice == -1) {
                    return;
                }
                pressButton(choice);
                choices.add(choice);
                checkButtonPattern(choices, player.isFirstIteration());
            }
            if (finished) return;
        }
    }

    private void checkButtonPattern(ArrayList<Integer> arr, boolean first) {
        if (arr.equals(solution)) {
            if (first) {
                txt.typeWriterNormal("Huh. You got it. Guess you knew about dimensions.");
                finished = true;
            }
            else {
                txt.typeWriterNormal("Not as impressive after the first time around, unfortunately.");
                finished = true;
            }
        }
    }
    
    private ArrayList<Integer> generateButtonSol() {
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            intList.add(rand.nextInt(3) + 1);
        }
        return intList;
    }

    private int chooseButtonFirst() {
        txt.waitFor(200);
        txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3 into the terminal to press the respective button.");
        String choice = scnr.nextLine();

        while (true) {
            if (choice.equalsIgnoreCase("exit")) {
                txt.waitFor(200);
                txt.typeWriterNormal("You decide to walk away from this puzzle, for now.");
                player.move();
                return -1;
            }

            if (choice.equalsIgnoreCase("help")) {
                txt.waitFor(200);
                txt.printHelp(player.getHelpInitiated());
                txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
                choice = scnr.nextLine();
                continue;
            }
            try {
                int numChoice = Integer.parseInt(choice);
                if (!validInt(numChoice)) {
                    int dialogueOption = rand.nextInt(5);
                    txt.waitFor(200);
                    selectButtonDialogueOptionsFirst(dialogueOption, choice);
                }
                else {
                    txt.waitFor(200);
                    txt.typeWriterNormal("You decide to press button " + choice + ".");
                    return Integer.parseInt(choice);
                }
            }
            catch (NumberFormatException e) {
                int dialogueOption = rand.nextInt(5);
                selectButtonDialogueOptionsFirst(dialogueOption, choice);
            }
            choice = scnr.nextLine();
        }
    }

    private int chooseButtonLater() {
        txt.waitFor(200);
        txt.typeWriterNormal("Choose a button. Enter 1, 2, or 3.");
        String choice = scnr.nextLine();

        while (true) { 
            if (choice.equalsIgnoreCase("exit")) {
                txt.waitFor(200);
                txt.typeWriterNormal("You decide to walk away from this puzzle, for now.");
                return -1;
            }

            if (choice.equalsIgnoreCase("help")) {
                txt.waitFor(200);
                txt.printHelp(player.getHelpInitiated());
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
                    txt.typeWriterNormal("You decide to press button " + choice + ".");
                    return Integer.parseInt(choice);
                }
            }
            catch (NumberFormatException e) {
                txt.waitFor(200);
                txt.typeWriterNormal("For some reason, you decide to mime pressing a button in thin air.");
            }
            choice = scnr.nextLine();
        }
    }

    private boolean validInt(int num) {
        return (num == 1 || num == 2 || num == 3);
    }

    private void selectButtonDialogueOptionsFirst(int num, String input) {
        switch (num) {
            case 0 -> {txt.typeWriterNormal("That's not a valid button. Clearly, you don't know what the numbers 1, 2, or 3 are. Try again.");}
            case 1 -> {txt.typeWriterNormal("What the hell is '" + input + "'? At least the others weren't as stupid as you.");}
            case 2 -> {txt.typeWriterNormal("You pressed the '" + input + "' input button. Nothing happened, because it's not a real button.");}
            case 3 -> {txt.typeWriterNormal("Is that a even real word?");}
            case 4 -> {txt.typeWriterNormal("If I had a wrong buzzer, I'd press it right about now.");}
        }
    }
    
    private void pressButton(int number) {
        switch (number) {
            case 1 -> txt.typeWriterNormal("You press the first button. It made a satisfying click.");
            case 2 -> txt.typeWriterNormal("You press the second button. It stuck in the socket a little.");
            case 3 -> {
                if (!revealedPaper) {
                    txt.typeWriterNormal("You press the third button. In the fourth corner of the room, you hear a whirring...");
                    txt.typeWriterNormal("Type 'exit' to investigate.");
                    revealedPaper = true;
                }
                else {
                    txt.typeWriterNormal("You press the third button with apparently an abundance of purpose. ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player();
        player1.setFirstIteration(false);
        player1.setHelpInitiated(true);
        Room1Buttons room1 = new Room1Buttons(player1);
        room1.initiateButtonPuzzle();
    }
}
