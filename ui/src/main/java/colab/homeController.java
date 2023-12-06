
// @author Dillion Norris
package colab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.fxml.Initializable;
import model.*;




public class homeController  {

    @FXML 

        private Button addTaskButton;



    @FXML
    private  void logOut(ActionEvent event) throws IOException {
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
    private void selectProject() throws IOException {
        Manager m = Manager.getInstance();

        m.changeCurrentProject(0);// add project num after prodlist is figured out 

        // add tasks to the task list 

        //make add task button visable 
        addTaskButton.setVisible(true);

        // change color of current project


    }

    @FXML
    private void addTask() throws IOException {


    }
   

}