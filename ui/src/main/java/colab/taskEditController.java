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

public class taskEditController implements Initializable {
  @FXML
  private Text tName;

  @FXML
  private Text tInfo;

  @FXML
  private TextField descBox;

  @FXML
  private TextField taskNameBox;

  @FXML
  private TextField usernameAddBox;

  @FXML
  private Text errorUser;

  private Task cTask;
  private Manager man;
  @Override
  public void initialize(URL url, ResourceBundle rb){
    man = Manager.getInstance();

    cTask = man.getCurrentTask();

    getTName();

    getTInfo();
  }

  private void getTName() {
    tName.setText(cTask.getName());
  }
  
  private void getTInfo() {
    String result;
    
    result = "Assigned Users:\n";
    ArrayList<UUID> auid = cTask.getAssignedUsers();
    for (UUID id : auid) {
      User u = UserManager.getInstance().findUser(id);
      result += u.getFirstName() + " " + u.getLastName() + "\n";
    }

    result += "Description:\n" + cTask.getDescription() + "\n";

    tInfo.setText(result);
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
    man.getCurrentTask().assignUser(user.getUserID(), man.getCurrentUser());
    getTInfo();
  }

  @FXML
  private void editName(MouseEvent event) throws IOException {
    String name = taskNameBox.getText();
    if (name != null)
      man.getCurrentTask().editTask(name, null, man.getCurrentUser());
    getTName();
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
      man.getCurrentTask().editTask(null, desc, man.getCurrentUser());
    getTInfo();
  }

  @FXML
  private void toTask(MouseEvent even) throws IOException {
    App.setRoot("task");
  }
}
