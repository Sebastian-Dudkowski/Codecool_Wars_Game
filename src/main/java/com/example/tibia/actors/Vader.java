package com.example.tibia.actors;

import com.example.tibia.map.Field;

import java.util.Random;

import static com.example.tibia.sounds.SoundsPlayer.*;

public class Vader extends Actor{


    private int canvasX;
    private int canvasY;

    public Vader(Field field) {
        super(ActorName.VADER.getName(), field);
        this.viewRange = 7;
        this.health = 666;
        this.strength = 11;
        this.exp = 1000;
        this.walkingSound = PLAYER_WALK_1; // TODO: add Vader sound
    }

    public int getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(int canvasX) {
        this.canvasX = canvasX;
    }

    public int getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(int canvasY) {
        this.canvasY = canvasY;
    }

    @Override
    public void attack(Actor player){
        player.setHealth(player.getHealth() - this.strength);
        if (player.getHealth() <= 0) {
            player.kill();
        }
        String sound = (new Random().nextBoolean())
                ? LIGHTSABER_CLASH_1 : (new Random().nextBoolean())
                ? LIGHTSABER_CLASH_2 : LIGHTSABER_CLASH_3;
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
}
