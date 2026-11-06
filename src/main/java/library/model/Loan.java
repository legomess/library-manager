package library.model;

import java.sql.Date;

public class Loan {

    private int id;
    private int userId;
    private int bookId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public Loan(){
    }

    public Loan(int id, int userId, int bookId, Date loanDate, Date dueDate, Date returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public Date getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{id = " + id + ", userId=" + userId + ", bookId= " + bookId + ", loanDate= " + loanDate + ", dueDate= " + dueDate + ", returnDate= " + returnDate + "}";
    }
}
