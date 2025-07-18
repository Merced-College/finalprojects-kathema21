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

    public void playLoop(String filepath) {
        try {
            File musicPath = new File(filepath);

            if (!musicPath.exists()) {
                System.out.println("Music file not found: " + filepath);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            setVolume(-10.0f);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error occurred.");
        }
    }

    public void play(String filepath) {
        try {
            File musicPath = new File(filepath);

            if (!musicPath.exists()) {
                System.out.println("Music file not found: " + filepath);
                return;
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error occurred.");
        }
    }

     public void setVolume(float decibels) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(decibels); 
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
