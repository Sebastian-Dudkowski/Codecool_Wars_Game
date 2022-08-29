package com.example.tibia.EQ;

import com.example.tibia.Drawable;
import com.example.tibia.actors.Actor;
import com.example.tibia.items.Item;
import com.example.tibia.map.FieldType;
import com.example.tibia.map.GameMap;

public class Cell implements Drawable {

    private EQMap eq;
    private int X;
    private int Y;
    private CellName type;
    private Actor actor;
    private Item item;


    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public CellName getType() {
        return type;
    }

    public void setType(CellName type) {
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

    public EQMap getEq() {
        return eq;
    }

    @Override
    public String getTileName(){
        if (actor != null){
            return (actor.isAlert()) ? actor.getTileName() + " alert" : actor.getTileName();
        } else if ( item != null) {
            return item.getTileName();
        }

        return type.getTileName();
    }

    public Cell(EQMap eq, CellName type, int x, int y) {
        this.eq = eq;
        this.type = type;
        this.X = x;
        this.Y = y;
    }
}
