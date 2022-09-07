package com.example.tibia.actors;

import com.example.tibia.Drawable;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;

import static com.example.tibia.sounds.SoundsPlayer.DEATH;
import static com.example.tibia.sounds.SoundsPlayer.playSound;

public abstract class Actor implements Drawable {

// attributes

    protected String name;
    protected Field field;
    protected int viewRange;
    protected int strength;

    protected int exp;
    protected int playerLvl;

    protected int health;
    protected int mana;
    protected boolean alert;
    protected boolean facingRight = true;
    protected boolean attacking = false;
    protected String walkingSound;

// constructors

    public Actor(){}

    public Actor(String name, Field field){
        this.name = name;
        this.field = field;
    }

// getters & setters

    public String getName() {
        return name;
    }
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
    public boolean isAttacking(){ return this.attacking; }
    public boolean isFacingRight() {
        return facingRight;
    }
    public String getWalkingSound() { return walkingSound; }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public int getPlayerLvl() {
        return playerLvl;
    }

    public void setPlayerLvl(int playerLvl) {
        this.playerLvl = playerLvl;
    }
// methods

    public void move(int dx, int dy) {
        int currentX = field.getX();
        Field nextField = field.getNeighbor(dx, dy);
        if (nextField.getActor() != null || nextField.getType() != FieldType.FLOOR && nextField.getType() != FieldType.NEXT){
            return;
        }
        field.setActor(null);
        nextField.setActor(this);
        field = nextField;
        int newX = field.getX();
        if (currentX < newX){
            this.facingRight = true;
        }
        if (currentX > newX){
            this.facingRight = false;
        }
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
        playSound(DEATH, 1f);
    }

    @Override
    public String getTileName() {
        if (alert){
            return (facingRight) ? name + " right alert" : name + " left alert";
        }
        return (facingRight) ? name + " right" : name + " left";
    }
}
