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

    SQLConnector connector = new SQLConnector();

    public void initReg() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();
        Main.userOfApp = new User(email, pw, "testName", "testLastName", "testAddress", "testCity", "testZip", "testState", "testCountry", false);
        connector.createUser(Main.userOfApp);
        emailTextField.clear();
        pwTextField.clear();

    }

    public void initLog() {

        String pw = pwTextField.getText();
        String email = emailTextField.getText();
        Main.isLoggedIn = connector.verifyLogin(email, pw);
        System.out.println("isLoggedIn: " + Main.isLoggedIn);
        if (Main.isLoggedIn == true) {
            logOutBtn.setVisible(true);
            loginBtn.setVisible(false);
            registerButton.setVisible(false);
            emailLabel.setVisible(false);
            passwordLabel.setVisible(false);
            emailTextField.setVisible(false);
            pwTextField.setVisible(false);
        }
        emailTextField.clear();
        pwTextField.clear();
    }

    public void initLogOut() {
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
