
// @author Dillion Norris

package colab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
//import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import model.*;

public class loginController implements Initializable {

    @FXML
    private TextField loginUsername;
    
    @FXML
    private TextField loginPassword;
    @FXML
    private Text loginFail;

    @FXML
    private void loginUser(MouseEvent event) throws IOException {
        // get user and pass
        String username = loginUsername.getText();
        System.out.println(username);
        String password = loginPassword.getText();
        // validate login
        Manager m = Manager.getInstance();

        // if (m.logInUser(username, password)) {
        if (m.logInUser("aMadden", "1234")) {
            // login successful
            // set rooot home
            System.out.println("login successful");
            App.setRoot("home");
        } else {
            // login failed
            // make loginFail visable
            System.out.println("login BAD");
            loginFail.setVisible(true);
        }
    }

    @FXML
    private void signUpUser(MouseEvent event) throws IOException {
        // CHANGE TO SIGNUP.FXML
        App.setRoot("signup");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
