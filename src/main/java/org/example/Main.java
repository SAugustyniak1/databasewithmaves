package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        //(city, phone, addressLine1, country, postalCode, territory)
        System.out.println("What is your city");
        String city = scanner.nextLine();

        System.out.println("What is your phone");
        String phone = scanner.nextLine();

        System.out.println("What is your adress line");
        String addressLine1 = scanner.nextLine();

        System.out.println("What is your countery");
        String countrey = scanner.nextLine();

        System.out.println("What is your postal code");
        String postalCode = scanner.nextLine();

        System.out.println("What is your territory");
        String territory = scanner.nextLine();




        exampleUpdate(city,phone,addressLine1, countrey, postalCode,territory);

        exampleSelect();



    }

    public static void exampleSelect() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        System.out.println(connection);

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM offices ORDER BY city DESC");

        result.next();

        String city = result.getString("city");
        System.out.println(city);


        while(result.next()){
            long officeCode = result.getLong("officeCOde");
            city = result.getString("city");
            System.out.println(city + " " + officeCode);
        }
        connection.close();


    }

    public static void exampleUpdate(String city, String phone, String adressLine1, String country, String postalCode, String territory) throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        System.out.println(connection);

//        String values = String.format("VALUES (%s,  %s, %s, %s, %s)", city, phone, adressLine1, postalCode, territory);
//
//        System.out.println(values);

        String command = String.format("INSERT INTO offices (city, phone, addressLine1, country, postalCode, territory)\n" +
                "VALUES ('%s',  '%s', '%s', '%s', '%s', '%s')", city, phone, adressLine1, country, postalCode, territory);

        System.out.println(command);

        Statement statement = connection.createStatement();


        int affectedRows = statement.executeUpdate(command);

       connection.close();

    }


}
