package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor{

    // attributes
    // size of the displayed grid
    private int viewRange = 9;

    private Inventory inventory;
    private String nickName;

// constructors

    public Player(String nickName, Field field) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
    }

// getters & setters

    public int getViewRange() {
        return viewRange;
    }

// methods

    public void attack(){}
}
