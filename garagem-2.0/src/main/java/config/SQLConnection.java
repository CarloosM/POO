package config;

import java.sql.*;

public class SQLConnection {

    // add SET GLOBAL time_zone = '-3:00';

    private static Connection conn = null;

    public static Connection getConnection() {

        if(conn == null) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/garagem_2?useTimezone=true&serverTimezone=UTC","root","121300");
            } catch (SQLException e) {
                // handle any errors
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

            return conn;

        } else {
            return conn;
        }

    }

}
