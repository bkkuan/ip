import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import seedu.bryan.Bryan;

/**
 * The Main class for the Bryan application.
 * <p>
 * Sets up the UI layout which includes a ScrollPane containing a dialog container,
 * a TextField for user input, and a Button to send messages. It also handles user input events
 * and generates responses using the Bryan class.
 * </p>
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Images for the user and Bryan avatars.
    private Image userImage;
    private Image bryanImage;

    // Bryan instance to generate responses.
    private Bryan bryan = new Bryan();

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        // Load images from the resources folder.
        userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
        bryanImage = new Image(this.getClass().getResourceAsStream("/images/Bryan.png"));

        // Set up the ScrollPane and its content container.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Set up the TextField and Send Button.
        userInput = new TextField();
        sendButton = new Button("Send");

        // Create the main layout using an AnchorPane.
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Create the scene with the main layout.
        scene = new Scene(mainLayout);

        // Configure the stage.
        stage.setTitle("Bryan");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // Set preferred sizes for the layout and its components.
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        // Position the components within the AnchorPane.
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Set event handlers so that user input is registered.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time the dialog container's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Set the scene and display the stage.
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles user input by creating dialog boxes for the user and Bryan's responses,
     * appending them to the dialog container, and clearing the user input field.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.trim().isEmpty()) {
            return;
        }
        String dukeText = bryan.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBryanDialog(dukeText, bryanImage)
        );
        userInput.clear();
    }
}
