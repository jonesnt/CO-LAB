
// @author Dillion Norris

package colab;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.*;

public class loginController {

     
    private Manager m ;
    
    @FXML
    private TextField loginUsername;
    @FXML 
    private TextField loginPassword;
    @FXML 
    private Text loginFail;





    @FXML
    private void loginUser(MouseEvent event) throws IOException {
        
        //get user and pass
        

        String username = loginUsername.getText();
        String password = loginPassword.getText();
// validate login
        if (m.logInUser(username, password)){
            // login successful
         // set rooot home
         App.setRoot("home");
        }else {
            //  login failed
            //make loginFail visable 
            loginFail.setVisible(true);
        }
    }


    @FXML 
    private void signUpUser(MouseEvent event) throws IOException {

        //CHANGE TO SIGNUP.FXML
        App.setRoot("signup");
    }
    
}
