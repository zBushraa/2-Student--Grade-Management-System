import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final int MAX_STUDENTS = 13;
    private static Student[] students = new Student[MAX_STUDENTS];
    private static int studentCount = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Integer> userChoices = new ArrayList<>();

    public static void main(String[] args) {
         
        System.out.println("Student Grade Management System");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            if (choice >= 1 && choice <= 7) {
                userChoices.add(choice);
            }

            switch (choice) {
                case 1: 
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    viewStudentInfo();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    viewChoiceHistory();
                    break;
                case 7:
                    running = false;
                    System.out.println("\nThank you for using the Grade Management System!");
                    displayFinalChoiceHistory();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Add grade to student");
        System.out.println("3. View student information");
        System.out.println("4. View all students");
        System.out.println("5. Delete student");
        System.out.println("6. View choice history");
        System.out.println("7. Exit");
    }

    private static void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum capacity reached!");
            return;
        }

        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter student ID: ");
        int id = getIntInput("");

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                System.out.println("Student with ID " + id + " already exists!");
                return;
            }
        }

        students[studentCount] = new Student(name, id);
        studentCount++;
        System.out.println("Student added successfully!");
    }

    private static void addGradeToStudent() {
        if (studentCount == 0) {
            System.out.println("No students found. Please add a student first.");
            return;
        }

        System.out.println("\n--- Add Grade to Student ---");
        System.out.print("Enter student ID: ");
        int id = getIntInput("");

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        System.out.print("Enter grade (0-100): ");
        double grade = getDoubleInput("");
        student.addGrade(grade);
        
        System.out.println("\n--- Updated Student Information ---");
        System.out.println("Average Score: " + String.format("%.2f", student.calculateAverage()));
        System.out.println("Grade Level: " + student.assignGradeLevel());
    }

    private static void viewStudentInfo() {
        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        System.out.print("\nEnter student ID to view information: ");
        int id = getIntInput("");

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        student.displayInfo();
    }

    private static void viewAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- All Students ---");
        for (int i = 0; i < studentCount; i++) {
            students[i].displayInfo();
        }
    }

    private static void deleteStudent() {
        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        System.out.print("\nEnter student ID to delete: ");
        int id = getIntInput("");

        int indexToDelete = -1;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        for (int i = indexToDelete; i < studentCount - 1; i++) {
            students[i] = students[i + 1];
        }
        students[studentCount - 1] = null;
        studentCount--;
        System.out.println("Student deleted successfully!");
    }

    private static Student findStudentById(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static void viewChoiceHistory() {
        if (userChoices.isEmpty()) {
            System.out.println("No choices made yet.");
            return;
        }

        System.out.println("\n--- Your Choice History ---");
        for (int i = 0; i < userChoices.size(); i++) {
            int choice = userChoices.get(i);
            System.out.println((i + 1) + ". Choice: " + getChoiceName(choice));
        }
    }

    private static void displayFinalChoiceHistory() {
        if (userChoices.isEmpty()) {
            System.out.println("No choices were made during this session.");
            return;
        }

        System.out.println("\n--- Final Choice Summary ---");
        System.out.println("Total choices made: " + userChoices.size());
        System.out.println("Choices:");
        for (int i = 0; i < userChoices.size(); i++) {
            int choice = userChoices.get(i);
            System.out.println((i + 1) + ". " + getChoiceName(choice));
        }
    }

    private static String getChoiceName(int choice) {
        switch (choice) {
            case 1: return "Add a new student";
            case 2: return "Add grade to student";
            case 3: return "View student information";
            case 4: return "View all students";
            case 5: return "Delete student";
            case 6: return "View choice history";
            case 7: return "Exit";
            default: return "Unknown";
        }
    }
}
