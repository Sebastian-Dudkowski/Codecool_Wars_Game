package com.example.tibia.DAO;

import com.example.tibia.actors.Player;
import com.example.tibia.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);
    void update(PlayerModel player);
    PlayerModel get(String player_name);
    List<PlayerModel> getAll();
    List<String>getSaveNames();
}
