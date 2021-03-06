package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button searchButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField pwTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button makeAccBtn;

    @FXML
    private TextField zipTextField;

    @FXML
    private TextField stateTextField;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label cityLabel;
    @FXML
    private Button issuebook;
    @FXML
    private Button addbook;



    //connector object to use the SQL related methods in the SQLConnector class
    SQLConnector connector = new SQLConnector();

    //used to toggle certain elements related to registration of the UI such as firstname label & textfield
    static boolean toggleUI = true;

    public void initReg() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();

        //assign the userOfApp object in the Main class to what the user has inserted
        Main.userOfApp = new User(email, pw, firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), cityTextField.getText(), zipTextField.getText(), stateTextField.getText(), countryTextField.getText(), false);
        //creates the user in the database
        connector.createUser(Main.userOfApp);
        emailTextField.clear();
        pwTextField.clear();
        toggleRegUI();

    }

    public void initLogIn() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();
        //isLoggedIn from the Main class will be based on what the verifyLogin method returns.
        Main.isLoggedIn = connector.verifyLogin(email, pw);
        System.out.println("isLoggedIn: " + Main.isLoggedIn);

        //if the user is logged in, hide all the elements related to logging in and/or registering
        if (Main.isLoggedIn) {
            addbook.setVisible(true);
            emailTextField.clear();
            logOutBtn.setVisible(true);
            loginBtn.setVisible(false);
            registerButton.setVisible(false);
            emailLabel.setVisible(false);
            passwordLabel.setVisible(false);
            emailTextField.setVisible(false);
            pwTextField.setVisible(false);
            makeAccBtn.setVisible(false);
            searchButton.setVisible(true);


        }
        pwTextField.clear();
    }

    public void initLogOut() {
        //logging out, therefore set isLoggedIn to false and make userOfApp null -- restarts over from scratch
        Main.isLoggedIn = false;
        Main.userOfApp = null;

        //resets UI to scratch
        logOutBtn.setVisible(false);
        loginBtn.setVisible(true);
        emailLabel.setVisible(true);
        passwordLabel.setVisible(true);
        emailTextField.setVisible(true);
        pwTextField.setVisible(true);
        makeAccBtn.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Log Out");
        alert.setContentText("You have successfully logged out.");
        alert.show();
    }

    public void toggleRegUI() {
        System.out.println("toggleRegUI");
        registerButton.setVisible(toggleUI);

        firstNameLabel.setVisible(toggleUI);
        firstNameTextField.setVisible(toggleUI);

        lastNameLabel.setVisible(toggleUI);
        lastNameTextField.setVisible(toggleUI);

        addressLabel.setVisible(toggleUI);
        addressTextField.setVisible(toggleUI);

        zipLabel.setVisible(toggleUI);
        zipTextField.setVisible(toggleUI);

        cityTextField.setVisible(toggleUI);
        cityLabel.setVisible(toggleUI);

        stateLabel.setVisible(toggleUI);
        stateTextField.setVisible(toggleUI);

        countryLabel.setVisible(toggleUI);
        countryTextField.setVisible(toggleUI);

        toggleUI = !toggleUI;
    }

    //switching to another scene when search button is pressed
    public void switchToSearchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SearchScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void issuebook(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlfiles/IssueBook.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void addbook(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlfiles/addBook.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}



