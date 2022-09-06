package com.example.tibia.actors;

import com.example.tibia.Drawable;
import com.example.tibia.items.Item;
import com.example.tibia.map.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory{

// attributes
    private List<Item> items;

// constructors

    public Inventory() {
        items = new ArrayList<>();
    }

// getters & setters

    public List<Item> getItems() {
        return items;
    }

// methods
    public void addItem(Item item) {
        items.add(item);
        item.getField().setItem(null);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    @Override
    public String toString() {
        return items.toString();
    }

}