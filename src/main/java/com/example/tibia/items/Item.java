package com.example.tibia.items;

import com.example.tibia.Drawable;
import com.example.tibia.map.Field;

public abstract class Item implements Drawable {
// attributes

    private String name;
    private Field field;
    protected int strength;
    protected int armor;


// constructors


    public Item(String name, Field field) {
        this.name = name;
        this.field = field;
    }

// getters & setters
    public Field getField() {
        return this.field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    // methods

    @Override
    public String getTileName() {
        return name;
    }
}
