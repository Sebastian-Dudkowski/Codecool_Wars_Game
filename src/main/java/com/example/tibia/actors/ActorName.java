package com.example.tibia.actors;

public enum ActorName {
    PLAYER("Player"),
    SKELETON("Skeleton"),
    SITH("Sith"),
    DROID("Droid"),
    GUARD("Guard"),
    TROOPER("Trooper");

    private final String name;

    ActorName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
