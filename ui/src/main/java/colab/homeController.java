
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
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import model.*;

public class homeController implements Initializable {

    @FXML
    private Button addTaskButton;
    @FXML
    private ListView<String> projectList;
    @FXML
    private ListView<String> taskList;

    private Facade fac;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fac = Facade.getInstance();
        try {
            loadProjects();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void logOut(MouseEvent event) throws IOException {
        Facade.getInstance().logOutUser();
        App.setRoot("login");
    }

    @FXML
    private void addProject(MouseEvent event) throws IOException {
        Facade f = Facade.getInstance();
        Project newProject = new Project("New Project", "Add a Description.");
        f.addProject(newProject);

        // add project to the list
        loadProjects();
    }

    @FXML
    private void selectProject(MouseEvent event) throws IOException {

        
        // get project from selection
        fac = Facade.getInstance();
        int selectedProject = projectList.getSelectionModel().getSelectedIndex();
        selectedProject++;
        fac.changeCurrentProject(selectedProject);
        App.setRoot("project");
    }

    @FXML
    private void addTask() throws IOException {

    }

    // gets names of projects and loads it itno the FX project list
    private void loadProjects() throws IOException {
        ObservableList<String> project_List = FXCollections.observableArrayList();
        ArrayList<Project> projects = fac.getProjectList();
        for (Project project : projects) {
            project_List.add(project.getName());
            ArrayList<Task> tasks = project.getTasks();
        }
        projectList.setItems(project_List);
    }

    // populates tasklist from current project
    private void loadTasks() throws IOException {
        ObservableList<String> taskList = FXCollections.observableArrayList();

        ArrayList<Task> tasks = fac.getTaskList();
        for (Task task : tasks) {
            taskList.add(task.getName());
        }
    }

}