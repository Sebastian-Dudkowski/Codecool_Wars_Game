package com.example.tibia.actors;

import com.example.tibia.map.Field;

import static com.example.tibia.sounds.SoundsPlayer.DROID_WALK;

public class Skeleton extends Actor{
// attributes

// constructors

    public Skeleton(Field field){
        super(ActorName.SKELETON.getName(), field);
        this.viewRange = 4;
        this.health = 100;
        this.strength = 5;
        this.walkingSound = DROID_WALK;
    }

// getters & setters

// methods
}
