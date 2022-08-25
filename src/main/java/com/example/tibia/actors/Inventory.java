package com.example.tibia.actors;

import com.example.tibia.items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    // attributes
    private List<Item> items;
    private List<String> itemNames;
// constructors

    public Inventory() {
        items = new ArrayList<>();
    }

// getters & setters

    // methods
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}