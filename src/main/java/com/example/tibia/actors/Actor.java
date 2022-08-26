package com.example.tibia.actors;

import com.example.tibia.Drawable;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;

public abstract class Actor implements Drawable {

// attributes
    private String name;
    private int health;
    private Field field;
    protected int viewRange = 9;

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
    public int getX() {
        return field.getX();
    }

    public int getY() {
        return field.getY();
    }

    public int getViewRange() { return viewRange; }

// methods

    public void move(int dx, int dy) {
        Field nextField = field.getNeighbor(dx, dy);
        if (nextField.getActor() != null || nextField.getType() != FieldType.FLOOR){
            return;
        }
        field.setActor(null);
        nextField.setActor(this);
        field = nextField;
    }

    private boolean moveValidation(){
        return false;
    }

    @Override
    public String getTileName() {
        return name;
    }
}
