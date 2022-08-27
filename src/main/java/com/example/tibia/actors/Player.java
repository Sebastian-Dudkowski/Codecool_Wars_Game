package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor {

// attributes

    private Inventory inventory;
    private String nickName;

// constructors

    public Player(String nickName, Field field, int health, int attackPower) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
        this.viewRange = 15;
        this.health = health;
        this.strength = attackPower;
    }

// getters & setters



// methods

    public void attack() {
    }
}
