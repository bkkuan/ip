package seedu.bryan;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private seedu.bryan.Bryan bryan;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image bryanImage = new Image(this.getClass().getResourceAsStream("/images/Bryan.png"));

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        // Bind the scroll pane's vertical value to the dialog container's height.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bryan instance into this controller.
     *
     * @param b the Bryan instance.
     */
    public void setBryan(seedu.bryan.Bryan b) {
        this.bryan = b;
    }

    /**
     * Handles user input by processing the command via Bryan and creating dialog boxes for both
     * the user input and Bryan's response.
     * If the user types "bye", the application exits after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // Create a dialog for the user's input.
        dialogContainer.getChildren().add(seedu.bryan.DialogBox.getUserDialog(input, userImage));

        String response = bryan.getResponse(input);
        // Create a dialog for Bryan's response.
        dialogContainer.getChildren().add(seedu.bryan.DialogBox.getBryanDialog(response, bryanImage));

        userInput.clear();

        // If the command is "bye", schedule application exit.
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}
