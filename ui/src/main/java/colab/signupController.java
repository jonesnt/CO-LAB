
//@author Dillion


package colab;
import java.io.IOException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.*;

public class signupController implements Initializable{

    @FXML 
    private TextField fName;
    @FXML 
    private TextField lName;
    @FXML 
    private TextField sUsername;
    @FXML 
    private TextField sPassword;
    @FXML 
    private TextField sPasswordRe;
    @FXML
    private Text errorSignUp;

    private Manager m;
    private UserManager um;

    @FXML
    private void signupUser(MouseEvent event)throws IOException{

        String firstName = fName.getText();
        String lastName = lName.getText();
        String username = sUsername.getText();
        String password = sPassword.getText();
        String passwordRe = sPasswordRe.getText();
 
        //make sure all feilds are filled out
        if (firstName.equals(null)||lastName.equals(null)||username.equals(null)||password.equals(null)||passwordRe.equals(null)){
            errorSignUp.setText("Please Fill out all feilds");
        }
        //error check passsword
        if(!password.equals(passwordRe)){
            errorSignUp.setText("Passwords do not match");
        }

        //  see if username is already in the db
        User copy  = m.findUser(username);

        if (copy == null){
            um.createNewAccount(username, firstName, lastName, password);
        }else {
            errorSignUp.setText("Username already in use");
        }

        App.setRoot("login");


        

    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {

    }




}