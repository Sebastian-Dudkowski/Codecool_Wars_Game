package com.example.tibia.actors;

import com.example.tibia.map.Field;

import static com.example.tibia.sounds.SoundsPlayer.DROID_WALK;
import static com.example.tibia.sounds.SoundsPlayer.PLAYER_WALK_1;

public class Trooper extends Actor{

    public Trooper(Field field){
        super(ActorName.TROOPER.getName(), field);
        this.viewRange = 4;
        this.health = 100;
        this.strength = 5;
        this.walkingSound = ""; // TODO: add sound
    }
}
