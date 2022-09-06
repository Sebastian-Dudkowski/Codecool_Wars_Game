package com.example.tibia.items;

import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;

import java.util.Random;

import static com.example.tibia.music.MusicPlayer.*;

public class Key extends Item{

    public Key(Field field) {
        super(ItemName.KEY.getName(), field);
    }

    public void useKey(Player player){
        for (int x=-1; x<=1; x++){
            for (int y=-1; y<=1; y++){
                // ensures checking only up, down, left and right directions
                if (Math.abs(x) + Math.abs(y) == 1){
                    if (player.getField().getNeighbor(x, y).getType().equals(FieldType.DOOR)){
                        player.getField().getNeighbor(x, y).setType(FieldType.FLOOR);
                        playSound(doorOpen, (float) 0.6);
                        player.getInventory().removeItem(this);
                        return;
                    }
                }
            }
        }
        // TODO: Play invalid use sound
    }
}
