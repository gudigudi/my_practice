// Connect.java: connect to the MySQL server


import java.lang.Exception;
import java.lang.System;
import java.sql.*;

public class Connect {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/cookbook";
        String userName = "user";
        String password = "password";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");
        } catch(Exception e){
            System.err.println("Cannot connect to server");
            System.exit(1);
            e.printStackTrace();
        }
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Disconnected");
            } catch (Exception e) {
                // ignore close errors
            }
        }
    }
}
