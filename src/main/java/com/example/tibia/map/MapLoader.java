package com.example.tibia.map;

import com.example.tibia.actors.*;
import com.example.tibia.items.Helmet;
import com.example.tibia.items.Key;
import com.example.tibia.items.Sword;

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
                        case ' ':
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
                        case '.':
                            field.setType(FieldType.FLOOR);
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
                        case '$':
                            field.setItem(new Sword(field));
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }


}
