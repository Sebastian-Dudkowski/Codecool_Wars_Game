package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    private Cell cell;
    GameMap gameMap;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell;
        try {
            nextCell = cell.getNeighbor(dx, dy);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (nextCell.getType().equals(CellType.WALL) || nextCell.getActor() != null) {
            return;
        }
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }
    private boolean moveValidation(int dx, int dy) {
        return !(dx < 0 || dy < 0 || dx > cell.getGameMap().getWidth() -1 || dy > cell.getGameMap().getHeight() -1);
    }
    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
