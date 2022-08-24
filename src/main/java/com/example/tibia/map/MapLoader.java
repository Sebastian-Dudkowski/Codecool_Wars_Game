package com.example.tibia.map;

import com.example.tibia.actors.ActorName;
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

        GameMap map = new GameMap(height, width);
        map.setPlayer(player);
        for (int x = 0; x < height; x++) {
            String line = scanner.nextLine();
            for (int y = 0; y < width; y++) {
                if (y < line.length()) {
                    Field field = map.getField(x, y);
                    switch (line.charAt(y)) {
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
                            field.setActor(new Skeleton(field));
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
