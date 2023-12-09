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

public class projectEditController implements Initializable {
  @FXML
  private Text pName;

  @FXML
  private Text pInfo;

  @FXML
  private TextField descBox;

  @FXML
  private TextField projectNameBox;

  @FXML
  private TextField usernameAddBox;

  @FXML
  private Text errorUser;

  private Project cProject;
  private Manager man;
  @Override
  public void initialize(URL url, ResourceBundle rb){
    man = Manager.getInstance();

    cProject = man.getCurrentProject();

    getPName();

    getPInfo();
  }

  private void getPName() {
    pName.setText(cProject.getName());
  }
  
  private void getPInfo() {
    String result;
    
    result = "Assigned Users:\n";
    ArrayList<UUID> auid = cProject.getAssignedUsers();
    for (UUID id : auid) {
      User u = UserManager.getInstance().findUser(id);
      result += u.getFirstName() + " " + u.getLastName() + "\n";
    }

    result += "Description:\n" + cProject.getDescription() + "\n";

    pInfo.setText(result);
  }

  @FXML
  private void addUser(MouseEvent event) throws IOException {
    //  TODO
    String name = usernameAddBox.getText();
    if (name == null) {
      errorUser.setVisible(true);
      return;
    }
    User user = UserManager.getInstance().findUser(name);
    if (user == null) {
      errorUser.setVisible(true);
      return;
    }
    man.getCurrentProject().addUser(user);
    getPInfo();
  }

  @FXML
  private void editName(MouseEvent event) throws IOException {
    String name = projectNameBox.getText();
    if (name != null)
      man.getCurrentProject().editProject(name, null);
    getPName();
  }
  
  @FXML
  private void logOut(MouseEvent event) throws IOException {
      Facade.getInstance().logOutUser();
      App.setRoot("login");
  }

  @FXML
  private void editDesc(MouseEvent event) throws IOException {
    String desc = descBox.getText();
    if (desc != null)
      man.getCurrentProject().editProject(null, desc);
    getPInfo();
  }

  @FXML
  private void toProject(MouseEvent even) throws IOException {
    App.setRoot("project");
  }
}
