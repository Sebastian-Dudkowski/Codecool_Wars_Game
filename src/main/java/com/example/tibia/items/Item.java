package com.example.tibia.items;

public abstract class Item {
// attributes



    private String name;

// constructors

    public Item(){}
    public Item(String name){
        this.name = name;
    }

// getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
// methods

    public void use(){

    }

}
