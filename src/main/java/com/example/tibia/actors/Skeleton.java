package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Skeleton extends Actor{
// attributes

// constructors

    public Skeleton(Field field){
        super(ActorName.SKELETON.getName(), field);
        this.viewRange = 4;
        this.health = 100;
        this.strength = 1;
    }

// getters & setters

// methods
}
