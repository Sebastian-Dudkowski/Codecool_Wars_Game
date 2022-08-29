package com.example.tibia.actors;

import com.example.tibia.map.Field;

public class Player extends Actor {

// attributes

    private Inventory inventory;
    private String nickName;

// constructors

    public Player(String nickName, Field field, int health,int mana, int attackPower) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
        this.viewRange = 15;
        this.health = health;
        this.mana = mana;
        this.strength = attackPower;
    }

// getters & setters



// methods

    public void attack() {
        for (int x=-1; x<=1; x++){
            for (int y=-1; y<=1; y++){
                if (!(x==0 && y==0)){
                    Actor target = this.getField().getNeighbor(x, y).getActor();
                    if (target != null){
                        attack(target);
                    }
                }
            }
        }
    }
}
