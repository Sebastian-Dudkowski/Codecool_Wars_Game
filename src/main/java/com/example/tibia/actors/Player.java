package com.example.tibia.actors;

import com.example.tibia.GameTiles;
import com.example.tibia.map.Field;
import javafx.scene.canvas.GraphicsContext;

public class Player extends Actor {

// attributes

    private Inventory inventory;
    private String nickName;
    private boolean right = true;
    private boolean attacking = false;
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

    public boolean isAttacking(){ return this.attacking; }


// methods

    public void attack(GraphicsContext context) {
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
        // displays sword flash
        Thread attackCooldown = new Thread(() -> {
            this.attacking = true;
            try {
                Thread.sleep(200); // sword flash duration
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.attacking = false;
        });
        attackCooldown.start();
    }

    @Override
    public void move(int dx, int dy){
        int currentX = field.getX();
        super.move(dx ,dy);
        int newX = field.getX();
        if (currentX < newX){
            this.right = true;
        }
        if (currentX > newX){
            this.right = false;
        }
    }

    @Override
    public String getTileName(){
        return (right) ? name + " right" : name + " left";
    }

}
