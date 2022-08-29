package com.example.tibia.EQ;

import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;

public class EQMap {
     private int height;
    private int width;
    private Cell[][] cells;
    private Player player;


    public EQMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                cells[x][y] = new Cell(this, CellName.EMPTY, x, y);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

     public Cell getCell(int x, int y){
        if (x >= 0 && x < height && y >= 0 && y < width) {
            return cells[x][y];
        }
        return new Cell(this, CellName.EMPTY, x, y);
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}

