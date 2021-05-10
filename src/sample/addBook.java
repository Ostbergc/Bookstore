package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class addBook implements Initializable {
    SQLConnector conn;
    Connection connection;
    @FXML
    private javafx.scene.control.TextField titletxt;
    @FXML
    private javafx.scene.control.TextField authortxt;
    @FXML
    private javafx.scene.control.TextField genretxt;
    @FXML
    private javafx.scene.control.TextField quantitytxt;
    @FXML
    private javafx.scene.control.TextField pricetxt;
    private String booktitle;
    private String authorname;
    private String genre;
    private int quantity;
    private double price;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn=new SQLConnector();

    }
    @FXML
    public void add(ActionEvent event) {
        try {
            booktitle = titletxt.getText();
            authorname = authortxt.getText();
            genre = genretxt.getText();
            quantity = Integer.parseInt(quantitytxt.getText());
            price = Double.parseDouble(pricetxt.getText());
        } catch(NumberFormatException e){

        }



        if ( titletxt.getText().isEmpty() ||  authortxt.getText().isEmpty() ||  genretxt.getText().isEmpty() ||  quantitytxt.getText().isEmpty() ||  pricetxt.getText().isEmpty()  )  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;

        }



        try {

            conn.connect();
            String query = "INSERT INTO `books` (`title`, `author`, `genre`, `quantity`, `price` ) VALUES " +
                    "('" + booktitle + "', '" +  authorname + "', '" + genre + "', '" + quantity + "', '" +   price + "');";
            System.out.println(query);
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("The Book with title  "+"`"+ booktitle +"`" +" and author name   " +"`"+ authorname + "`" +   "has been added" );
            alert.show();
            System.out.println("User created: " );

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("transaction failed");
            alert.show();


        }

    }
    @FXML
    void backButton(ActionEvent ae) throws IOException {

        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
