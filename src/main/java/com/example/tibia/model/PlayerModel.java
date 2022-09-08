package com.example.tibia.model;

import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int health;
    private int mana;
    private int attackPower;
    private int x;
    private int y;
    private int experience;


    private String savedGameName;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player, String savedGameName) {
        this.savedGameName = savedGameName;
        this.playerName = player.getName();
        this.health = player.getHealth();
        this.mana = player.getMana();
        this.attackPower = player.getStrength();
        this.x = player.getX();
        this.y = player.getY();
        this.experience = player.getExp();
    }

    public PlayerModel(String savedGameName) {
        this.playerName = savedGameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSavedGameName() {
        return savedGameName;
    }

    public void setSavedGameName(String savedGameName) {
        this.savedGameName = savedGameName;
    }
}
