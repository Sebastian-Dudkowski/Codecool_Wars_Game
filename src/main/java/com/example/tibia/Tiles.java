package com.example.tibia;

import com.example.tibia.actors.ActorName;
import com.example.tibia.map.FieldType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 64;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put(FieldType.EMPTY.getTileName(), new Tile(0, 0));
        tileMap.put(FieldType.WALL.getTileName(), new Tile(10, 17));
        tileMap.put(FieldType.FLOOR.getTileName(), new Tile(2, 0));
        tileMap.put(ActorName.PLAYER.getName(), new Tile(27, 0));
        tileMap.put(ActorName.SKELETON.getName(), new Tile(29, 6));
        tileMap.put(ActorName.DROID.getName(), new Tile(26, 3));
        tileMap.put(ActorName.SITH.getName(), new Tile(24, 3));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

}
