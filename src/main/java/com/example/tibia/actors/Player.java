package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor{
// attributes

    private Inventory inventory;
    private String nickName;

// constructors

    public Player(String nickName, Field field) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
    }

// getters & setters

// methods

    public void attack(){}
}
