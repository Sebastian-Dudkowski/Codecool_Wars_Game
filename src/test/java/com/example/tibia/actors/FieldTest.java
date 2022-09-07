package com.example.tibia.actors;

import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FieldTest {

    GameMap map = new GameMap(3, 3);

    @Test
    void getNeighbor() {
        Field field = map.getField(1, 1);
        Field neighbor = field.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

//    @Test
//    void cellOnEdgeHasNoNeighbor() {
//        Field field = map.getField(0, 0);
//        assertNull(field.getNeighbor(0, -30));
//
//        field = map.getField(1, 2);
////        assertNull(field.getNeighbor(-1, -1));
//    }


}

