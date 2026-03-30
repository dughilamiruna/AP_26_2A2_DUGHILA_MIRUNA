package org.example;

import org.example.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieActorDAO {


    public void addActorToMovie(int movieId, int actorId) throws SQLException {
        String sql = "INSERT INTO movies_actors (movie_id, actor_id) VALUES (?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();

        }
    }
}