import java.util.ArrayList;
import java.util.Scanner;

// Book class - Encapsulation
class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayBookInfo() {
        System.out.println("Book ID     : " + bookId);
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author);
        System.out.println("Availability: " +
                (available ? "Available" : "Borrowed"));
    }
}


// User class - Abstraction
abstract class User {

    protected int userId;
    protected String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    abstract void displayUserInfo();
}


// StudentUser inherits User - Inheritance
class StudentUser extends User {

    public StudentUser(int userId, String name) {
        super(userId, name);
    }

    @Override
    void displayUserInfo() {
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Type    : Student");
    }
}


// Library class
class Library {

    private ArrayList<Book> books = new ArrayList<>();

    // Add Book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Borrow Book
    public void borrowBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is already borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Return Book
    public void returnBook(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Display all books
    public void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\n========== BOOK LIST ==========");

        for (Book book : books) {
            System.out.println();
            book.displayBookInfo();
        }

        System.out.println("===============================");
    }
}


// Main class
public class LibraryManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Library library = new Library();

        // Creating a user
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter User Name: ");
        String userName = sc.nextLine();

        StudentUser user = new StudentUser(userId, userName);

        System.out.println("\n========== USER INFORMATION ==========");
        user.displayUserInfo();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    Book book = new Book(bookId, title, author);

                    library.addBook(book);
                    break;

                case 2:

                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();

                    library.borrowBook(borrowId);
                    break;

                case 3:

                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();

                    library.returnBook(returnId);
                    break;

                case 4:

                    library.displayBooks();
                    break;

                case 5:

                    System.out.println(
                        "Thank you for using the Library Management System."
                    );
                    break;

                default:

                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
