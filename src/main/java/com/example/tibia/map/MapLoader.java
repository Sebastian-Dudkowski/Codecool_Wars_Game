package com.example.tibia.map;

import com.example.tibia.actors.Actor;
import com.example.tibia.actors.ActorName;
import com.example.tibia.actors.Player;
import com.example.tibia.actors.Skeleton;
import com.example.tibia.items.Helmet;
import com.example.tibia.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(Player player) {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
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
                            break;
                        case '#':
                            field.setType(FieldType.WALL);
                            break;
                        case '^':
                            field.setType(FieldType.WALL_VERTICAL);
                            break;
                        case 'C':
                            field.setType(FieldType.WALL_CORNER);
                            break;
                        case '.':
                            field.setType(FieldType.FLOOR);
                            break;
                        case 'D':
                            field.setType(FieldType.DOOR);
                            break;
                        case 'L':
                            field.setType(FieldType.BENCH);
                            break;
                        case 's':
                            Actor skeleton = new Skeleton(field);
                            field.setActor(skeleton);
                            map.addNpc(skeleton);
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
