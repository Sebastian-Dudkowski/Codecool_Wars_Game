package com.example.tibia.DAO;

import com.example.tibia.actors.Player;
import com.example.tibia.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name,health,mana,attackPower,x,y,exp) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHealth());
            statement.setInt(3, player.getMana());
            statement.setInt(4, player.getAttackPower());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setInt(7, player.getExperience());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET player_name=?,health=?,mana=?,attackPower=?,x=?,y=?,exp=? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(8, player.getId());
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHealth());
            statement.setInt(3, player.getMana());
            statement.setInt(4, player.getAttackPower());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setInt(7, player.getExperience());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all players", e);
        }

    }

    @Override
    public PlayerModel get(String player_name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,player_name,health,mana,attackPower,x,y,exp FROM player WHERE player_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player_name);

            ResultSet rs = statement.executeQuery();
            rs.next();
            PlayerModel player = new PlayerModel(rs.getString(2), rs.getInt(5), rs.getInt(6));
            player.setHealth(rs.getInt(3));
            player.setMana(rs.getInt(4));
            player.setExperience(rs.getInt(7));
            return player;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading player " + player_name, e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,player_name,health,mana,attackPower,x,y,exp FROM player";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<PlayerModel> result = new ArrayList<>();
            while (rs.next()) {
                PlayerModel player = new PlayerModel(rs.getString(2), rs.getInt(5), rs.getInt(6));
                player.setHealth(rs.getInt(3));
                player.setMana(rs.getInt(4));
                player.setExperience(rs.getInt(7));
                result.add(player);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<String> getSaveNames() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_name FROM player";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<String> result = new ArrayList<>();
            while (rs.next()) {
                PlayerModel player = new PlayerModel(rs.getString(1));
                result.add(player.getPlayerName());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}
