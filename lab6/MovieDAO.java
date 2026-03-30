package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    public void create(Movie movie) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "INSERT INTO movies (id, title, release_date, duration, score, genre_id) VALUES (movies_seq.NEXTVAL, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, movie.getTitle());
            pstmt.setDate(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getDuration());
            pstmt.setDouble(4, movie.getScore());
            pstmt.setInt(5, movie.getGenreId());
            pstmt.executeUpdate();
        }
    }
    public Integer findByTitle(String title) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id FROM movies WHERE title = ?")) {

            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
                return null;
            }
        }
    }
    public List<Movie> findAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM movies")) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("release_date"),
                        rs.getInt("duration"),
                        rs.getDouble("score"),
                        rs.getInt("genre_id")
                ));
            }
        }
        return movies;
    }
    }
