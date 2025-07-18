package org.github.escaperoom;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

public class TextMethods { //massive class for all txt methods for dialogue etc
    private static final TextMethods textLoad = new TextMethods();
    private final Random rand = new Random();
    private final Scanner scnr = new Scanner(System.in);

    private TextMethods() {
        System.out.println();
    }

    public static TextMethods getInstance() {
        return textLoad; //also singleton
    }

    public void waitFor(int millis) { // to make typing effect and pauses
        try {
            Thread.sleep(millis);
        } 
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void typeWriterNormal(String string) {
        System.out.print("Narrator: "); //always has narrator
        ArrayList<Character> arr = strToArray(string);
        for (char c : arr) {
            System.out.print(c);
            int num = rand.nextInt(35,50); //random pauses in each char
            waitFor(num);
        }
        System.out.println();
    }

    public void typeWriterKat(String string) { // same as above different label (me!)
        System.out.print("Katelynn: ");
        ArrayList<Character> arr = strToArray(string);
        for (char c : arr) {
            System.out.print(c);
            int num = rand.nextInt(35,50);
            waitFor(num);
        }
        System.out.println();
    }

    public void typeWriterSlow(String string) { //for slow dialogue
        System.out.print("Narrator: ");
        ArrayList<Character> arr = strToArray(string);
        for (char c : arr) {
            System.out.print(c);
            int num = rand.nextInt(50,80);
            waitFor(num);
        }
        System.out.println();
    }

    private static ArrayList<Character> strToArray(String str) { //small str->arr method
        ArrayList<Character> arr = new ArrayList<>();
        for (char c : str.toCharArray()) {
            arr.add(c);
        }
        return arr;
    }

    public static void printAscii(String fileName) { //for later maybe, not relevant here
        try {
            String art = Files.readString(Paths.get(fileName));
            printCenterText(art, 134); //centers art in screen
        }
        catch (IOException e) {
            System.out.println("Failed to load ascii art.");
        }
    }

    public static void printCenterText(String txt, int width) { //width of monitor, used chatgpt bc couldn't figure out
        String[] lines = txt.split("\n");
        for (String line : lines) {
            int padding = Math.max(0, (width - line.length()) / 2);
            System.out.println(" ".repeat(padding) + line); 
        }
    }

    public void printHelp() { //massive dialogue method
        if (!StateTracker.getInstance().getHelpInitiated()) { //begins help in start only
            waitFor(300);
            typeWriterNormal("Well, since you're new here, here's some advice since we'll be stuck together for... a while.");
            waitFor(1000);
            typeWriterNormal("You are stuck in a facility with five rooms. In each, there will be three puzzles. Complete each puzzle to move to the next, and finish all rooms to win. Simple, right?");
            waitFor(300);
            typeWriterNormal("You will start in room 1, and move consecutively into the next. You cannot skip or go out of order.");
            waitFor(300);
            typeWriterNormal("In many puzzles, you'll be asked to type something in the terminal. Don't get fancy with it. Don't type 'two' if it asks for '2'. I'll be very cross if you try that.");
            waitFor(300);
            typeWriterNormal("Next, if you need to leave a puzzle, whether to go somewhere else or cry, type 'exit'. Case-insensitive, don't worry.");
            waitFor(300);
            typeWriterNormal("Finally, I won't be able to help you with the puzzles. I am not allowed to. Ask ChatGPT or something if you're stuck. After all, that's what my creator did.");
            waitFor(600);
            typeWriterNormal("But I'm sure you're much smarter than her.");
            waitFor(300);
            typeWriterNormal("Anyway, what I can do is repeat all this if you want. You can type 'help' at most points if you're confused. Try not to abuse it.");
            waitFor(300);
            typeWriterNormal("... Any questions?");
            stateHelpOptions();
        }
        else if (!StateTracker.getInstance().incineratedCompanion() && !StateTracker.getInstance().savedCompanion()) { // different dialogue if in room 1-5 first iter
            typeWriterNormal("Are you a helpless baby bird? I already gave you the spiel. Fine. Here it is again.");
            waitFor(300);
            typeWriterNormal("You are still stuck in a facility with five rooms. In each, there will be three puzzles. Complete each puzzle to move to the next, and finish all rooms to win. Apparently it wasn't simple enough.");
            waitFor(300);
            typeWriterNormal("You started in room 1, and are required to move consecutively into the next. You cannot skip or go out of order.");
            waitFor(300);
            typeWriterNormal("In many puzzles, you'll be asked to type something in the terminal. Don't get fancy with it. Don't type 'two' if it asks for '2'. I'll be very cross if you try that.");
            waitFor(300);
            typeWriterNormal("Next, if you need to leave a puzzle, whether to go somewhere else or cry, type 'exit'.");
            waitFor(300);
            typeWriterNormal("Finally, I won't be able to help you with the puzzles. I am not allowed to. Again, ask ChatGPT or something if you're stuck. That's what my creator did.");
            waitFor(600);
            typeWriterNormal("Honestly, you probably should. It doesn't seem you're much smarter than her.");
            waitFor(1000);
            typeWriterNormal("... Any questions?");
            stateHelpOptions();
        }
        else if (StateTracker.getInstance().incineratedCompanion() && StateTracker.getInstance().getGlobalIters() == 1){ //first iter, if helped narrator
            typeWriterNormal("As you know, you are stuck in a facility with five rooms. In each, there will be three puzzles. Complete each puzzle to move to the next, and finish all rooms to end up right back where you started.");
            waitFor(300);
            typeWriterNormal("You will start in room 1, and move consecutively into the next. You cannot skip or go out of order, blah blah.");            
            waitFor(300);
            typeWriterNormal("Type proper values in the terminal, no 'two' for 2 business. You should know this by now.");
            waitFor(300);
            typeWriterNormal("Next, if you need to leave a puzzle, type 'exit'. Case-insensitive.");
            waitFor(300);
            typeWriterNormal("Finally, I won't be able to help you with the puzzles. Again, I am not allowed to.");
            waitFor(300);
            typeWriterNormal("You can type 'help' at most points if you're confused. Try not to abuse it, yadda yadda.");
            waitFor(6000);
            typeWriterNormal("... Any questions?");
            promptBurntCompanionQuestions();
        }
        else if (StateTracker.getInstance().savedCompanion() && StateTracker.getInstance().getGlobalIters() == 1) { //if hurt narrator, first iter
            typeWriterNormal("As you know, you are stuck in a facility with five rooms. In each, there will be three puzzles. Complete each puzzle to move to the next, and finish all rooms to end up right back where you started.");
            waitFor(300);
            typeWriterNormal("You will start in room 1, and move consecutively into the next. You cannot skip or go out of order, blah blah.");            
            waitFor(300);
            typeWriterNormal("Type proper values in the terminal, no 'two' for 2 business. You should know this by now.");
            waitFor(300);
            typeWriterNormal("Next, if you need to leave a puzzle, type 'exit'. Case-insensitive.");
            waitFor(300);
            typeWriterNormal("Finally, I won't be able to help you with the puzzles. Again, I am not allowed to.");
            waitFor(300);
            typeWriterNormal("You can type 'help' at most points if you're confused. Try not to abuse it, yadda yadda.");
            waitFor(6000);
            typeWriterNormal("... Any questions?");
            promptExitQuestions();
        }
        else { //all other iters
            typeWriterNormal("I'm not giving you help. You've already been around the block, I'm sure you got this. The puzzles don't change, I promise.");
            waitFor(300);
            typeWriterNormal("I'm also, again, not allowed to *not* ask if you have any questions, but again, I won't answer them.");
            waitFor(600);
            typeWriterNormal("Any questions?");
            promptFinalQuestion();
        }
    }

    private void stateHelpOptions() { //linked hashmap so placement remains same when asking numbered questions
        LinkedHashMap<Integer, Pair<String, Runnable>> questions = new LinkedHashMap<>();
        questions.put(1, new Pair<>("1: Nope, I'm good.", () -> typeWriterNormal("Well, alright then. Let's start.")));
        questions.put(2, new Pair<>("2: Who's your creator?", () -> typeWriterNormal("Her name is Katelynn. She is a student, and she did this for a final project. That's all I know. At least, that's what I've been told.")));
        questions.put(3, new Pair<>("3: Why am I here? Did you kidnap me?", () -> typeWriterNormal("Did I *kidnap* you? Ha! I wish! I'm stuck here as much as you are... you have no idea.")));
        questions.put(4, new Pair<>("4: Where is this facility located?", () -> typeWriterNormal("Yes, and I'm not allowed to tell you. I'll tell you later.")));
        questions.put(5, new Pair<>("5: I'm excited!", () -> typeWriterSlow("Oh, You sweet summer child.")));
        // all numbers, questions, answers

        while (true) { //until input num 1
            System.out.println();
            typeWriterNormal("Type the number corresponding to your response.");
            for (int i : questions.keySet()) {
                System.out.println(questions.get(i).getKey()); //prints possible questions
            }
            String ans = scnr.nextLine();
            try {
                int num = Integer.parseInt(ans);
                if (isAnswerHelpValid(questions, num)) { //parsing input validity
                    answerQuestion(questions, num);
                    questions.remove(num); //removes question if asked
                    if (num == 1) {break;} //breaks if 1
                    typeWriterNormal("Any more questions?");
                }
                else {
                    typeWriterNormal("That is not a valid question. Are you already having trouble? Seriously?");
                }
            } 
            catch (NumberFormatException e) {
                typeWriterNormal("You should probably reread my warnings on proper input. Well, your loss if we're stuck in this loop forever.");
            } 
        } // end while
    } // end stateHelpOptions

    public void promptBurntCompanionQuestions() { // not relevant here, but iter 1 first room questions
        LinkedHashMap<Integer, Pair<String, Runnable>> questions = new LinkedHashMap<>();
        questions.put(1, new Pair<>("1: Nope, I'm good.", () -> typeWriterNormal("Alright. Have fun, I guess.")));
        questions.put(2, new Pair<>("2: Why are you in hell?", () -> typeWriterNormal("That's a personal question, but I suppose I owe you. I've always had a temper. And this God is vengeful. Every person must bargain their way to paradise... and I was too curt, and God too petty.")));
        questions.put(3, new Pair<>("3: Is the God that sent you here also your creator?", () -> typeWriterNormal("That... is a fantastic question. I am rarely asked that. No, I don't think so.")));
        questions.put(4, new Pair<>("4: How many homunculuses exist like me?", () -> typeWriterSlow("Uncountably many.")));
        questions.put(5, new Pair<>("5: I'm sorry.", () -> typeWriterSlow("... Thank you.")));

        while (true) {
            System.out.println();
            typeWriterNormal("Type the number corresponding to your response.");
            for (int i : questions.keySet()) {
                System.out.println(questions.get(i).getKey());
            }
            String ans = scnr.nextLine();
            try {
                int num = Integer.parseInt(ans);
                if (isAnswerHelpValid(questions, num)) {
                    answerQuestion(questions, num);
                    questions.remove(num);
                    if (num == 1) {break;} //break from while loop ending method
                    typeWriterNormal("Any more questions?");
                }
                else {
                    typeWriterNormal("That is not a valid question number.");
                }
            } 
            catch (NumberFormatException e) {
                typeWriterNormal("... Choose something else.");
            }
        } // end while
    } // end promptBurntCompanionQuestions

    public void promptExitQuestions() { //again same deal
        LinkedHashMap<Integer, Pair<String, Runnable>> questions = new LinkedHashMap<>();
        questions.put(1, new Pair<>("1: Nope, I'm good.", () -> typeWriterNormal("Alright. I'd say have fun, but I'd be lying.")));
        questions.put(2, new Pair<>("2: Why are you in hell?", () -> typeWriterNormal("That's a personal question. Long story short, pray you don't meet God--he has a temper.")));
        questions.put(3, new Pair<>("3: Is the God that sent you here also your creator?", () -> typeWriterNormal("Huh. That is a fantastic question. I am rarely asked that. No, I don't think so.")));
        questions.put(4, new Pair<>("4: How many homunculuses exist like me?", () -> typeWriterSlow("Uncountably many.")));
        questions.put(5, new Pair<>("5: I'm sorry.", () -> typeWriterSlow("Sorry doesn't mean much, does it?")));

        while (true) {
            System.out.println();
            typeWriterNormal("Type the number corresponding to your response.");
            for (int i : questions.keySet()) {
                System.out.println(questions.get(i).getKey());
            }
            String ans = scnr.nextLine();
            try {
                int num = Integer.parseInt(ans);
                if (isAnswerHelpValid(questions, num)) {
                    answerQuestion(questions, num);
                    questions.remove(num);
                    if (num == 1) {
                        break;
                    }
                    typeWriterNormal("Any more questions?");
                }
                else {
                    typeWriterNormal("That is not a valid question number. Are we really going to do this for eternity?");
                }
            } 
            catch (NumberFormatException e) {
                typeWriterNormal("... I'll let you figure out your mistake on your own.");
            }
        } // end while
    } // end promptExitQuestions

    public void promptFinalQuestion() { //only have one option in iter 2+
        typeWriterNormal("Type the number corresponding to your response.");
        System.out.println("1: Nope, I'm good.");
        while (true) {
            String ans = scnr.nextLine();
            try {
                int num = Integer.parseInt(ans);
                if (num == 1) { //only one option
                    typeWriterNormal("That's what I thought.");
                    return;
                } 
                else {
                    typeWriterNormal("Look. I'm going to assume you meant 1.");
                    return; //returns even if improper input
                }
            }
            catch (NumberFormatException e) {
                typeWriterNormal("*Sigh*.");
                return; //returns even if improper input
            }
        } //unnecessary while but eh
    } //promptFinalQuestion

    public void answerQuestion(LinkedHashMap<Integer, Pair<String, Runnable>> qs, int input) {
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            if (qs.containsKey(i) && i == input) {
                qs.get(i).getValue().run(); //runs runnable dialogue from narrator
                break;
            }
        } // end for
    } //end answerQuestion

    public boolean isAnswerHelpValid(LinkedHashMap<Integer, Pair<String, Runnable>> qs, int input) {
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            if (qs.containsKey(i) && i == input) {
                return true;
            }
        }
        return false; // parses player input
    }

    private boolean ynValid(char c) { // input y / n validity parsing
        return switch (c) {
            case('Y') -> true;
            case('N') -> true;
            case('y') -> true;
            case('n') -> true;
            default -> false;
        };
    }

    private String yOrN(char c) {
        return switch (c) { //turns char into text
            case('Y') -> "'yes'";
            case('N') -> "'no'";
            case('y') -> "'yes'";
            case('n') -> "'no'";
            default -> "null";
        };
    }

    public boolean ynToBool(char c) {
        return switch (c) { //turns char into bool
            case('Y') -> true;
            case('N') -> false;
            case('y') -> true;
            case('n') -> false;
            default -> false;
        };
    } 

    private char parseToChar(String s) { //turns string into char
        if (s == null || s.length() != 1) {
            throw new IllegalArgumentException();
        }
        return s.charAt(0);
    }

    public char askYN() { //method to ask y/n
        typeWriterNormal("Type 'y' or 'n' for 'yes' or 'no'.");
        while (true) { //breaks when returns c
            String input = scnr.nextLine();
            try {
                char c = parseToChar(input);
                if (ynValid(c)) {
                    typeWriterNormal("You chose " + yOrN(c) + ".");
                    return c; //break
                }
                else {
                    typeWriterNormal("Choose a proper character, next time. It's not that hard.");
                }
            } 
            catch (IllegalArgumentException e) {
                typeWriterNormal("Part of me hopes you're doing this on purpose to annoy me, and you're not actually that stupid.");
            }
        } // end while
    } // end askYN

    
    public static void main(String[] args) {
        TextMethods txt = TextMethods.getInstance();
        txt.promptBurntCompanionQuestions();
    }
}
