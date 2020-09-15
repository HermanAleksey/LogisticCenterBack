package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URL;
import java.sql.*;

@SpringBootApplication
public class RestServiceApplication {

    private static final String user = "root";
    private static final String password = "Mysql1119067";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/QuizzDatabase?serverTimezone=UTC",
                user, password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM TESTTABLE;");
        resultSet.next();

        String s = resultSet.getString("QWERTY"); //resultSet.getString(2);
        System.out.println(s);
        SpringApplication.run(RestServiceApplication.class, args);


        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException ignore) {
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException ignore) {
            }

    }
}
