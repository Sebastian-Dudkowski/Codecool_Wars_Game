package com.example.tibia.actors;

import com.example.tibia.map.Field;

import static com.example.tibia.sounds.SoundsPlayer.TROOPER_WALK;

public class Trooper extends Actor{

    public Trooper(Field field){
        super(ActorName.TROOPER.getName(), field);
        this.viewRange = 4;
        this.health = 150;
        this.strength = 10;
        this.walkingSound = TROOPER_WALK;
    }
}
