
import java.util.ArrayList;
import java.util.Scanner;

public class StudentMarks {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Double> marks = new ArrayList<>();

        try {
            System.out.print("Enter number of students: ");
            int n = Integer.parseInt(sc.nextLine().trim());

            if (n <= 0) {
                System.out.println("Number of students must be positive.");
                return;
            }

            for (int i = 1; i <= n; i++) {
                System.out.print("Enter marks for student " + i + ": ");

                double mark = Double.parseDouble(sc.nextLine().trim());

                if (mark < 0 || mark > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    i--;
                    continue;
                }

                marks.add(mark);
            }

            double highest = marks.get(0);
            double lowest = marks.get(0);

            for (double mark : marks) {
                if (mark > highest) {
                    highest = mark;
                }

                if (mark < lowest) {
                    lowest = mark;
                }
            }

            System.out.println("\n===== Student Marks Record =====");
            System.out.println("All Marks: " + marks);
            System.out.println("Highest Marks: " + highest);
            System.out.println("Lowest Marks: " + lowest);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values.");
        } finally {
            sc.close();
        }
    }
}
