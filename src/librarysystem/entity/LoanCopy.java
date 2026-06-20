package librarysystem.entity;

import java.util.Objects;

public class LoanCopy {
    private Book book;
    private Loan loan;

    public LoanCopy(Book book, Loan loan) {
        this.book = book;
        this.loan = loan;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LoanCopy loanCopy)) return false;
        return Objects.equals(book, loanCopy.book) && Objects.equals(loan, loanCopy.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, loan);
    }
}
