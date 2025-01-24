import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Tasks> tasks;
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    //add task
    public void addTask(String input) {

        Tasks task = null;
        if (input.startsWith("todo ")) {
            String information = input.substring(5).trim();
            task = new Todo(information);
        }
        else if (input.startsWith("deadline ")) {
            String details = input.substring(9).trim();
            String[] parts = details.split(" /by ", 2);
            if (parts.length < 2) {
                System.out.println("Invalid deadline format. Use: deadline <description> /by <time>");
                return;
            }
            task = new Deadline(parts[0].trim(), parts[1].trim());
        }
        else if (input.startsWith("event ")) {
            String details = input.substring(6).trim();
            String[] parts = details.split(" /from ", 2);
            if (parts.length < 2 || !parts[1].contains(" /to ")) {
                System.out.println("Invalid event format. Use: event <description> /from <start> /to <end>");
                return;
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            task = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        }
        else {
            System.out.println("Format is wrong. Please retry");
            return;
        }

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    //print tasks
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty. Let's add something to get started! ");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    //mark tasks done
    public void taskscompleted(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).taskDone();

            System.out.println(" Great job! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskNumber));


        } else {
            System.out.println(" Oops! You do not have that task. Try again!");
        }
    }
    //unmark tasks
    public void tasksnotcompleted(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).taskNotDone();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskNumber));

        } else {
            System.out.println(" Oops! You do not have that task. Try again!");
        }
    }
}
