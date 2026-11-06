package library;

import library.dao.BookDAO;
import library.dao.UserDAO;
import library.model.Book;
import library.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();

        Book book = new Book(0, "O Hobbit", "J.R.R. Tolkien", "Fantasia", 5, 5);
        bookDAO.save(book);

        List<Book> books = bookDAO.findAll();
        for (Book b : books) {
            System.out.println(b);
        }

        // teste do UserDAO
        UserDAO userDAO = new UserDAO();

        List<User> users = userDAO.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
