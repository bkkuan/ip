import java.util.Scanner;

public class Bryan {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Scanner sc = new Scanner(System.in);
        welcome(); //intro message

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                goodbye();  //exit message
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskManager.printTasks();
            } else if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskManager.taskscompleted(taskNumber);
                } catch (Exception e) {
                    System.out.println(" Error, use mark followed by number");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int number = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskManager.tasksnotcompleted(number);
                } catch (Exception e) {
                    System.out.println(" Error, use unmark followed by number");
                }
            } else {
                taskManager.addTask(input);
            }
        }
        sc.close();
    }


    private static void welcome() {
        System.out.println("Hello! I'm Bryan, your trustworthy support");
        System.out.println("What can I do for you?");
    }

    private static void goodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
