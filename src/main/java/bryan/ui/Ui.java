package bryan.ui;

import java.util.Scanner;

/**
 * The Ui class handles user interactions and displays messages.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bryan, your trustworthy support\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command from the user.
     *
     * @return the trimmed command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a general message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
