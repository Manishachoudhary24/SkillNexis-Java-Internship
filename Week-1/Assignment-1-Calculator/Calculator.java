import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       BASIC CALCULATOR");
        System.out.println("================================");

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("\nSelect an operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        double result;

        System.out.println("\n--------------------------------");

        switch (choice) {

            case 1:
                result = num1 + num2;
                System.out.println("Operation: Addition");
                System.out.println("Result = " + result);
                break;

            case 2:
                result = num1 - num2;
                System.out.println("Operation: Subtraction");
                System.out.println("Result = " + result);
                break;

            case 3:
                result = num1 * num2;
                System.out.println("Operation: Multiplication");
                System.out.println("Result = " + result);
                break;

            case 4:
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                } else {
                    result = num1 / num2;
                    System.out.println("Operation: Division");
                    System.out.println("Result = " + result);
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("--------------------------------");
        System.out.println("       Thank you!");
        System.out.println("================================");

        sc.close();
    }
}
