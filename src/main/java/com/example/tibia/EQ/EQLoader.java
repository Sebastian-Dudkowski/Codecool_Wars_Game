package com.example.tibia.EQ;

import com.example.tibia.actors.Player;

import java.io.InputStream;
import java.util.Scanner;

public class EQLoader {
    public static EQMap loadEQ(Player player) {
        InputStream is = EQLoader.class.getResourceAsStream("/Eq.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        EQMap eq = new EQMap(width, height);
        eq.setPlayer(player);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = eq.getCell(x, y);
                    switch (line.charAt(x)) {
                        case '0':
                            cell.setType(CellName.EMPTY);
                            break;
                        case 'H':
                            cell.setType(CellName.HELMET);
                            break;
                        case 'C':
                            cell.setType(CellName.CARD);
                            break;
                        case 'S':
                            cell.setType(CellName.SWORD);
                            break;
                        case 'A':
                            cell.setType(CellName.ARMOR);
                            break;
                        case 'T':
                            cell.setType(CellName.BLASTER);
                            break;
                        case 'M':
                            cell.setType(CellName.MANA_POTION);
                            break;
                        case 'B':
                            cell.setType(CellName.SHOES);
                            break;
                        case 'Z':
                            cell.setType(CellName.HEALTH_POTION);
                            break;

                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return eq;
    }
}
