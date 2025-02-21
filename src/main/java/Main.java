package seedu.bryan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Bryan using FXML.
 */
public class Main extends Application {

    // Create the backend Bryan instance using a storage file.
    private final seedu.bryan.Bryan bryan = new seedu.bryan.Bryan("data/bryan.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bryan");
            stage.setScene(scene);
            // Inject the Bryan instance into the controller.
            fxmlLoader.<seedu.bryan.MainWindow>getController().setBryan(bryan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serves as the main entry point for the application.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
