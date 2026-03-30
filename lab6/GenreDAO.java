package org.example;

import java.sql.*;

public class GenreDAO {
    public void create(Genre genre) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(
                "insert into genres (id, name) values (genres_seq.NEXTVAL, ?)")) {
            stmt.setString(1, genre.getName());
            stmt.executeUpdate();
        }
    }

    public Genre findByName(String name) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id, name FROM genres WHERE name = ?")) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Genre(rs.getInt("id"), rs.getString("name"));
                }
                return null;
            }
        }
    }

    public Genre findById(int id) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id, name FROM genres WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Genre(rs.getInt("id"), rs.getString("name"));
                }
                return null;
            }
        }
    }
}