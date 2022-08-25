package com.example.tibia.actors;

import com.example.tibia.items.Item;
import com.example.tibia.map.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    public List<Item> getItems() {
        return items;
    }

    // attributes
    private List<Item> items;
    private List<String> itemNames;
    private Field field;
// constructors

    public Inventory() {
        items = new ArrayList<>();
    }

// getters & setters

    // methods
    public void addItem(Item item) {
        items.add(item);
//        field.setItem(null);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

}