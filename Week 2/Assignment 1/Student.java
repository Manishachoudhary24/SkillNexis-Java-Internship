import java.util.Scanner;

public class Student {

    // Attributes
    String name;
    int rollNumber;
    String course;
    double marks;

    // Method to display student information
    void displayStudentInfo() {
        System.out.println("\n================================");
        System.out.println("       STUDENT INFORMATION");
        System.out.println("================================");
        System.out.println("Name        : " + name);
        System.out.println("Roll Number : " + rollNumber);
        System.out.println("Course      : " + course);
        System.out.println("Marks       : " + marks);
        System.out.println("================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Creating Student object
        Student student = new Student();

        // Taking input from user
        System.out.print("Enter student name: ");
        student.name = sc.nextLine();

        System.out.print("Enter roll number: ");
        student.rollNumber = sc.nextInt();

        sc.nextLine(); // Clear newline

        System.out.print("Enter course: ");
        student.course = sc.nextLine();

        System.out.print("Enter marks: ");
        student.marks = sc.nextDouble();

        // Display student information
        student.displayStudentInfo();

        sc.close();
    }
}