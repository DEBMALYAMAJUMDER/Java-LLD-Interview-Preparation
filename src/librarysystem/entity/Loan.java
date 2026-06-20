package librarysystem.entity;

import java.util.Date;

public class Loan {
    private String loanId;
    private String bookId;
    private String memberId;
    private Date timeStamp;

    public Loan(String loanId, String bookId, String memberId, Date timeStamp) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.timeStamp = timeStamp;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
