package com.example.tibia.map;

public class GameMap {
// attributes

    private int height;
    private int width;
    private Field[][] fields;

// constructors

    public GameMap(int height, int width){
        this.height = height;
        this.width = width;
        this.fields = new Field[height][width];
    }

// getters & setters

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Field getField(int x, int y){
        return fields[x][y];
    }

// methods


}
