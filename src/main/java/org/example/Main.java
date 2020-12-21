package org.example;



import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {


        lunch();


    }

    private static void lunch() throws Exception{

        int decision = 10;
        Scanner scanner = new Scanner(System.in);


        while(decision!= -1){

            System.out.println("==================");
            System.out.println("What do you want to do?");
            System.out.println("1: Create new office ");
            System.out.println("2: Show offices ");
            System.out.println("3: Modify office ");
            System.out.println("4: Delete office ");
            System.out.println("0: Exit ");

            decision = scanner.nextInt();

            if(decision == 1 ) {
                scanner.nextLine();
                System.out.println("Insert city");
                String city = scanner.nextLine();
                System.out.println("Insert phone");
                String phone = scanner.nextLine();
                System.out.println("Insert address Line 1");
                String addressLine1 = scanner.nextLine();
                System.out.println("Insert country");
                String country = scanner.nextLine();
                System.out.println("Insert postal Code");
                String postalCode = scanner.nextLine();
                System.out.println("Insert territory");
                String territory = scanner.nextLine();
                createOffice(city,phone,addressLine1,country,postalCode,territory);
            }
            if(decision == 2){
                showOffices();
            }
            if(decision==3){
                showOffices();
                System.out.println("write code number of office you want modify");
                int officeNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Insert city");
                String city = scanner.nextLine();
                System.out.println("Insert phone");
                String phone = scanner.nextLine();
                System.out.println("Insert address Line 1");
                String addressLine1 = scanner.nextLine();
                System.out.println("Insert country");
                String country = scanner.nextLine();
                System.out.println("Insert postal Code");
                String postalCode = scanner.nextLine();
                System.out.println("Insert territory");
                String territory = scanner.nextLine();
                modifyOffice(officeNumber,city,phone,addressLine1,country,postalCode,territory);
            }



        }
    }

    public static void createOffice( String city,
                                    String phone, String addressLine1,
                                    String country, String postalCode,
                                    String territory) throws Exception{

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        String preparedStatemen = "INSERT INTO offices ( city, phone, addressLine1, country, postalCode, territory)\n" +
                "VALUES( ?, ?, ?, ?, ?, ?)";


        PreparedStatement statement = connection.prepareStatement(preparedStatemen);

        statement.setString(1, city);
        statement.setString(2, phone);
        statement.setString(3, addressLine1);
        statement.setString(4, country);
        statement.setString(5, postalCode);
        statement.setString(6, territory);

        statement.executeUpdate();
        connection.close();

    }

    public static void modifyOffice( int officeCode, String city,
                                     String phone, String addressLine1,
                                     String country, String postalCode,
                                     String territory) throws Exception{

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        String preparedStatemen = "UPDATE offices\n" +
                "SET  city = ?, phone = ?, addressLine1 = ?, country = ?, postalCode = ?, territory = ?\n" +
                "                WHERE officeCode = ?";

        PreparedStatement statement = connection.prepareStatement(preparedStatemen);

        statement.setString(1, city);
        statement.setString(2, phone);
        statement.setString(3, addressLine1);
        statement.setString(4, country);
        statement.setString(5, postalCode);
        statement.setString(6, territory);
        statement.setInt(7, officeCode);

        statement.executeUpdate();
        connection.close();

    }

    public static void showOffices() throws Exception{

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classicmodels", "root", "123456");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM offices ORDER BY city DESC");


        while (resultSet.next()){


            int officeCode  = resultSet.getInt(1);
            String city  = resultSet.getString(2);
            String phone  = resultSet.getString(3);
            String adressLine1  = resultSet.getString(4);
            String country  = resultSet.getString(5);
            String postalCode  = resultSet.getString(6);
            String territory  = resultSet.getString(7);

            System.out.printf("%10d%20s%20s%25s%10s%10s%10s\n", officeCode,city,phone,adressLine1,country,postalCode,territory);




        };

        connection.close();


    }





}
