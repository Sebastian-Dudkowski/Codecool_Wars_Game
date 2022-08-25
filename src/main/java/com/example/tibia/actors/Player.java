package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor {

// attributes
    private int viewRange = 9; // size of the displayed grid

    private Inventory inventory;
    private String nickName;

    private int health;


    private int attackPower;

// constructors

    public Player(String nickName, Field field, int health, int attackPower) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
        this.health = health;
        this.attackPower = attackPower;
    }

    // getters & setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getViewRange() {
        return viewRange;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
// methods

    public void attack() {
    }
}
