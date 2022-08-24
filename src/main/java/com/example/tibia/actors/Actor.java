package com.example.tibia.actors;

import com.example.tibia.Drawable;
import com.example.tibia.map.Field;

public abstract class Actor implements Drawable {

// attributes
    private String name;
    private int health;
    private Field field;

// constructors

    public Actor(){}

    public Actor(String name, Field field){
        this.name = name;
        this.field = field;
    }

// getters & setters

    public Field getField() { return this.field; }
    public void setField(Field field) {
        this.field = field;
    }

// methods

    public void move(){}

    private boolean moveValidation(){
        return false;
    }

    @Override
    public String getTileName() {
        return name;
    }
}
