import java.util.Scanner;

public class GradeEvaluation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       GRADE EVALUATION");
        System.out.println("================================");

        System.out.print("Enter student marks (0-100): ");
        int marks = sc.nextInt();

        System.out.println("--------------------------------");

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks. Please enter marks between 0 and 100.");
        }
        else if (marks >= 90) {
            System.out.println("Grade: A");
            System.out.println("Performance: Excellent");
        }
        else if (marks >= 75) {
            System.out.println("Grade: B");
            System.out.println("Performance: Very Good");
        }
        else if (marks >= 50) {
            System.out.println("Grade: C");
            System.out.println("Performance: Good");
        }
        else {
            System.out.println("Grade: Fail");
            System.out.println("Performance: Needs Improvement");
        }

        System.out.println("--------------------------------");
        System.out.println("       Thank you!");
        System.out.println("================================");

        sc.close();
    }
}