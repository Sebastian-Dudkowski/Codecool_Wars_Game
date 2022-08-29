package com.example.tibia.music;

import com.example.tibia.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicPlayer {
    public static String opening = "/opening2.wav";
    public static void playSound(String fileName,float volume) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream(fileName));
            clip.open(inputStream);
            setVolume(volume,clip);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void setVolume(float volume, Clip clip) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
