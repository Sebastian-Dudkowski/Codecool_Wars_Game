package com.example.tibia.items;

import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;

import java.util.Random;

import static com.example.tibia.music.MusicPlayer.*;

public class Key extends Item{

    public Key(String name, Field field) {
        super(name, field);
    }

    public void use(Player player){
        for (int x=-1; x<=1; x+=2){
            for (int y=-1; y<=1; y+=2){
                if (player.getField().getNeighbor(x, y).getType().equals(FieldType.DOOR)){
                    player.getField().getNeighbor(x, y).setType(FieldType.FLOOR);
                    // TODO: Play open door sound
                    player.getInventory().removeItem(this);
                    return;
                }
            }
        }
        // TODO: Play invalid use sound
    }
}
