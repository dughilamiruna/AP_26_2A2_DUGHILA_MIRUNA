package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public void generate(){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(ReportGenerator.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        try {
            Template template = cfg.getTemplate("report.ftl");
            Map<String, Object> templateData = new HashMap<>();

            List<Map<String, Object>> moviesList = new ArrayList<>();

            try (Connection connection = Database.getConnection();
                 Statement stmt = connection.createStatement();
                 ResultSet result = stmt.executeQuery("SELECT title, release_date, duration, score, genre_name, actors FROM v_movie_report")) {

                while (result.next()) {
                    Map<String, Object> movie = new HashMap<>();
                    movie.put("title", result.getString("title"));
                    movie.put("releaseDate", result.getDate("release_date"));
                    movie.put("duration", result.getInt("duration"));
                    movie.put("score", result.getDouble("score"));
                    movie.put("genre", result.getString("genre_name"));
                    movie.put("actors", result.getString("actors"));
                    moviesList.add(movie);
                }
            }
            templateData.put("movies", moviesList);

            File outputFile = new File("movies_report.html");
            FileWriter out = new FileWriter(outputFile);

            template.process(templateData, out);
            out.close();

            Desktop.getDesktop().browse(outputFile.toURI());

        } catch (Exception e) {
            e.printStackTrace();        }
    }
}
