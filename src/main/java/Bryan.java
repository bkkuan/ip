/**
 * Represents Bryan, a simple chat bot that echoes the user's input.
 */
public class Bryan {

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user's chat message.
     * @return a response string prefixed with "Duke heard: ".
     */
    public String getResponse(String input) {
        return "Bryan heard: " + input;
    }
}
