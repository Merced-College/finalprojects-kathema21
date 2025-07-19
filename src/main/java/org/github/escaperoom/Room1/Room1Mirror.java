
//Katelynn Prater - 7/18/25
//Third puzzle in room1 logic

package org.github.escaperoom.Room1;

import java.util.LinkedHashMap;
import java.util.Scanner;

import org.github.escaperoom.MusicPlayer;
import org.github.escaperoom.Pair;
import org.github.escaperoom.StateTracker;
import org.github.escaperoom.TextMethods;

public class Room1Mirror {
    private final TextMethods txt = TextMethods.getInstance();
    private final Scanner scnr = new Scanner(System.in);


    public void initiateMirror() {
        if (StateTracker.getInstance().isPuzzleFinished(0, 2)) {
             txt.typeWriterNormal("The mirror is already shattered.");
            txt.waitFor(1000);
            txt.typeWriterNormal("You still don't have a fleshy reflection.");
            return;
        }
        txt.typeWriterNormal("You approach the mirror.");
        txt.waitFor(600);
        txt.typeWriterNormal("You expect a reflection.");
        txt.waitFor(600);
        txt.typeWriterNormal("But you don't get one.");
        txt.waitFor(300);
        txt.typeWriterNormal("Not really.");

        txt.waitFor(1000);
        txt.typeWriterNormal("There's a shape.");
        txt.waitFor(500);
        txt.typeWriterNormal("It mimics your movements. But it's all wrong.");
        txt.waitFor(600);
        txt.typeWriterNormal("Too smooth. Too empty. Like it's copying motion without understanding its minutiae and spirit.");
        txt.waitFor(600);
        txt.typeWriterNormal("It, you doesn't blink. It doesn't breathe.");

        MusicPlayer.getInstance().play("src\\main\\java\\org\\github\\escaperoom\\Room1\\glass.wav");
        txt.waitFor(2000);

        txt.typeWriterNormal("The mirror splinters from the center, webbing outward.");
        txt.waitFor(400);
        txt.typeWriterNormal("Glass rains to the floor in quiet judgment.");
        txt.waitFor(800);

        txt.typeWriterNormal("Well. Now I am required to ask if you have questions. Go ahead, if you have any.");
        txt.waitFor(500);
        askQuestions();
        txt.waitFor(400);
        txt.typeWriterNormal("Okay, well. ... That's the puzzle. Don't look so shocked.");
        StateTracker.getInstance().setPuzzleFinished(0, 2);
    }
    
    
    private void askQuestions() {
        LinkedHashMap<Integer, Pair<String, Runnable>> questions = new LinkedHashMap<>();
        questions.put(1, new Pair<>("1: Um, no...", () -> txt.typeWriterNormal("I don't believe you. But alright.")));
        questions.put(2, new Pair<>("2: Why did it *do* that?", () -> txt.typeWriterNormal("If you'll believe me, it's made to taunt me about some inevitability. It's not about you.")));
        questions.put(3, new Pair<>("3: Was that me?", () -> txt.typeWriterNormal("What answer would you prefer I give you?")));

        while (true) {
            System.out.println();
            txt.typeWriterNormal("Type the number corresponding to your response.");
            for (int i : questions.keySet()) {
                System.out.println(questions.get(i).getKey());
            }
            String ans = scnr.nextLine();
            try {
                int num = Integer.parseInt(ans);
                if (txt.isAnswerHelpValid(questions, num)) {
                    txt.answerQuestion(questions, num);
                    questions.remove(num);
                    if (num == 1) {break;}
                    txt.typeWriterNormal("You still seem confused... anything else you'd like to ask me?");
                }
                else {
                    txt.typeWriterNormal("Speechless?");
                }
            } 
            catch (NumberFormatException e) {
                txt.typeWriterNormal("Speechless?");
            }
        } // end while loop
    } // end raskQuestions
} // end room1Mirror class
