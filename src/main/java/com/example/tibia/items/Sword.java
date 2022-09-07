package com.example.tibia.items;

import com.example.tibia.actors.ActorName;
import com.example.tibia.map.Field;

public class Sword extends Item {
    // attributes
    public Sword(Field field) {
        super(ItemName.SWORD.getName(), field);
        this.strength = 10;
        this.armor = 1;
    }
// constructors

// getters & setters

// methods
}
