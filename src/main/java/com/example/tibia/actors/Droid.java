package com.example.tibia.actors;

import com.example.tibia.map.Field;

import static com.example.tibia.sounds.SoundsPlayer.DROID_WALK;

public class Droid extends Actor {
// attributes

// constructors

    public Droid(Field field) {
        super(ActorName.DROID.getName(), field);
        this.viewRange = 4;
        this.health = 100;
        this.strength = 5;
        this.exp = 50;
        this.walkingSound = DROID_WALK;
    }

// getters & setters

// methods
}
