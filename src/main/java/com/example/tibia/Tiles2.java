package com.example.tibia;

import com.example.tibia.actors.ActorName;
import com.example.tibia.items.ItemName;
import com.example.tibia.map.FieldType;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles2 {
    public static int TILE_WIDTH = 64;
    private static Image tileset = new Image("tiles2.png", 8192, 2048, true, false);

    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH ) ;
            y = j * (TILE_WIDTH );
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put(FieldType.EMPTY.getTileName(), new Tile(55, 22));
        tileMap.put(FieldType.WALL.getTileName(), new Tile(52, 22));
        tileMap.put(FieldType.FLOOR.getTileName(), new Tile(85, 23));
        tileMap.put("floor", new Tile(86, 23));
        tileMap.put(FieldType.DOOR.getTileName(), new Tile(50, 22));
        tileMap.put(FieldType.BENCH.getTileName(), new Tile(8, 5));
        tileMap.put(ActorName.PLAYER.getName(), new Tile(65, 20));
        tileMap.put("player2", new Tile(65, 19));
        tileMap.put(ActorName.SKELETON.getName(), new Tile(30, 20));
        tileMap.put(ActorName.SKELETON.getName() + " alert", new Tile(31, 20));
        tileMap.put(ActorName.DROID.getName(), new Tile(26, 3));
        tileMap.put(ActorName.SITH.getName(), new Tile(24, 3));
        tileMap.put("armor", new Tile(3, 23));
        tileMap.put(ItemName.CARD.getName(), new Tile(22, 4));
        tileMap.put(ItemName.SWORD.getName(), new Tile(8, 10));
        tileMap.put(ItemName.SHOES.getName(), new Tile(8, 22));
        tileMap.put(ItemName.SHIELD.getName(), new Tile(7, 26));
        tileMap.put(ItemName.HELMET.getName(), new Tile(14, 0));
        tileMap.put(ItemName.MANA_POTION.getName(), new Tile(16, 25));
        tileMap.put(ItemName.HEALTH_POTION.getName(), new Tile(17, 25));

    }

    public static void drawTile(GraphicsContext context, String name, int x, int y) {
        Tile tile = tileMap.get(name);
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }



}

