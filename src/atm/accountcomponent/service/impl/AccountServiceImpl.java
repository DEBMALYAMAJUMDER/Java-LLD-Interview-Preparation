package atm.accountcomponent.service.impl;

import atm.accountcomponent.entity.BankAccount;
import atm.accountcomponent.repository.AccountRepository;
import atm.accountcomponent.service.AccountService;
import atm.cardcomponent.entity.Card;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(BankAccount bankAccount) {
        accountRepository.addAccount(bankAccount);
    }

    @Override
    public void addCard(Card card, String accountNo) {
        accountRepository.addCard(card, accountNo);
    }

    @Override
    public BankAccount fetchAccountDetails(String cardNo) {
        return accountRepository.fetchAccountDetails(cardNo);
    }

    @Override
    public void depositMoney(String accountNo, BigDecimal amount) {
        accountRepository.depositMoney(accountNo, amount);
    }
}
