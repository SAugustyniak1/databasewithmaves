package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        System.out.println(connection);

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM offices ORDER BY city DESC");

        result.next();

        String city = result.getString("city");
        System.out.println(city);


        while(result.next()){
            city = result.getString("city");
            System.out.println(city);
        }

    }
}
