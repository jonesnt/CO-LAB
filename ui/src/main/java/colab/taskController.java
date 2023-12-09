
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
import model.Facade;
import model.Manager;
import model.ToDo;
import model.User;
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
    @FXML
    private TextField usernameAdd;
    @FXML 
    private Text error ;
    @FXML 
    private TextField descBox;
    @FXML 
    private TextField pBox;
    



 @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Facade f = Facade.getInstance();
    man = Manager.getInstance();

       ArrayList<UUID> uuidList = f.getCurrentTask().getAssignedUsers();
       UserManager um  = UserManager.getInstance();
       String users = " ";
       

       setTDesc();

       
        //load todos
            ObservableList<String> todo_List = FXCollections.observableArrayList();
    
            ArrayList<ToDo> todos = man.getToDoList();
            for (ToDo todo : todos) {

                todo_List.add(todo.getName());
            }
            todoList.setItems(todo_List);
        //load comments
             ObservableList<String> comment_List = FXCollections.observableArrayList();
    
            ArrayList<Comment> comments = man.getCommentList();
            for (Comment comment : comments) {
                comment_List.add(comment.getCommentData());
            }
            commentList.setItems(comment_List);
    }

    private void setTDesc() {
        String result = "";
        result += "Assigned Users:\n";
        for (UUID u : man.getCurrentTask().getAssignedUsers()) {
            User user = UserManager.getInstance().findUser(u);
            if (user != null) {
                result += user.getFirstName() + " " + user.getLastName() + "\n";
            }
        }
        result += "Description:\n";
        String desc = man.getCurrentTask().getDescription();
        if (desc != null)
            result += desc + "\n";
        result += "Column Tag:\n";
        result += man.getCurrentTask().getColumnTag();
        tDesc.setText(result);
    }

    @FXML
    private void toHome(MouseEvent event) throws IOException {
        App.setRoot("project");
    }

    @FXML
    private void toEditTask(MouseEvent event) throws IOException {
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


    @FXML
    private void addUser (MouseEvent event) throws IOException {
        String username = usernameAdd.getText();
        UserManager um  = UserManager.getInstance();
        ArrayList<User> userList = um.getUserList();
        User currentUser = man.getCurrentUser();
        boolean userFound = false;
        for(User user: userList){
            if (user.getUsername().equals(username)) {
                man.getCurrentTask().assignUser(user.getUserID(), currentUser);
                userFound = true;
                return;
            }
        }
         error.setVisible(!userFound);
    }

    @FXML 
    private void editTask (MouseEvent event) throws IOException {

        
    // get description
        String desc = tDesc.getText();
    // get priority
        String priority  = tPriority.getText();
    // chnage if different 
        if (!descBox.equals(null))
        tDesc.setText(descBox.getText());

        if (!pBox.equals(null))
        tPriority.setText(pBox.getText());
    //apply to task 
        man.getCurrentTask().editTask(man.getCurrentTask().getName(), descBox.getText(), man.getCurrentUser());

    }
}
