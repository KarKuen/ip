package friday.gui;

import java.io.IOException;

import friday.Friday;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Friday using FXML.
 */
public class Main extends Application {

    private Friday friday = new Friday();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Friday");
            fxmlLoader.<MainWindow>getController().setFriday(friday); // inject the Friday instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
