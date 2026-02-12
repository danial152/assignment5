package database;

import database.IDB;
import java.sql.*;

public class DatabaseConnection implements IDB {

    private Connection connection;

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/SimpleDB";
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(connectionUrl, "postgres", "11dan11");

            return connection;
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());

            return null;
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Connection close error: " + ex.getMessage());
            }
        }
    }
}


