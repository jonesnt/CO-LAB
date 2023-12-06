package colab;
import java.io.IOException;
import javafx.fxml.FXML;
public class projectController {
    
    @FXML
    private void toHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void editTask() throws IOException {
        App.setRoot("editTask");

    }

    @FXML
    private void addToDo() throws IOException {
        App.setRoot("addToDo");
    }
    @FXML
    private void addComment() throws IOException {
        //add comment to list of comments
    }
    @FXML
    private void logOut() throws IOException {
        App.setRoot("login");
    }
}
