package com.example.tibia.DAO;

import com.example.tibia.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state);
    void update(GameState state);
    GameState get(int id);
    List<GameState> getAll();
}
