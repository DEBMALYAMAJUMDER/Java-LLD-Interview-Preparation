package atm.accountcomponent.repository;

import atm.accountcomponent.entity.BankAccount;
import atm.cardcomponent.entity.Card;
import atm.cardcomponent.repository.CardRepository;
import atm.cardcomponent.service.CardService;
import atm.cardcomponent.service.impl.CardServiceImpl;
import atm.exception.BankAccountNotFoundException;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {
    private ConcurrentHashMap<String, BankAccount> acctMap = new ConcurrentHashMap<>();
    private CardService cardService;

    public AccountRepository(CardService cardService) {
        this.cardService = cardService;
    }

    public void addAccount(BankAccount bankAccount) {
        acctMap.put(bankAccount.getAccntNo(), bankAccount);
        System.out.println("Adding Bank Account" + bankAccount);
    }

    public void addCard(Card card, String accountNo) {
        if (!acctMap.containsKey(accountNo)) {
            throw new BankAccountNotFoundException("Please Provide a valid Bank Account");
        }
        BankAccount bankAccount = acctMap.get(accountNo);
        cardService.saveCard(card);
        bankAccount.addCard(card);
        cardService.generateCardAccountLink(card.getCardNo(), accountNo);
    }

    public BankAccount fetchAccountDetails(String cardNo) {
        String accountNo = cardService.fetchAccountNo(cardNo);
        return acctMap.get(accountNo);
    }

    public void depositMoney(String accountNo, BigDecimal amount) {
        if (!acctMap.containsKey(accountNo)) {
            throw new BankAccountNotFoundException("Please Provide a valid Bank Account");
        }
        BankAccount bankAccount = acctMap.get(accountNo);
        BigDecimal balance = bankAccount.getBalance();
        bankAccount.setBalance(balance.add(amount));
        System.out.println("Deposit Amount " + amount);
    }
}
