package com.example.tibia.map;

import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.npcs = new ArrayList<>();
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
        return generateRandomEmptyField(x, y);
    }
    public List<Actor> getNpcs() {
        return npcs;
    }


// methods

    public void addNpc(Actor npc){
        npcs.add(npc);
    }

    public void removeNpc(Actor npc) { npcs.remove(npc); }

    public Field generateRandomEmptyField(int x, int y){
        Field emptyField = new Field(this, FieldType.EMPTY, x, y);
        emptyField.setRandom(new Random().nextInt(1, 7));
        return emptyField;
    }
}
