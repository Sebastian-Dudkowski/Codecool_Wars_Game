package com.example.tibia.items;

public enum ItemName {
    SWORD("sword"),
    ARMOR("armor"),
    HELMET("helmet"),
    SHOES("shoes"),
    SHIELD("shield"),
    MANA_POTION("manaPotion"),
    HEALTH_POTION("healthPotion"),
    CARD("card"),
    KEY("key"),
    SWORD1("sword1"),
    BLASTER("blaster");

    private final String name;

    ItemName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
