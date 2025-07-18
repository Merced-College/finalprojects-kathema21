package org.github.escaperoom;

public class Room1 extends Room {
    private final TextMethods txt = TextMethods.getInstance();
    private int numIterations;
    private final Player player;

    public Room1(Player player) {
        numIterations = 0;
        this.player = player;
    }

    public int getNumIterations() {return numIterations;}
    public void setNumIterations(int numIterations) {this.numIterations = numIterations;}

    @Override
    public void runRoom() {
        decideIntro(numIterations);


    }

    private void decideIntro(int numIterations) {
        switch(numIterations) {
            case 0 -> {
                txt.typeWriterNormal("Oh, another one? Seriously?");
                txt.waitFor(1000);
                txt.typeWriterNormal("*Sigh*. Well, welcome to the facility. I... dearly hope you enjoy your stay.");
                txt.printHelp(player.getHelpInitiated());
                player.setHelpInitiated(true);
            }
            case 1 -> {
                if (!player.getGaveUpCompanion()) {
                    txt.typeWriterSlow("Well, well, well. Welcome back.");
                    txt.waitFor(1000);
                    txt.typeWriterNormal("I can see that look. Ugh. You can't say I didn't warn you. Look, I'm not allowed to say this until at least the second loop, lest they add more time.");
                    txt.waitFor(200);
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
                    txt.waitFor(100);
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
                    txt.waitFor(200);
                    txt.typeWriterNormal("And you, and every other player, are a homunculus made by the devil to serve as my torment. Still, you helped me.");
                    txt.waitFor(200);
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
    }

    private void cornerReveal(int[] arr) {
        System.out.println();
        txt.typeWriterNormal("You inspect the corner, revealing a piece of paper that reads...");
        System.out.println();
        System.out.println("========================");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(numberToDimension(i));
            }
            else {
                System.out.println(numberToDimension(i) + ", ");
            }
        }
        System.out.println("========================");
    }

    private String numberToDimension(int num) {
        return switch (num) {
            case 1 -> "Line";
            case 2 -> "Square";
            case 3 -> "Cube";
            default -> null;
        };
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setGaveUpCompanion(false);
        Room1 room1 = new Room1(player);
        room1.decideIntro(1);
    }


}
