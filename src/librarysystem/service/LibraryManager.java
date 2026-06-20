package librarysystem.service;

import librarysystem.entity.Book;
import librarysystem.entity.Loan;
import librarysystem.entity.LoanCopy;
import librarysystem.entity.Member;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LibraryManager {
    private ConcurrentHashMap<String, Queue<Book>> bookMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Set<Loan>> memberMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, LoanCopy> loanMap = new ConcurrentHashMap<>();
    private Set<String> members = new HashSet<>();
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addBook(Book book) {
        var queue = bookMap.getOrDefault(book.getBookId(), new LinkedList<>());
        queue.add(book);
        bookMap.put(book.getBookId(), queue);
        System.out.println("Book Added : " + bookMap);
    }

    public void addMember(Member member) {
        members.add(member.getMemberId());
        System.out.println("Member added : " + members);
    }

    public Loan borrowBook(String memberId, String bookId) {
        if (!members.contains(memberId)) {
            System.out.println("Member is not authorized " + memberId);
            //exception will be thrown
            return null;
        }
        if (memberMap.containsKey(memberId) && memberMap.get(memberId).size() > 5) {
            System.out.println("Member have already 5 books issued : " + memberId);
            //exception will be thrown
            return null;
        }
        if (!bookMap.containsKey(bookId)) {
            System.out.println("Book Is Not Available in Library" + bookId);
            //exception will be thrown
            return null;
        }
        Book book = bookMap.get(bookId).poll();
        if (book == null) {
            System.out.println("Book Is Not Available in Library" + bookId);
            //exception will be thrown
            return null;
        }
        Loan loan = new Loan("Loan-" + atomicInteger.getAndIncrement(), bookId, memberId, new Date());
        System.out.println("Loan : " + loan);
        var set = memberMap.getOrDefault(memberId, new HashSet<>());
        set.add(loan);
        memberMap.put(memberId, set);
        loanMap.put(loan.getLoanId(), new LoanCopy(book, loan));
        System.out.println("Member Map : " + memberMap);
        System.out.println("Loan Map : " + loanMap);
        return loan;
    }

    public void returnBook(String loanId) {
        var loanCopy = loanMap.remove(loanId);
        if (loanCopy == null || loanCopy.getBook() == null) {
            System.out.println("Invalid Loan Id : " + loanId);
            return;
        }
        var book = loanCopy.getBook();
        var loan = loanCopy.getLoan();
        bookMap.get(book.getBookId()).add(book);
        var memberId = loan.getMemberId();
        memberMap.get(memberId).remove(loan);
        System.out.println("Member Map : " + memberMap);
        System.out.println("Loan Map : " + loanMap);
    }
}
