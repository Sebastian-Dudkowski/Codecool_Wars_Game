package com.example.tibia.map;

import com.example.tibia.Drawable;
import com.example.tibia.actors.Actor;

public class Field implements Drawable {

// attributes
    private final int X;
    private final int Y;
    private FieldType type;
    private Actor actor;

// constructors

    public Field(FieldType type, int x, int y){
        this.type = type;
        this.X = x;
        this.Y = y;
    }

// getters & setters

    public FieldType getType() {
        return type;
    }
    public void setType(FieldType type) {
        this.type = type;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public Actor getActor() {
        return this.actor;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

// methods
    @Override
    public String getTileName(){
        if (actor != null){
            return actor.getTileName();
        }
        return type.getTileName();
    }

}
