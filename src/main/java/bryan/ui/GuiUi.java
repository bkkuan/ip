package seedu.bryan.ui;

import bryan.ui.Ui;

/**
 * GUI implementation of the Ui interface for Bryan.
 */
public class GuiUi implements Ui {
    private String message = "";

    /**
     * Clears the current message.
     */
    public void clearMessage() {
        message = "";
    }

    /**
     * Returns the current message.
     *
     * @return the current message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to display.
     *
     * @param message the message to display.
     */
    @Override
    public void showMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the error message.
     *
     * @param message the error message.
     */
    @Override
    public void showError(String message) {
        this.message = "Error: " + message;
    }

    /**
     * Sets the goodbye message.
     */
    @Override
    public void showGoodbye() {
        this.message = "Goodbye. Hope to see you again soon!";
    }

    /**
     * Sets the welcome message.
     */
    @Override
    public void showWelcome() {
        this.message = "Hello! I'm Bryan, your trustworthy support.\nWhat can I do for you?";
    }
}
