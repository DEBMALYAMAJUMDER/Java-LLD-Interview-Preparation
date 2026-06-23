package atm.cashcomponent.entity;

import atm.accountcomponent.entity.BankAccount;
import atm.cardcomponent.entity.Card;

import java.math.BigDecimal;
import java.util.List;

public class CashWithdrawal {
    private List<Integer> notes;
    private BigDecimal balance;
    private BankAccount accountNo;
    private Card card;
    private TransactionState state;

    public CashWithdrawal() {
    }

    public CashWithdrawal(List<Integer> notes, BigDecimal balance, BankAccount accountNo, Card card, TransactionState state) {
        this.notes = notes;
        this.balance = balance;
        this.accountNo = accountNo;
        this.card = card;
        this.state = state;
    }

    public List<Integer> getNotes() {
        return notes;
    }

    public void setNotes(List<Integer> notes) {
        this.notes = notes;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankAccount getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(BankAccount accountNo) {
        this.accountNo = accountNo;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public TransactionState getState() {
        return state;
    }

    public void setState(TransactionState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CashWithdrawal{" +
                "notes=" + notes +
                ", balance=" + balance +
                ", accountNo=" + accountNo +
                ", card=" + card +
                ", state=" + state +
                '}';
    }
}
