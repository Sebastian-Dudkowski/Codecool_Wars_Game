package com.example.tibia.actors;

import com.example.tibia.Drawable;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Actor implements Drawable {

// attributes

    protected String name;
    protected Field field;
    protected int viewRange;
    protected int strength;
    protected int health;
    protected int mana;
    protected boolean alert;

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
    public void setViewRange(int range) { this.viewRange = range; }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setAlert(boolean alert) { this.alert = alert; }
    public boolean isAlert() {
        return alert;
    }


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

    public void attack(Actor target){
        target.setHealth(target.getHealth() - this.strength);
        if (target.getHealth() <= 0) {
            target.kill();
        }
    }

    public void kill(){
        this.field.getGameMap().removeNpc(this);
        this.field.setActor(null);
    }

    private boolean moveValidation(){
        return false;
    }

    @Override
    public String getTileName() {
        return name;
    }
}
