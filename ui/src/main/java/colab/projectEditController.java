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

  private Project cProject;
  private Manager man;
  @Override
  public void initialize(URL url, ResourceBundle rb){
    cProject = man.getCurrentProject();

    pName.setText(cProject.getName());

    pInfo.setText(getPInfoSafe());
  }

  private String getPInfoSafe() {
    
  }

  @FXML
  private void addUser(MouseEvent event) throws IOException {
    //  TODO
  }

  @FXML
  private void editProject(MouseEvent event) throws IOException {
    //  TODO
  }
  
  @FXML
  private void logOut(MouseEvent event) throws IOException {
    //  TODO
  }

  @FXML
  private void editDesc(MouseEvent event) throws IOException {
    //  TODO
  }

  @FXML
  private void toProject(MouseEvent even) throws IOException {
    //  TODO
  }
}
