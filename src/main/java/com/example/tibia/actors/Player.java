package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor{

// attributes
    private int viewRange = 9; // size of the displayed grid

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
