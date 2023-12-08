package colab;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Facade;

//import model.Manager;

import java.io.IOException;
import java.net.URL;

/**
 * @author Dillion Norris
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 800, 480);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    Facade.getInstance().logOutUser();
                } catch (Exception e){
                    //  do nothing
                }
                Platform.exit();
                System.exit(0);
            }
        });
    }

    


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL location = App.class.getResource(fxml + ".fxml");
        /*  This is incredibly unstable for some reason (at least on my 
            machine). When opening it, it takes a few tries to actually get
            it working. This is the same with Portia's program in my experience
        */ 
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}