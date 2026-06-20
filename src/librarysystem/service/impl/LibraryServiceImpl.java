package librarysystem.service.impl;

import librarysystem.entity.Book;
import librarysystem.entity.Loan;
import librarysystem.entity.Member;
import librarysystem.service.LibraryManager;
import librarysystem.service.LibraryService;

public class LibraryServiceImpl implements LibraryService {
    private LibraryManager libraryManager = new LibraryManager();

    @Override
    public void returnBook(String loanId) {
        libraryManager.returnBook(loanId);
    }

    @Override
    public Loan borrowBook(String memberId, String bookId) {
        return libraryManager.borrowBook(memberId, bookId);
    }

    @Override
    public void addMember(String memberId, String name) {
        libraryManager.addMember(new Member(memberId, name));
    }

    @Override
    public void addBook(String bookId, String title, String author) {
        libraryManager.addBook(new Book(bookId, title, author));
    }
}
