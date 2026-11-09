package library.service;

import library.dao.BookDAO;
import library.dao.LoanDAO;
import library.model.Book;
import library.model.Loan;

import java.sql.Date;
import java.time.LocalDate;

public class LoanService {

    private final LoanDAO loanDAO = new LoanDAO();
    private final BookDAO bookDAO = new BookDAO();

    // Empresta um livro para um usuário
    public boolean borrowBook(int userId, int bookId, int loanDays) {
        Book book = bookDAO.findById(bookId);

        if (book == null) {
            System.out.println("Error!! Book not found.");
            return false;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("No available copies for this book.");
            return false;
        }

        Loan loan = new Loan(
                0,
                userId,
                bookId,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(loanDays)),
                null
        );
        loanDAO.save(loan);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDAO.update(book);

        System.out.println("Loan successful!");
        return true;
    }

    // Devolve um livro
    public boolean returnBook(int loanId) {
        Loan loan = loanDAO.findById(loanId);

        if (loan == null) {
            System.out.println("Loan not found.");
            return false;
        }

        if (loan.getReturnDate() != null) {
            System.out.println("This book is already returned.");
            return false;
        }

        loan.setReturnDate(Date.valueOf(LocalDate.now()));
        loanDAO.update(loan);

        Book book = bookDAO.findById(loan.getBookId());
        if (book != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookDAO.update(book);
        }

        System.out.println("Book  successfully returned");
        return true;
    }
}
