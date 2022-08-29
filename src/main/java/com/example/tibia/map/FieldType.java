package com.example.tibia.map;

public enum FieldType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    WALL2("wall2"),
    DOOR("door"),
    BENCH("bench"),
    ITEM("item");

    private final String tileName;

    FieldType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
