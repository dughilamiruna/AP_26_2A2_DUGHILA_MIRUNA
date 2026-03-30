package org.example;

import java.sql.*;

public class ActorDAO {
    public void create(Actor actor) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO actors (id, name) VALUES (actors_seq.NEXTVAL, ?)")) {
            stmt.setString(1, actor.getName());
            stmt.executeUpdate();
        }
    }

    public Actor findByName(String name) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id, name FROM actors WHERE name = ?")) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(rs.getInt("id"), rs.getString("name"));
                }
                return null;
            }
        }
    }
    public Actor findById(int id) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id, name FROM actors WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(rs.getInt("id"), rs.getString("name"));
                }
                return null;
            }       }
    }
}
