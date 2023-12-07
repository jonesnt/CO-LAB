
//@author Dillion


package colab;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Comment;
import model.Manager;
import model.ToDo;
import model.UserManager;
public class taskController implements Initializable {
    
private Manager man;

    @FXML
    private Text tName;
    @FXML
    private Text tAssign;
    @FXML
    private Text tDesc;
    @FXML
    private Text tPriority;
    @FXML
    private Text tHistory;
    @FXML
    private ListView<String> todoList;
    @FXML
    private ListView<String> commentList;



 @Override
    public void initialize(URL url, ResourceBundle rb) {
       man =  Manager.getInstance();
       ArrayList<UUID> uuidList = man.getCurrentTask().getAssignedUsers();
       UserManager um  = UserManager.getInstance();
       String users = " ";

       for (UUID uuid : uuidList) {
        String userName = um.getUserName(uuid);
          users =  users + " "+ userName +"\n";
       }
        tAssign.setText("Assigned to: "+users );
        // update task name
       tName.setText(man.getCurrentTask().getName());
       // update task desc
       tDesc.setText(man.getCurrentTask().getDescription());
       
       
        //load todos
            ObservableList<String> todoList = FXCollections.observableArrayList();
    
            ArrayList<ToDo> todos = man.getCurrentTask().getTodo();
            for (ToDo todo : todos) {
                todoList.add(todo.getName());
            }
        
        //load comments
             ObservableList<String> commentList = FXCollections.observableArrayList();
    
            ArrayList<Comment> comments = man.getCurrentTask().getComments();
            for (Comment comment : comments) {
                commentList.add(comment.getCommentData());
            }
    }


    @FXML
    private void toHome(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void editTask(MouseEvent event) throws IOException {
        App.setRoot("editTask");
    }

    @FXML
    private void addToDo(MouseEvent event) throws IOException {
        App.setRoot("addToDo");
    }
    @FXML
    private void addComment(MouseEvent event) throws IOException {
        //add comment to list of comments
        App.setRoot("addComment");
    }
    @FXML
    private void logOut() throws IOException {
        man = Manager.getInstance();
        man.logOutUser();
        App.setRoot("login");
    }
    
    
}
