package com.dennis.databases;

import com.dennis.Init;
import static com.dennis.utils.Logging.LOGGER;
import java.sql.*;
import java.util.logging.Level;

/**
 *
 * @author DennisMutethia
 */
public class MySQL {

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + Init.APPLICATION_PROPERTIES.getProperty("db_host")+":"
                            + Init.APPLICATION_PROPERTIES.getProperty("db_port")+"/" 
                            + Init.APPLICATION_PROPERTIES.getProperty("db_name"),
                    Init.APPLICATION_PROPERTIES.getProperty("db_user"),
                    Init.APPLICATION_PROPERTIES.getProperty("db_user_password")
            )) {
                Statement stmt = con.createStatement();
                String query = "SELECT * FROM `books`";
                System.out.println("Executing query: " + query);
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    System.out.println(
                            "id: " + id
                            + "\ntitle: " + title
                            + "\nauthor: " + author
                    );
                    System.out.println("------------------------------");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "{0}", e);
        } 
    }

}
