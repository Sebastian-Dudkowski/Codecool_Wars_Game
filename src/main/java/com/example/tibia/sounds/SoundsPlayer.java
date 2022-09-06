package com.example.tibia.sounds;

import com.example.tibia.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundsPlayer {
    public static final String OPENING = "/sounds/opening.wav";
    public static final String LIGHTSABER_SWING_1 = "/sounds/lightsaber_swing_1.wav";
    public static final String LIGHTSABER_SWING_2 = "/sounds/lightsaber_swing_2.wav";
    public static final String LIGHTSABER_SWING_3 = "/sounds/lightsaber_swing_3.wav";
    public static final String LIGHTSABER_HIT_1 = "/sounds/lightsaber_hit_1.wav";
    public static final String LIGHTSABER_HIT_2 = "/sounds/lightsaber_hit_2.wav";
    public static final String DEATH = "/sounds/oof.wav";
    public static final String DOOR_OPEN = "/sounds/door_open.wav";
    public static final String PLAYER_WALK_1 = "/sounds/player_walk_1.wav";
    public static final String PLAYER_WALK_2 = "/sounds/player_walk_2.wav";
    public static final String DROID_WALK = "/sounds/droid_walk.wav";
    public static final String PICK_UP = "/sounds/pick_up.wav";
    public static void playSound(String fileName,float volume) {
        try {
            Clip clip = AudioSystem.getClip();
            URL url = Main.class.getResource(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
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
