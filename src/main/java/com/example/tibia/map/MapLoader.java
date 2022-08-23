package com.example.tibia.map;

import com.example.tibia.actors.Player;
import com.example.tibia.actors.Skeleton;

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
                        case '.':
                            field.setType(FieldType.FLOOR);
                            break;
                        case 's':
                            field.setType(FieldType.FLOOR);
                            field.setActor(new Skeleton(field));
                            break;
                        case '@':
                            field.setType(FieldType.FLOOR);
                            field.setActor(player);
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
