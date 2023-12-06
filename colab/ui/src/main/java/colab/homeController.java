package colab;

import java.io.IOException;
import javafx.fxml.FXML;

public class homeController {

    @FXML
    private void logOut() throws IOException {
        App.setRoot("login");
    }
}