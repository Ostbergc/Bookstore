package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //user object in which the entire app will interact around, this object is entirely based on the user (isemployee, firstname, email, passwoerd, etc.)
    public static User userOfApp;


    //boolean to check whether the user is logged in, the entire app will act around the value of this variable
    public static boolean isLoggedIn = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bookstore");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
