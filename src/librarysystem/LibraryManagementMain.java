package librarysystem;

import librarysystem.entity.Loan;
import librarysystem.service.LibraryService;
import librarysystem.service.impl.LibraryServiceImpl;

public class LibraryManagementMain {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImpl();

        libraryService.addBook(
                "B1", "Clean Code", "Robert Martin");

        libraryService.addBook(
                "B2", "Clean Code", "Robert Martin");

        libraryService.addMember(
                "M1", "Debmalya");

        Loan loan =
                libraryService.borrowBook("M1", "B1");
        Loan loan1 =
                libraryService.borrowBook("M2", "B1");

        libraryService.returnBook(loan.getLoanId());
        libraryService.returnBook(loan.getLoanId());
    }
}
