import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Tasks> tasks;
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    //add task
    public void addTask(String information) {
        Tasks task = new Tasks(information);
        tasks.add(task);
        System.out.println(" added: " + information);
    }
    //print tasks
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty. Let's add something to get started! ");
        } else {
            System.out.println(" Here's your task list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }
    //mark tasks done
    public void taskscompleted(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).taskDone();

            System.out.println(" Great job! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskNumber));
            System.out.println(" Keep up the momentum! ");

        } else {
            System.out.println(" Oops! You do not have that task. Try again!");
        }
    }
    //unmark tasks
    public void tasksnotcompleted(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).taskNotDone();

            System.out.println(" Task marked as not done:");
            System.out.println("   " + tasks.get(taskNumber));

        } else {
            System.out.println(" Oops! You do not have that task. Try again!");
        }
    }
}
