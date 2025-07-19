//Katelynn Prater - 7/18/25
// music player utility class to run music or audio

package org.github.escaperoom;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
    private Clip clip;
    private static final MusicPlayer musicPlayer = new MusicPlayer();

    public static final MusicPlayer getInstance() {
        return musicPlayer;
    }

    public void playLoop(String filepath) { //to loop if wanted music, used chatgpt cus I've never used this library b4
        try {
            File musicPath = new File(filepath);

            if (!musicPath.exists()) {
                System.out.println("Music file not found: " + filepath);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY); //loops continuously until stop()

            setVolume(-10.0f); //sets low volume
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error occurred.");
        }
    } // end playLoop

    public void play(String filepath) { //plays once
        try {
            File musicPath = new File(filepath);

            if (!musicPath.exists()) {
                System.out.println("Music file not found: " + filepath);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start(); //plays 
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error occurred.");
        }
    } // end play

     public void setVolume(float decibels) { //sets vol
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(decibels); 
        }
    }

    public void stop() { //stops audio
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    } 
} // end musicplayer class
