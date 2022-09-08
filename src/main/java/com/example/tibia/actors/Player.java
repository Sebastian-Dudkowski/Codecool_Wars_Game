package com.example.tibia.actors;

import com.example.tibia.items.Lightsaber;
import com.example.tibia.map.Field;
import java.util.Random;

import static com.example.tibia.Main.player;
import static com.example.tibia.sounds.SoundsPlayer.*;

public class Player extends Actor {

// attributes

    private Inventory inventory;
    private String nickName;
    private boolean healing;
    private boolean hasLightsaber = false;
    private int maxHealth = 100;
    private int maxMana = 100;


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
        this.armor = 2;
        this.inventory = new Inventory();
    }

// getters & setters

    public Inventory getInventory(){ return this.inventory; }
    @Override
    public String getName(){ return this.nickName; }
    public void setFacingRight(Boolean right) { this.facingRight = right; }
    public boolean isHealing() {
        return healing;
    }
    public int getMaxHealth(){ return this.maxHealth; }
    public void setMaxHealth(int health){ this.maxHealth = health; }
    public boolean hasLightsaber(){ return this.hasLightsaber; }
    public int getMaxMana() {
        return maxMana;
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
// methods

    public void attack() {
        for (int x=-1; x<=1; x++){
            for (int y=-1; y<=1; y++){
                if (!(x==0 && y==0)){
                    Actor target = this.getField().getNeighbor(x, y).getActor();
                    if (target != null){
                        String sound;
                        if (hasLightsaber){
                            sound = (new Random().nextBoolean()) ? LIGHTSABER_HIT_1 : LIGHTSABER_HIT_2;
                        } else {
                            sound = (new Random().nextBoolean()) ? PUNCH_1 : PUNCH_2;
                        }
                        playSound(sound, (float) 0.3);
                        attack(target);
                    }
                }
            }
        }
        String sound;
        if (hasLightsaber){
            sound = (new Random().nextBoolean())
                ? LIGHTSABER_SWING_1 : (new Random().nextBoolean())
                ? LIGHTSABER_SWING_2 : LIGHTSABER_SWING_3;
        } else {
            sound = (new Random().nextBoolean())
                ? FIST_1 : (new Random().nextBoolean())
                ? FIST_2 : FIST_3;
        }
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
            if (field.getItem() instanceof Lightsaber){ hasLightsaber = true; }
            player.setStrength(getStrength()+ this.field.getItem().getStrength());
            player.setArmor(getArmor()+ this.field.getItem().getArmor());
            this.inventory.addItem(field.getItem());
            field.setItem(null);
            playSound(PICK_UP, (float) 0.5);
        }
    }

    @Override
    public void move(int dx, int dy){
        super.move(dx, dy);
        this.walkingSound = (new Random().nextBoolean()) ? PLAYER_WALK_1 : PLAYER_WALK_2;
        playSound(walkingSound, (float) 0.1);
    }

    public void heal(){
        playSound(HEALING, (float) 0.3);
        Thread heal = new Thread(() -> {
            this.healing = true;
            for (int i=0; i< 40; i++){
                if (health < maxHealth){
                    this.health++;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.healing = false;
        });
        heal.start();
    }
    @Override
    public String getTileName() {
        if (hasLightsaber){
            return (facingRight) ? name + " right lightsaber" : name + " left lightsaber";
        }
        return (facingRight) ? name + " right" : name + " left";
    }

}
