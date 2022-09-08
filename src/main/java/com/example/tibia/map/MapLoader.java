package com.example.tibia.map;

import com.example.tibia.actors.*;
import com.example.tibia.items.Helmet;
import com.example.tibia.items.Key;
import com.example.tibia.items.Lightsaber;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(Player player, int level) {
        Scanner scanner;
        if (level == 1) {
            InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
            scanner = new Scanner(is);
        } else {
            InputStream is = MapLoader.class.getResourceAsStream("/map2.txt");
            scanner = new Scanner(is);
        }

        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height);
        map.setPlayer(player);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Field field = map.getField(x, y);
                    switch (line.charAt(x)) {
                        case '.':
                        case 'x':
                            field.setType(FieldType.EMPTY);
                            field.setRandom(new Random().nextInt(1, 7));
                            break;
                        case '#':
                            field.setType(FieldType.WALL);
                            field.setRandom(new Random().nextInt(1, 7));
                            break;
                        case '^':
                            field.setType(FieldType.WALL_VERTICAL);
                            field.setRandom(new Random().nextInt(1, 5));
                            break;
                        case 'C':
                            field.setType(FieldType.WALL_CORNER);
                            break;
                        case ' ':
                            field.setType(FieldType.FLOOR);
                            field.setRandom(new Random().nextInt(1, 4));
                            break;
                        case 'u':
                            field.setType(FieldType.FLOOR_UNWALKABLE);
                            field.setRandom(new Random().nextInt(1, 4));
                            break;
                        case 'D':
                            field.setType(FieldType.DOOR);
                            break;
                        case 'X':
                            field.setType(FieldType.DOOR_CLOSED);
                            break;
                        case 'L':
                            field.setType(FieldType.BENCH);
                            break;
                        case 'd':
                            Actor droid = new Droid(field);
                            field.setActor(droid);
                            map.addNpc(droid);
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'g':
                            Actor guard = new Guard(field);
                            field.setActor(guard);
                            map.addNpc(guard);
                            field.setType(FieldType.FLOOR);
                            break;
                        case 't':
                            Actor trooper = new Trooper(field);
                            field.setActor(trooper);
                            map.addNpc(trooper);
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'V':
                            Actor vader = new Vader(field);
                            map.addNpc(vader);
                            field.setType(FieldType.FLOOR);
                            break;
                        case '$':
                            field.setItem(new Lightsaber(field));
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'H':
                            field.setItem(new Helmet(field));
                            field.setType(FieldType.FLOOR);
                            break;
                        case '@':
                            field.setActor(player);
                            player.setField(field);
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'K':
                            field.setItem(new Key(field));
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'P':
                            field.setType(FieldType.NEXT);
                            break;
                        case 'E':
                            field.setType(FieldType.ENGINE);
                            field.setRandom(new Random().nextInt(1, 3));
                            break;
                        case 'b':
                            field.setType(FieldType.BOX_SMALL);
                            break;
                        case 'B':
                            field.setType(FieldType.BOX_BIG);
                            break;
                        case 'R':
                            field.setType(FieldType.R2D2);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
