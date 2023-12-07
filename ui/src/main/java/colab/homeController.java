
// @author Dillion Norris
package colab;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.*;

public class homeController implements Initializable {

    @FXML
    private Button addTaskButton;
    @FXML
    private ListView<String> projectList;
    @FXML
    private ListView<String> taskList;

    private Manager man;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        man = Manager.getInstance();
        try {
            loadProjects();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Manager.getInstance().logOutUser();
        App.setRoot("login");
    }

    @FXML
    private void addProject() throws IOException {

        Manager m = Manager.getInstance();
        Project newProject = new Project("New Project", "Add a Description.");
        m.addProject(newProject);

        // add project to the list

    }

    @FXML
    private void selectProject() throws IOException {

        
        // get project from selection
        man = Manager.getInstance();
        int selectedProject = projectList.getSelectionModel().getSelectedIndex();
        man.changeCurrentProject(selectedProject);

        // populate tasks to the task list
        loadTasks();
        // make add task button visable
        addTaskButton.setVisible(true);

        // change color of current project
        //will do later
    }

    @FXML
    private void addTask() throws IOException {

    }

    // gets names of projects and loades it itno the FX project list
    private void loadProjects() throws IOException {
        ObservableList<String> projectList = FXCollections.observableArrayList();

        ArrayList<Project> projects = man.getProjectList();
        for (Project project : projects) {
            projectList.add(project.getName());
        }
    }

    // populates tasklist from current project
    private void loadTasks() throws IOException {
        ObservableList<String> taskList = FXCollections.observableArrayList();

        ArrayList<Task> tasks = man.getTaskList();
        for (Task task : tasks) {
            taskList.add(task.getName());
        }
    }

}