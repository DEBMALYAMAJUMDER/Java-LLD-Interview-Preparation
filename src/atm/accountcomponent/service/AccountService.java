package atm.accountcomponent.service;

import atm.accountcomponent.entity.BankAccount;
import atm.cardcomponent.entity.Card;

import java.math.BigDecimal;

public interface AccountService {
    void addAccount(BankAccount bankAccount);

    void addCard(Card card, String accountNo);

    BankAccount fetchAccountDetails(String cardNo);

    void depositMoney(String accountNo, BigDecimal amount);
}
