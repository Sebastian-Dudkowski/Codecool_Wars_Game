package com.example.tibia.actors;

public enum ActorName {
    PLAYER("player"),
    SKELETON("Skeleton"),
    SITH("sith"),
    DROID("droid");

    private final String name;

    ActorName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
