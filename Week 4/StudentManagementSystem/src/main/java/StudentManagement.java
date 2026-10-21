
import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("PostgreSQL connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed: "
                    + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> {
                    System.out.println("Program closed.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double readMarks() {
        double marks;

        while (true) {
            System.out.print("Enter marks (0-100): ");

            if (!sc.hasNextDouble()) {
                System.out.println("Enter a valid number.");
                sc.next();
                continue;
            }

            marks = sc.nextDouble();

            if (marks >= 0 && marks <= 100) {
                return marks;
            }

            System.out.println("Marks must be between 0 and 100.");
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        sc.nextLine();
        return sc.nextLine().trim();
    }

    private static void addStudent() {
        String name = readText("Enter name: ");
        String course = readText("Enter course: ");
        double marks = readMarks();

        if (name.isBlank() || course.isBlank()) {
            System.out.println("Name and course cannot be empty.");
            return;
        }

        String sql = "INSERT INTO students (name, course, marks) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, marks);
            ps.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding student: "
                    + e.getMessage());
        }
    }

    private static void viewStudents() {
        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("course"),
                                rs.getDouble("marks")
                        )
                );
            }

            if (!found) {
                System.out.println("No student records found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing students: "
                    + e.getMessage());
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = readInt();

        String name = readText("Enter new name: ");
        String course = readText("Enter new course: ");
        double marks = readMarks();

        if (name.isBlank() || course.isBlank()) {
            System.out.println("Name and course cannot be empty.");
            return;
        }

        String sql = "UPDATE students SET name = ?, course = ?, "
                + "marks = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, marks);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0
                    ? "Student updated successfully!"
                    : "Student ID not found.");

        } catch (SQLException e) {
            System.out.println("Error updating student: "
                    + e.getMessage());
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0
                    ? "Student deleted successfully!"
                    : "Student ID not found.");

        } catch (SQLException e) {
            System.out.println("Error deleting student: "
                    + e.getMessage());
        }
    }

    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        int id = readInt();

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            new Student(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("course"),
                                    rs.getDouble("marks")
                            )
                    );
                } else {
                    System.out.println("Student ID not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: "
                    + e.getMessage());
        }
    }
}
