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
        public final int dx ,dy; // image offset relative to tile's position
        Tile(int i, int j) {
            x = i * (TILE_WIDTH ) ;
            y = j * (TILE_WIDTH );
            w = TILE_WIDTH;
            h = TILE_WIDTH;
            dx = 0;
            dy = 0;
        }

        /**
         * Constructor for images with size different from default
         */
        Tile(int i, int j, int width, int height) {
            x = i * (TILE_WIDTH ) ;
            y = j * (TILE_WIDTH );
            w = width;
            h = height;
            dx = 0;
            dy = 0;
        }

        /**
         * constructor for images offset relative to tile's position
         */
        Tile(int i, int j, int width, int height, int dx, int dy) {
            x = i * (TILE_WIDTH ) ;
            y = j * (TILE_WIDTH );
            w = width;
            h = height;
            this.dx = dx;
            this.dy = dy;
        }

    }

    static {
        // Background
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
        //Next lvl
        tileMap.put(FieldType.NEXT.getTileName(), new Tile(3, 24));
        // Decorations
        tileMap.put(FieldType.ENGINE.getTileName() + "1", new Tile(122, 4, 192, 254, -2, -3));
        tileMap.put(FieldType.ENGINE.getTileName() + "2", new Tile(125, 4, 192, 254, -2, -3));
        tileMap.put(FieldType.BOX_BIG.getTileName(), new Tile(94, 25, 192, 128, -2, -1));
        tileMap.put(FieldType.BOX_SMALL.getTileName(), new Tile(89, 27));
        // Player
        tileMap.put(ActorName.PLAYER.getName() + " left", new Tile(66, 22, 64, 128, 0, -1));
        tileMap.put(ActorName.PLAYER.getName() + " right", new Tile(65, 22, 64, 128, 0, -1));
        // NPCs
        tileMap.put(ActorName.DROID.getName() + " left", new Tile(70, 20));
        tileMap.put(ActorName.DROID.getName() + " right", new Tile(69, 20));
        tileMap.put(ActorName.DROID.getName() + " left alert", new Tile(71, 20));
        tileMap.put(ActorName.DROID.getName() + " right alert", new Tile(72, 20));

        tileMap.put(ActorName.GUARD.getName() + " left", new Tile(81, 20));
        tileMap.put(ActorName.GUARD.getName() + " right", new Tile(82, 20));
        tileMap.put(ActorName.GUARD.getName() + " left alert", new Tile(84, 20));
        tileMap.put(ActorName.GUARD.getName() + " right alert", new Tile(83, 20));

        tileMap.put(ActorName.TROOPER.getName() + " left", new Tile(77, 20));
        tileMap.put(ActorName.TROOPER.getName() + " right", new Tile(78, 20));
        tileMap.put(ActorName.TROOPER.getName() + " left alert", new Tile(79, 20));
        tileMap.put(ActorName.TROOPER.getName() + " right alert", new Tile(80, 20));

        tileMap.put(ActorName.VADER.getName() + " left", new Tile(70, 23));
        tileMap.put(ActorName.VADER.getName() + " right", new Tile(69, 23));
        tileMap.put(ActorName.VADER.getName() + " left alert", new Tile(72, 23));
        tileMap.put(ActorName.VADER.getName() + " right alert", new Tile(71, 23));
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
        tileMap.put("sword flash right", new Tile(97, 25, 192, 192, -2, -2));
        tileMap.put("sword flash left", new Tile(100, 25, 192, 192, -2, -2));
        tileMap.put("Vader sword flash right", new Tile(102, 25, 192, 192, -2, -2));
        tileMap.put("Vader sword flash left", new Tile(105, 25, 192, 192, -2, -2));

    }
    public static void drawTile(GraphicsContext context, String name, int x, int y) {
        Tile tile = tileMap.get(name);
        if (tile == null){
            throw new IllegalArgumentException("Tile does not exist: " + name);
        }
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                (x + tile.dx) * TILE_WIDTH, (y + tile.dy) * TILE_WIDTH, tile.w, tile.h);
    }

}

