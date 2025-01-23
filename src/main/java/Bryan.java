import java.util.Scanner;

public class Bryan {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int numberOfTasks = 0;
        String input;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bryan, your trustworthy support");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while(true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                tasks[numberOfTasks] = input;
                numberOfTasks++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }

        }
        sc.close();

    }
}
