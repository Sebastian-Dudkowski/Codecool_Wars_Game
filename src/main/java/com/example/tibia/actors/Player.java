package com.example.tibia.actors;

import com.example.tibia.map.Field;

import java.util.Random;

import static com.example.tibia.sounds.SoundsPlayer.*;

public class Player extends Actor {

// attributes

    private Inventory inventory;
    private String nickName;

// constructors

    public Player(String nickName, Field field, int health, int mana, int attackPower) {
        super(ActorName.PLAYER.getName(), field);
        this.nickName = nickName;
        this.viewRange = 15;
        this.health = health;
        this.mana = mana;
        this.strength = attackPower;
        this.playerLvl = 1;
        this.exp = 0;
        this.inventory = new Inventory();
    }

// getters & setters

    public Inventory getInventory(){ return this.inventory; }
    @Override
    public String getName(){ return this.nickName; }

// methods

    public void attack() {
        for (int x=-1; x<=1; x++){
            for (int y=-1; y<=1; y++){
                if (!(x==0 && y==0)){
                    Actor target = this.getField().getNeighbor(x, y).getActor();
                    if (target != null){
                        String sound = (new Random().nextBoolean()) ? LIGHTSABER_HIT_1 : LIGHTSABER_HIT_2;
                        playSound(sound, (float) 0.3);
                        attack(target);
                    }
                }
            }
        }
        String sound = (new Random().nextBoolean())
                ? LIGHTSABER_SWING_1 : (new Random().nextBoolean())
                ? LIGHTSABER_SWING_2 : LIGHTSABER_SWING_3;
        playSound(sound, (float) 0.3);
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

    public void pickUpItem(Field field){
        if (field.getItem() != null){
            this.inventory.addItem(field.getItem());
            field.setItem(null);
            playSound(PICK_UP, (float) 0.5);
        }
    }

    @Override
    public void move(int dx, int dy){
        super.move(dx, dy);
        this.walkingSound = PLAYER_WALK_1;
        playSound(walkingSound, (float) 0.6);
    }

}
