//  @author Aidan Hadley
package colab;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
  @FXML
  private ComboBox<String> filter;
  @FXML
  private ListView<String> taskList;



  private Project cProject;
  private Manager man;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    man = Manager.getInstance();

    cProject = man.getCurrentProject();

    pName.setText(cProject.getName());

    pInfo.setText(getPInfo());

    //  debug test for the combobox
    ObservableList<String> filters = FXCollections.observableArrayList();
    ArrayList<String> columns = man.getColumnList();
    filters.add("Yours");
    filter.getItems();
    for (String col : columns) {
      filters.add(col);
      // filter.getItems().add(col);
    }
    filter.setItems(filters);
    //  default:
    try {
      setTasks("Yours");
    } catch (Exception e) {
      //  no tasks womp womp
    }

        // Set up a ChangeListener for ComboBox selection changes
        filter.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          setTasks(newValue);
      });
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

    private void setTasks(String colName) {
      //  collect tasks
      ArrayList<String> cNames = man.getColumnList();
      ArrayList<Task> tasks = man.getTaskList();
      ArrayList<Task> result = new ArrayList<Task>();
      UUID auid = man.getCurrentUser().getUserID();
      //  first check if it's name is valid
      if (!cNames.contains(colName) && !colName.equals("Yours"))
        return;
      //  return yours specifically
      if (colName.equals("Yours")) {
          for (Task t : tasks) {
            ArrayList<UUID> ids = t.getAssignedUsers();
            for (UUID id : ids) 
              if (id.equals(auid))
                result.add(t);
          }
      } else {
        for (Task t : tasks) {
          if (t.getColumnTag().equals(colName))
            result.add(t);
        }
      }
      ObservableList<String> tasks_List = FXCollections.observableArrayList();
      for (Task t : result) {
        System.out.println(t.getName());
        tasks_List.add(t.getName());
      }
      taskList.setItems(tasks_List);
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

@FXML

private void selectTask(MouseEvent event) throws IOException {
  

  int selectedTask = taskList.getSelectionModel().getSelectedIndex();
  selectedTask++;
Facade.getInstance().changeCurrentTask(selectedTask);
  App.setRoot("task");

}

}
