package bryan.ui;

/**
 * Interface for the user interface of Bryan.
 */
public interface Ui {
    /**
     * Displays a general message.
     *
     * @param message the message to display.
     */
    void showMessage(String message);

    /**
     * Displays an error message.
     *
     * @param message the error message to display.
     */
    void showError(String message);

    /**
     * Displays a goodbye message.
     */
    void showGoodbye();

    /**
     * Displays a welcome message.
     */
    void showWelcome();
}
