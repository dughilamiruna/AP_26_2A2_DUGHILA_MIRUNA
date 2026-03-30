package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
        config.setUsername("student");
        config.setPassword("STUDENT");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        dataSource = new HikariDataSource(config);
    }
    private Database() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
