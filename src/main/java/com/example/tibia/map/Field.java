package com.example.tibia.map;

import com.example.tibia.Drawable;
import com.example.tibia.actors.Actor;
import com.example.tibia.items.Item;

import java.util.Random;

public class Field implements Drawable {

// attributes
    private int X;
    private int Y;
    private FieldType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private int random = 0;

// constructors

    public Field(GameMap gameMap,FieldType type, int x, int y) {
        this.gameMap = gameMap;
        this.type = type;
        this.X = x;
        this.Y = y;
    }

    /**
     * Constructor for fields with randomized tiles
     */
    public Field(GameMap gameMap,FieldType type, int x, int y, int random) {
        this.gameMap = gameMap;
        this.type = type;
        this.X = x;
        this.Y = y;
        this.random = random;
    }

// getters & setters
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public FieldType getType() {
        return type;
    }
    public void setType(FieldType type) {
        this.type = type;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public Actor getActor() {
        return this.actor;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public GameMap getGameMap() {
        return gameMap;
    }
    public void setRandom(int random) {
        this.random = random;
    }

    // methods
    @Override
    public String getTileName(){
        if (actor != null){
            return actor.getTileName();
        }
        if (item != null) {
            return item.getTileName();
        }
        if ( random != 0) {
            return  type.getTileName() + random;
        }
        return type.getTileName();
    }

    public Field getNeighbor(int dx, int dy) {
        return gameMap.getField(X + dx, Y + dy);
    }

}
