//  @author Aidan Hadley
package colab;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.*;

public class projectController implements Initializable {
  @FXML
  private Text pName;
  @FXML
  private Text pInfo;




  private Project cProject;
  private Manager man;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    man = Manager.getInstance();

    cProject = man.getCurrentProject();

    pName.setText(cProject.getName());

    pInfo.setText(getPInfo());

  }


  private String getPInfo() {
    String result;
    
    result = "Assigned Users:\n";
    ArrayList<UUID> auid = cProject.getAssignedUsers();
    for (UUID id : auid) {
      User u = UserManager.getInstance().findUser(id);
      result += u.getFirstName() + " " + u.getLastName() + "\n";
    }

    result += "Description:\n" + cProject.getDescription() + "\n";

    return result;
  }



  @FXML
  private void editProject(MouseEvent event) throws IOException {
    App.setRoot("projectEdit");
 }

  @FXML
  private void addTask(MouseEvent event) throws IOException {
    //  TODO
  }

  
  @FXML
  private void toHome(MouseEvent event) throws IOException {
    App.setRoot("home");
  }

  @FXML
  private void logOut(MouseEvent event) throws IOException {
      Facade.getInstance().logOutUser();
      App.setRoot("login");
  }
}
