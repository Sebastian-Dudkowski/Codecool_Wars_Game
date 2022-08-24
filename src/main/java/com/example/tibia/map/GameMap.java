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
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                fields[x][y] = new Field(FieldType.EMPTY);
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
    public Field getField(int x, int y){
        if (x >= width || y >= height){
            return new Field(FieldType.EMPTY);
        }
        return fields[x][y];
    }

// methods

    private void createFields(){

    }
}
