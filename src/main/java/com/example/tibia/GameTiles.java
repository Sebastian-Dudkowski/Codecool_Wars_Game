package com.example.tibia;

import com.example.tibia.actors.ActorName;
import com.example.tibia.items.ItemName;
import com.example.tibia.map.FieldType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTiles {
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
        Tile(int i, int j, int width, int height) {
            x = i * (TILE_WIDTH ) ;
            y = j * (TILE_WIDTH );
            w = width;
            h = height;
        }
    }

    static {
        tileMap.put(FieldType.EMPTY.getTileName(), new Tile(54, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "1", new Tile(54, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "2", new Tile(55, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "3", new Tile(56, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "4", new Tile(57, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "5", new Tile(58, 22));
        tileMap.put(FieldType.EMPTY.getTileName() + "6", new Tile(59, 22));
        // Walls
        tileMap.put(FieldType.WALL.getTileName() + "1", new Tile(91, 25));
        tileMap.put(FieldType.WALL.getTileName() + "2", new Tile(92, 25));
        tileMap.put(FieldType.WALL.getTileName() + "3", new Tile(91, 26));
        tileMap.put(FieldType.WALL.getTileName() + "4", new Tile(91, 27));
        tileMap.put(FieldType.WALL.getTileName() + "5", new Tile(91, 25));
        tileMap.put(FieldType.WALL.getTileName() + "6", new Tile(91, 25));
        tileMap.put(FieldType.WALL_VERTICAL.getTileName() + "1", new Tile(90, 26));
        tileMap.put(FieldType.WALL_VERTICAL.getTileName() + "2", new Tile(90, 27));
        tileMap.put(FieldType.WALL_VERTICAL.getTileName() + "3", new Tile(90, 27));
        tileMap.put(FieldType.WALL_VERTICAL.getTileName() + "4", new Tile(90, 27));
        tileMap.put(FieldType.WALL_CORNER.getTileName(), new Tile(90, 25));
        // Floors
        tileMap.put(FieldType.FLOOR.getTileName(), new Tile(93, 27));
        tileMap.put(FieldType.FLOOR.getTileName() + "1", new Tile(93, 25));
        tileMap.put(FieldType.FLOOR.getTileName() + "2", new Tile(93, 26));
        tileMap.put(FieldType.FLOOR.getTileName() + "3", new Tile(93, 27));
        // Doors
        tileMap.put(FieldType.DOOR.getTileName(), new Tile(92, 27));
        tileMap.put(FieldType.DOOR_CLOSED.getTileName(), new Tile(92, 26));
        // Decorations
        tileMap.put(FieldType.BENCH.getTileName(), new Tile(8, 5));
        // Player
        tileMap.put(ActorName.PLAYER.getName() + " left", new Tile(66, 23));
        tileMap.put(ActorName.PLAYER.getName() + " right", new Tile(65, 23));
        tileMap.put(ActorName.PLAYER.getName() + " left 2", new Tile(66, 22));
        tileMap.put(ActorName.PLAYER.getName() + " right 2", new Tile(65, 22));
        // NPC
        tileMap.put(ActorName.DROID.getName() + " left", new Tile(70, 20));
        tileMap.put(ActorName.DROID.getName() + " right", new Tile(69, 20));
        tileMap.put(ActorName.DROID.getName() + " left alert", new Tile(71, 20));
        tileMap.put(ActorName.DROID.getName() + " right alert", new Tile(72, 20));
        tileMap.put(ActorName.SITH.getName(), new Tile(24, 3));
        // items
        tileMap.put(ItemName.ARMOR.getName(), new Tile(3, 23));
        tileMap.put(ItemName.CARD.getName(), new Tile(22, 4));
        tileMap.put(ItemName.SWORD.getName(), new Tile(8, 10));
        tileMap.put(ItemName.SHOES.getName(), new Tile(8, 22));
        tileMap.put(ItemName.SHIELD.getName(), new Tile(7, 26));
        tileMap.put(ItemName.HELMET.getName(), new Tile(14, 0));
        tileMap.put(ItemName.MANA_POTION.getName(), new Tile(16, 25));
        tileMap.put(ItemName.HEALTH_POTION.getName(), new Tile(17, 25));
        tileMap.put(ItemName.KEY.getName(), new Tile(94, 27));
        // effects
        tileMap.put("sword flash right", new Tile(97, 25, 192, 192));
        tileMap.put("sword flash left", new Tile(100, 25, 192, 192));

    }
    public static void drawTile(GraphicsContext context, String name, int x, int y) {
        Tile tile = tileMap.get(name);
        if (tile == null){
            System.out.println(name);
        }
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, tile.w, tile.h);
    }



}

