package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField SignUpDateBirth;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpName;

    @FXML
    private Button signUpUser;

    @FXML
    private TextField signUpCountry;

    @FXML
    private CheckBox signupCheckBoxFemale;

    @FXML
    private CheckBox signupCheckBoxMale;

    @FXML
    void initialize() {

        signUpUser.setOnAction(event -> {

            signUpNewUser();
        });
    }

    private void signUpNewUser() {

        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = SignUpName.getText();
        String lastname = SignUpLastName.getText();
        String username = LoginField.getText();
        String password = PasswordField.getText();
        String datebirth = SignUpDateBirth.getText();
        String location = signUpCountry.getText();
        String gender = "" ;

        if(signupCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstname,lastname,username,password,datebirth,location,gender);

        dbHandler.signUpUser(user);
    }

}
