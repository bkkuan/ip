package seedu.bryan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        // Bind the vertical scroll value of the ScrollPane to the dialog container's height
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bryan instance into the controller.
     *
     * @param b the Bryan instance.
     */
    public void setBryan(seedu.bryan.Bryan b) {
        bryan = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bryan's reply,
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bryan.getResponse(input);
        dialogContainer.getChildren().addAll(
                seedu.bryan.DialogBox.getUserDialog(input, userImage),
                seedu.bryan.DialogBox.getBryanDialog(response, bryanImage)
        );
        userInput.clear();
    }
}
