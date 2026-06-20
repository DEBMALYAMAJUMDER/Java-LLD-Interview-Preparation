package librarysystem.service;

import librarysystem.entity.Book;
import librarysystem.entity.Loan;
import librarysystem.entity.Member;

public interface LibraryService {
    void returnBook(String loanId);

    Loan borrowBook(String memberId, String bookId);

    void addMember(String memberId, String name);

    void addBook(String bookId, String title, String author);
}
