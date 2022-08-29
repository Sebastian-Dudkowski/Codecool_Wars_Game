package com.example.tibia.EQ;

public enum CellName {
    EMPTY("empty"),
    SWORD("sword"),
    ARMOR("armor"),
    HELMET("helmet"),
    SHOES("shoes"),
    SHIELD("shield"),
    MANA_POTION("manaPotion"),
    HEALTH_POTION("healthPotion"),
    CARD("card");



    private final String tileName;

    CellName(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
