package library;

import library.service.LoanService;
import library.dao.BookDAO;
import library.dao.UserDAO;
import library.model.Book;
import library.model.User;

import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        LoanService loanService = new LoanService();

        boolean running = true;

        while (running) {
            System.out.println("======= Library Management System =====");
            System.out.println(" 1- Borrow a book");
            System.out.println(" 2- Return a book");
            System.out.println(" 3- List Books");
            System.out.println(" 4- Register a new user");
            System.out.println(" 5- Exit");
            System.out.println("Choose an option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter USER ID: ");
                    int userID = scanner.nextInt();

                    System.out.println("Enter BOOK ID: ");
                    int bookID = scanner.nextInt();

                    System.out.println("Enter Loan days: ");
                    int loanDays = scanner.nextInt();
                    loanService.borrowBook(userID, bookID, loanDays);
                    break;

                case 2:
                    System.out.println("Enter Loan ID: ");
                    int loanID = scanner.nextInt();
                    loanService.returnBook(loanID);
                    break;

                case 3:
                    List<Book>books = bookDAO.findAll();
                    System.out.println("\n--- Book List ---");
                    for (Book book : books) {
                        System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor() + " (Available: " + book.getAvailableCopies() + "/" + book.getTotalCopies() + ")");
                    }

                    break;

                case 4:
                    System.out.println("Enter name: ");
                    String name = scanner.next();

                    System.out.println("Enter email: ");
                    String email = scanner.next();

                    System.out.println("Enter password: ");
                    String password = scanner.next();

                    System.out.println("Enter role (admin/user): ");
                    String role = scanner.next();

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setPassword(password);
                    newUser.setRole(role);

                    userDAO.save(newUser);
                    System.out.println("User registered successfully!");
                    break;

                    default:
                        System.out.println("Invalid input");
                    }
            }

        scanner.close();
        }
    }
