package sample;


import javafx.scene.control.Alert;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class SQLConnector {

    /*TODO WARNING***********************************************************************
      TODO MUST GET SQL JAR OR SOMETHING TO CONNECT TO DATABASE INTO LIBRARY
      TODO********************************************************************************************
     */
    private String url = "jdbc:mysql://den1.mysql6.gear.host:3306/group10bookstore?user=group10bookstore&password=Bi3o_!AcSP51&serverTimezone=UTC";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    //use this to connect to the database
    public void connect() {
        try {
            System.out.println("Connection successfully established..");
            connection = DriverManager.getConnection(url);

        } catch (SQLException sq) {
            sq.printStackTrace();
            System.out.println(sq.getMessage());
        }
    }


    public void createUser(User user) {
        System.out.println("Entered createuser");

        try {
            connect();
            String sql = "INSERT INTO `users` (`email`, `password`, `firstname`, `lastname`, `address`, `city`, `zip`, `state`, `country`, `isEmployee`) VALUES " +
                    "('" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getAddress() + "', " +
                    "'" + user.getCity() + "', '" + user.getZip() + "', '" + user.getState() + "', '" + user.getCountry() + "', '0');";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("User has been registered, you may now log in.");
            alert.show();
            System.out.println("User created: " + user.toString());

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("SQLIntegrityConstraintViolationException: email already exists in database. ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Email has already been taken. Please choose a different email for registration.");
            alert.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean verifyLogin(String userIn, String pwIn) {
        System.out.println("Entered verifylogin.");
        boolean isVerified = false;
        try {
            connect();
            String sql = "SELECT email, password FROM users WHERE email = '" + userIn + "' AND password = '" + pwIn + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println("userin: " + userIn + " , pwin: " + pwIn);
            while (resultSet.next()) {
                if (resultSet.getString("email").equals(userIn) && resultSet.getString("password").equals(pwIn)) {
                    System.out.println(resultSet.getString("email") + " is email");
                    System.out.println(resultSet.getString("password") + " is password");
                    isVerified = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("You have successfully logged in.");
                    alert.show();
                }
            }

            if (isVerified == false) {
                System.out.println("Wrong email and/or password.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Wrong email and/or password.");
                alert.show();
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }


    //disconnect from the database
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }


}





