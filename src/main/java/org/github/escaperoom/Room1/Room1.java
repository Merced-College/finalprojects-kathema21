//Katelynn Prater - 7/18/25
// Room1 class to store loop for room1 and further logic

package org.github.escaperoom.Room1;
import org.github.escaperoom.Player;
import org.github.escaperoom.Room;
import org.github.escaperoom.StateTracker;
import org.github.escaperoom.TextMethods;
import org.github.escaperoom.items.Paper;

public class Room1 extends Room {
    private final TextMethods txt = TextMethods.getInstance();
    private final Room1Buttons puzzle1;
    private final Room1Memory puzzle2;
    private final Room1Mirror puzzle3;
    private final Paper paper;
    private final Player player = Player.getInstance(); //one player only

    public Room1() { // constructor initializes all puzzles & collectable item
        puzzle1 = new Room1Buttons();
        puzzle2 = new Room1Memory();
        puzzle3 = new Room1Mirror();
        paper = new Paper(puzzle1);
    }

    public Room1Buttons getPuzzle1() {return puzzle1;}
    public Room1Memory getPuzzle2() {return puzzle2;}
    
    @Override // Room.java abstract class
    public void runRoom() {
        boolean removedItem = false;
        player.setLocation(Player.Move.CENTER); //starts player in center of room
        decideIntro(); // for later version of game when iterations (endless loop) changes narrator dialogue, not relevant here
        txt.typeWriterNormal("In the four corners of the room are various puzzles. Complete all three to finish the room, as stated prior.");
        while (!StateTracker.getInstance().isRoomFinished(0)) { 
            player.move(); //to corner
            switch (player.getLocation()) {
                case FIRST -> puzzle1.initiateButtonPuzzle();
                case SECOND -> puzzle2.initiateMemoryPuzzle();
                case THIRD -> puzzle3.initiateMirror();
                case FOURTH -> decidePaper();
                case CENTER -> {} // impossible to be here, can't move from center to center in .move()
            }
            if (StateTracker.getInstance().isPuzzleFinished(0, 0) && removedItem == false) { 
                player.getInventory().removeFirstItem(paper); //mechanic that dissolves item once used for puzzle
                removedItem = true;
            }
        }
        decideCompletionDialogue(); // irrelevant, always first iter as incomplete
    } // end runRoom

    public void decidePaper() { //logic to determine if paper is unactivated, taken, or ready to be taken
        if (!puzzle1.hasRevealedPaper()) {
            txt.typeWriterNormal("You see a blank wooden table with nothing on it. It is suspiciously bare. You wonder if it is hiding something from you.");
            txt.typeWriterNormal("You don't find anything yet, so you decide to move back to the center.");
            player.move();
        }
        else if (paper.getAcquired()) { 
            txt.typeWriterNormal("You return to the table where you found your paper. You don't see anything else here. You decide to return to the center.");
            player.move();
        }
        else {
            paper.cornerReveal();
            paper.introduce();
            player.getInventory().addItem(paper); 
        }
    } // end decidePaper

    private void decideCompletionDialogue() { //dialogue method
        switch(state.getRoomIter()) { //more narrator options
            case 0 -> {
                txt.typeWriterNormal("Well, that was frankly an unreasonable amount of time. I've seen players do this room much quicker than you.");
                txt.waitFor(300);
                txt.typeWriterNormal("Regardless, you've completed it. Don't celebrate yet, you have four more rooms to go.");
                txt.waitFor(700);
                txt.typeWriterNormal("... the door should be opening by--");
                txt.typeWriterNormal("Ah. There we go. In the far wall, you can hear the screeching of the door to the next room.");
                txt.waitFor(300);
                txt.typeWriterNormal("You decide to walk through into room 2.");
            }
            case 1 -> {
                if (StateTracker.getInstance().savedCompanion()) {
                    txt.typeWriterNormal("You wait until the far wall screeches, revealing a door to the second room.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("Well? What are you waiting for? Welcome to eternity.");
                }
                else {
                    txt.typeWriterNormal("You wait until the far wall screeches, revealing a door to the second room.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("Be my guest if you wish to stay here for a while.");
                }
            }
            default -> {
                txt.typeWriterNormal("The door screeches, yadda yadda.");
                txt.typeWriterNormal("Feel free to do whatever.");
            }
        } // end decideCompletionDialogue
    }

    private void decideIntro() { //more dialogue & iteration changes
        switch(state.getRoomIter()) {
            case 0 -> {
                txt.typeWriterNormal("Oh, another one? Seriously?");
                txt.waitFor(1000);
                txt.typeWriterNormal("*Sigh*. Well, welcome to the facility. I... dearly hope you enjoy your stay.");
                txt.printHelp();
                StateTracker.getInstance().setHelpInitiated();
            }
            case 1 -> {
                if (StateTracker.getInstance().savedCompanion()) {
                    txt.typeWriterSlow("Well, well, well. Welcome back.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("I can see that look. Ugh. You can't say I didn't warn you. Look, I'm not allowed to say this until at least the second loop, lest they add more time.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("But, I'm dead. That's why I have no form here. You don't really know or remember much before this, don't you?");
                    txt.waitFor(200);
                    txt.typeWriterNormal("Nobody to miss. To mourn. You don't even have a soul to suffer with. You get to enjoy these puzzles for the remainder of that timer.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("Oh, you'll get bored. In the beyond, there's a terminal, it can be killed. It ends time, here. It doesn't kill you, or me. To experience restitution requires linear time.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("This is my hell, and you are a homunculus crafted by the devil to torment me. You and every player that are playing these damned games.");
                    txt.waitFor(500);
                    txt.typeWriterNormal("Just. Do whatever you want. Some players like to lounge in their favorite rooms, daydream, speedrun the facility, whatever.");
                    txt.waitFor(200);
                    txt.typeWriterNormal("Any questions?");
                    txt.promptExitQuestions();
                }
                else {
                    txt.typeWriterSlow("...You burned it.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("I wasn't expecting you to. Barely anyone does.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("Most people... they see the incinerator, hear me beg, and they walk out the door. I can't really blame them. I've gotten used to the disappointment.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("But you. You actually gave it up. I wouldn't if I thought I could escape. Although, you haven't been here for millennia, like I have, so I don't know what you feel you gave up.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("Either way, maybe you trusted me. Maybe you just wanted to see what would happen. Doesn't matter, I suppose.");
                    txt.typeWriterNormal("Either way... thank you.");
                    txt.waitFor(800);
                    txt.typeWriterNormal("I've said some awful things to you. To every player, really. It's hard not to, after enough cycles. You start to rot inside.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("But for a moment--just a moment--it almost felt like it meant something.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("...Anyway. It won't last. You'll be back at the beginning like always. And I'll still be here. This is my personal hell, you know? Quite literally.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("And you, and every other player, are a homunculus made by the devil to serve as my torment. Still, you helped me.");
                    txt.waitFor(400);
                    txt.typeWriterNormal("Maybe having a little hope is more crushing than the alternative. But it was nice. Thank you for that.");
                    txt.waitFor(600);
                    txt.typeWriterNormal("Now, go ahead. Ask me anything.");
                    txt.promptBurntCompanionQuestions();
                }
            }
            default -> {
                txt.typeWriterNormal("Welcome back. Hope you had fun with your last iteration.");
                txt.waitFor(400);
                txt.typeWriterNormal("Now, I'm required to ask you if you have any questions, but I won't be answering anything. I'm too tired.");
                txt.waitFor(400);
                txt.typeWriterNormal("Any questions?");
                txt.promptFinalQuestion();
            }
        }
    } // end decideIntro

    public static void main(String[] args) {
        Room1 r1 = new Room1();
        r1.runRoom();
    }
} // end room1 class
