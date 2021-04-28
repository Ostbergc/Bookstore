package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

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

    //connector object to use the SQL related methods in the SQLConnector class
    SQLConnector connector = new SQLConnector();

    public void initReg() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();

        //assign the userOfApp object in the Main class to what the user has inserted
        Main.userOfApp = new User(email, pw, "testName", "testLastName", "testAddress", "testCity", "testZip", "testState", "testCountry", false);
        //creates the user in the database
        connector.createUser(Main.userOfApp);
        emailTextField.clear();
        pwTextField.clear();

    }

    public void initLog() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();
        //isLoggedIn from the Main class will be based on what the verifyLogin method returns.
        Main.isLoggedIn = connector.verifyLogin(email, pw);
        System.out.println("isLoggedIn: " + Main.isLoggedIn);

        //if the user is logged in, hide all the elements related to logging in and/or registering
        if (Main.isLoggedIn == true) {
            logOutBtn.setVisible(true);
            loginBtn.setVisible(false);
            registerButton.setVisible(false);
            emailLabel.setVisible(false);
            passwordLabel.setVisible(false);
            emailTextField.setVisible(false);
            pwTextField.setVisible(false);
        }
        pwTextField.clear();
    }

    public void initLogOut() {
        //logging out, therefore set isLoggedIn to false and make userOfApp null -- restarts over from scratch
        Main.isLoggedIn = false;
        Main.userOfApp = null;

        logOutBtn.setVisible(false);
        registerButton.setVisible(true);
        loginBtn.setVisible(true);
        emailLabel.setVisible(true);
        passwordLabel.setVisible(true);
        emailTextField.setVisible(true);
        pwTextField.setVisible(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Log Out");
        alert.setContentText("You have successfully logged out.");
        alert.show();
    }

}
