import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a custom dialog box containing a text label and an image.
 * <p>
 * This control is designed to be reused whenever a dialog (or chat message)
 * needs to be displayed in the application.
 * </p>
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param s the text to be displayed in the dialog.
     * @param i the image to be displayed alongside the text.
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Styling the dialog box.
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        // Add the text and image to the HBox.
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is used to differentiate between user input and Duke's responses.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a dialog box for user input.
     *
     * @param s the text to be displayed in the dialog.
     * @param i the image to be displayed alongside the text.
     * @return a DialogBox instance representing user input.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Returns a dialog box for Duke's response.
     *
     * @param s the text to be displayed in the dialog.
     * @param i the image to be displayed alongside the text.
     * @return a DialogBox instance representing Duke's response.
     */
    public static DialogBox getBryanDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

