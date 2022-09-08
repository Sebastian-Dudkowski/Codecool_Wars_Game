package com.example.tibia.actors;

import com.example.tibia.map.Field;

import static com.example.tibia.sounds.SoundsPlayer.GUARD_WALK;

public class Guard extends Actor{
// attributes

// constructors

    public Guard(Field field){
        super(ActorName.GUARD.getName(), field);
        this.viewRange = 8;
        this.health = 400;
        this.strength = 15;
        this.walkingSound = GUARD_WALK;
    }

// getters & setters

// methods
}
