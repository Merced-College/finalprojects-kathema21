package org.github.escaperoom;
import org.github.escaperoom.Room1.Room1;

/**
 *
 * @author kathema21
 * 
 */
public class EscapeRoomMain {

    public static void main(String[] args) {
        TextMethods txt = TextMethods.getInstance();

        txt.typeWriterKat("Hello! Welcome to my escape room game (name still pending). It definitely isn't super professional, but I've had fun writing it so far!");
        txt.waitFor(400);
        txt.typeWriterKat("This game is *unfinished*, and only has one room out of the several I have planned. Moreover, while I did my best to do weird inputs to spot bugs, I probably couldn't find everything. As such, let me know if you come across any (talking to you, professor).");
        txt.waitFor(400);
        txt.typeWriterKat("As a final preliminary, bring your volume up as there will be some audio (not needed for gameplay) (by some I mean one track). It will be somewhat of a jumpscare, so check out the name of the file if necessary. It'll be pretty obvious when it'll play once you're there.");
        txt.waitFor(400);
        System.out.println();
        txt.typeWriterKat("Let's start!");
        System.out.println();
        System.out.println();
        txt.waitFor(700);
        Room1 r1 = new Room1();
        r1.runRoom();
    }
}
