package atm.accountcomponent.entity;

import atm.cardcomponent.entity.Card;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accntNo;
    private String custName;
    private BigDecimal balance;
    private List<Card> cards;

    public BankAccount() {
    }

    public BankAccount(String accntNo, String custName, BigDecimal balance) {
        this.accntNo = accntNo;
        this.custName = custName;
        this.balance = balance;
        cards = new ArrayList<>();
    }

    public String getAccntNo() {
        return accntNo;
    }

    public void setAccntNo(String accntNo) {
        this.accntNo = accntNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accntNo='" + accntNo + '\'' +
                ", custName='" + custName + '\'' +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}
