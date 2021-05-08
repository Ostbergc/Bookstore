package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    SQLConnector connector = new SQLConnector();
    public String title;
    public String author;
    public String genre;
    public int quantity;
    public int price;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fillListWithBooks();
    }
    //going back to first scene
    public void backToMainMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //first clears listview then fills it with all books from database if quantity>0
    public void fillListWithBooks(){

        listView.getItems().clear();

        try {
            connector.connect();
            String sql = "SELECT * FROM books;";
            connector.statement = connector.connection.createStatement();
            connector.resultSet = connector.statement.executeQuery(sql);

            while (connector.resultSet.next()){
                title = connector.resultSet.getString("title");
                author = connector.resultSet.getString("author");
                genre = connector.resultSet.getString("genre");
                price = connector.resultSet.getInt("price");
                quantity = connector.resultSet.getInt("quantity");
                if (quantity > 0){
                    listView.getItems().addAll(title + "  -  " + author + "  -  $" + price + " USD");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //first clears listview then compares database with textfield and adds matches if quantity>0
    public void searchForBooks(){

        listView.getItems().clear();

        try {
            connector.connect();
            String sql = "SELECT * FROM books;";
            connector.statement = connector.connection.createStatement();
            connector.resultSet = connector.statement.executeQuery(sql);

            while (connector.resultSet.next()){
                title = connector.resultSet.getString("title");
                author = connector.resultSet.getString("author");
                genre = connector.resultSet.getString("genre");
                price = connector.resultSet.getInt("price");
                quantity = connector.resultSet.getInt("quantity");
                if (quantity > 0 && (genre.equalsIgnoreCase(searchTextField.getText()) || title.contains(searchTextField.getText()) || author.contains(searchTextField.getText()))){
                    listView.getItems().addAll(title + "  -  " + author + "  -  $" + price + " USD");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
