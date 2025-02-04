import java.util.Scanner;

public class Bryan {
    private static final String FILE_PATH = "data/bryan.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private static final TaskManager taskManager = new TaskManager(storage.load());
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        welcome();
        runCommandLoop();
        goodbye();
        sc.close();
    }

    private static void runCommandLoop() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) break;
            processInput(input);
        }
    }

    private static void processInput(String input) {
        try {
            if (input.equalsIgnoreCase("list")) {
                taskManager.printTasks();
            } else if (input.startsWith("mark ")) {
                handleMarkCommand(input);
            } else if (input.startsWith("unmark ")) {
                handleUnmarkCommand(input);
            } else if (input.startsWith("delete ")) {
                handleDeleteCommand(input);
            } else {
                handleAddCommand(input);
            }
        } catch (BryanException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleMarkCommand(String input) {
        int taskNumber = parseTaskNumber(input);
        taskManager.markTask(taskNumber);
        storage.save(taskManager.getTasks());
    }

    private static void handleUnmarkCommand(String input) {
        int taskNumber = parseTaskNumber(input);
        taskManager.unmarkTask(taskNumber);
        storage.save(taskManager.getTasks());
    }

    private static void handleDeleteCommand(String input) {
        int taskNumber = parseTaskNumber(input);
        taskManager.deleteTask(taskNumber);
        storage.save(taskManager.getTasks());
    }

    private static void handleAddCommand(String input) throws BryanException {
        taskManager.addTask(input);
        storage.save(taskManager.getTasks());
    }

    private static int parseTaskNumber(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private static void welcome() {
        System.out.println("Hello! I'm Bryan, your trustworthy support\nWhat can I do for you?");
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
