package tracker;

import java.util.Locale;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        boolean exit = false;
        do {
            String command = scanner.nextLine().strip().toLowerCase();
            switch (command) {
                case "exit":
                    System.out.println("Bye!");
                    exit = true;
                    break;
                case "":
                    System.out.println("No input.");
                    break;
                case "add students":
                    Student.getCredentials();
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program");
                    break;
                case "list":
                    Student.listStudents();
                    break;
                case "add points":
                    Student.addScore();
                    break;
                case "find":
                    Student.find();
                    break;
                case "statistics":
                    Course.showStatistics();
                    break;
                case "notify":
                    Notification.sendCertificate();
                    break;
                default:
                    System.out.println("Error: unknown command!");
            }
        } while (!exit);
    }
}