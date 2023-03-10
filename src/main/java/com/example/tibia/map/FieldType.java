package com.example.tibia.map;

import java.util.Random;

public enum FieldType {
    EMPTY("empty"),
    FLOOR("floor"),
    FLOOR_UNWALKABLE("floor"),
    WALL("wall"),
    WALL_VERTICAL("wall vertical"),
    WALL_CORNER("wall corner"),
    DOOR("door"),
    DOOR_CLOSED("door closed"),
    BENCH("bench"),
    ITEM("item"),
    NEXT("next"),
    ENGINE("engine"),
    BOX_SMALL("box"),
    BOX_BIG("box big");

    private final String tileName;

    FieldType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
