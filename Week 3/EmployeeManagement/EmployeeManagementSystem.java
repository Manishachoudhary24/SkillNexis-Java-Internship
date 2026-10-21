
package EmployeeManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {

    private static final String FILE_NAME =
            "EmployeeManagement/employees.txt";

    private static final ArrayList<Employee> employees =
            new ArrayList<>();

    static class Employee {
        int id;
        String name;
        String department;
        double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "ID: " + id
                    + " | Name: " + name
                    + " | Department: " + department
                    + " | Salary: " + String.format("%.2f", salary);
        }

        String toFileFormat() {
            return id + "|" + name + "|" + department + "|" + salary;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadEmployees();

        boolean running = true;

        while (running) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Save Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        addEmployee(sc);
                        break;

                    case 2:
                        displayEmployees();
                        break;

                    case 3:
                        searchEmployee(sc);
                        break;

                    case 4:
                        saveEmployees();
                        break;

                    case 5:
                        saveEmployees();
                        running = false;
                        System.out.println("Program closed successfully.");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice! Enter a number from 1 to 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        sc.close();
    }

    private static void addEmployee(Scanner sc) {
        try {
            System.out.print("Enter employee ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            if (id <= 0) {
                System.out.println("Employee ID must be positive.");
                return;
            }

            for (Employee emp : employees) {
                if (emp.id == id) {
                    System.out.println("Employee ID already exists.");
                    return;
                }
            }

            System.out.print("Enter employee name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter department: ");
            String department = sc.nextLine().trim();

            if (name.isEmpty() || department.isEmpty()
                    || name.contains("|") || department.contains("|")) {
                System.out.println(
                        "Name and department cannot be empty or contain |.");
                return;
            }

            System.out.print("Enter salary: ");
            double salary = Double.parseDouble(sc.nextLine().trim());

            if (!Double.isFinite(salary) || salary < 0) {
                System.out.println(
                        "Salary must be a valid non-negative number.");
                return;
            }

            Employee employee =
                    new Employee(id, name, department, salary);

            employees.add(employee);

            System.out.println("Employee added successfully.");
            saveEmployees();

        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid ID or salary! Please enter valid numbers.");
        }
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("\n===== Employee Records =====");

        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void searchEmployee(Scanner sc) {
        try {
            System.out.print("Enter employee ID to search: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            for (Employee emp : employees) {
                if (emp.id == id) {
                    System.out.println("Employee found:");
                    System.out.println(emp);
                    return;
                }
            }

            System.out.println("Employee not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
        }
    }

    private static void saveEmployees() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Employee emp : employees) {
                writer.write(emp.toFileFormat());
                writer.newLine();
            }

            System.out.println("Employee records saved successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving employee records: " + e.getMessage());
        }
    }

    private static void loadEmployees() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println(
                    "No saved employee records. Starting with an empty list.");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split("\\|", -1);

                    if (data.length != 4) {
                        throw new IllegalArgumentException(
                                "Invalid record format");
                    }

                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String department = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());

                    if (id <= 0 || name.isEmpty() || department.isEmpty()
                            || name.contains("|") || department.contains("|")
                            || !Double.isFinite(salary) || salary < 0) {
                        throw new IllegalArgumentException(
                                "Invalid employee data");
                    }

                    boolean duplicate = false;

                    for (Employee emp : employees) {
                        if (emp.id == id) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (!duplicate) {
                        employees.add(
                                new Employee(id, name, department, salary));
                    } else {
                        System.out.println(
                                "Skipping duplicate employee ID: " + id);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Skipping invalid record: " + line);
                }
            }

            System.out.println(
                    "Employee records loaded: " + employees.size());

        } catch (IOException e) {
            System.out.println(
                    "Error loading employee records: " + e.getMessage());
        }
    }
}
