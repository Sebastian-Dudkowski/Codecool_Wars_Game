package com.example.tibia.items;

import com.example.tibia.map.Field;

public class Lightsaber extends Item {

    public Lightsaber(Field field) {
        super(ItemName.SWORD.getName(), field);
        this.strength = 10;
        this.armor = 1;
    }

}
