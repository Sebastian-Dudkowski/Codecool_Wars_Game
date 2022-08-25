package com.example.tibia.map;

import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;

import java.util.List;

public class GameMap {
// attributes

    private int height;
    private int width;
    private Field[][] fields;
    private Player player;

    private List<Actor> npcs;

// constructors

    public GameMap(int height, int width){
        this.height = height;
        this.width = width;
        this.fields = new Field[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                fields[x][y] = new Field(this, FieldType.EMPTY, x, y);
            }
        }
    }

// getters & setters

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Field getField(int x, int y){
        if (x >= 0 && x < height && y >= 0 && y < width) {
            return fields[x][y];
        }
        return new Field(this, FieldType.EMPTY, x, y);
    }

// methods

    public void addNpc(Actor npc){
        npcs.add(npc);
    }
}
