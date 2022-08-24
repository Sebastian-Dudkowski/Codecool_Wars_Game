package com.example.tibia.map;

import com.example.tibia.Drawable;
import com.example.tibia.actors.Actor;

public class Field implements Drawable {
// attributes

    private FieldType type;
    private Actor actor;

// constructors

    public Field(FieldType type){
        this.type = type;
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

// methods
    @Override
    public String getTileName(){
        if (this.getActor() == null){
            return type.getTileName();
        }
        return actor.getTileName();
    }

    public Actor getActor() {
        return this.actor;
    }
}
