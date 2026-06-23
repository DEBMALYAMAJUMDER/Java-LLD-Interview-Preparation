package atm;

import atm.accountcomponent.entity.BankAccount;
import atm.accountcomponent.repository.AccountRepository;
import atm.accountcomponent.service.AccountService;
import atm.accountcomponent.service.impl.AccountServiceImpl;
import atm.cardcomponent.entity.Card;
import atm.cardcomponent.repository.CardRepository;
import atm.cardcomponent.service.CardService;
import atm.cardcomponent.service.impl.CardServiceImpl;
import atm.cashcomponent.entity.CashWithdrawal;
import atm.cashcomponent.repository.AtmRepository;

import java.math.BigDecimal;
import java.util.List;

public class AtmMain {
    public static void main(String[] args) {
        CardRepository cardRepository = new CardRepository();
        CardService cardService = new CardServiceImpl(cardRepository);
        AccountRepository accountRepository = new AccountRepository(cardService);
        AccountService accountService = new AccountServiceImpl(accountRepository);
        AtmRepository atmRepository = new AtmRepository();
        AtmService atmService = new AtmServiceImpl(accountService, cardService, atmRepository);
        atmRepository.updateCash(List.of(2, 3, 2));
        Card card = new Card("card-123", "ACC-123", "Debmalya", "1234", "2028-07");
        BankAccount bankAccount = new BankAccount("ACC-123", "Debmalya", new BigDecimal(1800));
        atmService.createBankAccount(bankAccount);
        atmService.linkCardToBankAccount(card, bankAccount.getAccntNo());
        // Success
        atmService.cashWithdrawal(new BigDecimal(1300), card, "1234");
        atmService.fetchAvailableNotes();

        // wrong pin
        try {
            atmService.cashWithdrawal(new BigDecimal("1000"), card, "9999");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        atmService.fetchAvailableNotes();

        // Insufficient Balance
        try {
            atmService.cashWithdrawal(new BigDecimal("20000"), card, "1234");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        atmService.fetchAvailableNotes();
        //Invalid Amount
        try {
            atmService.cashWithdrawal(new BigDecimal("-100"), card, "1234");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        atmService.fetchAvailableNotes();
        try {
            Card invalidCard = new Card("card-456", "ACC-123", "Debmalya", "1234", "2028-07");
            atmService.cashWithdrawal(new BigDecimal("1000"), invalidCard, "1234");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        atmService.fetchAvailableNotes();
        //Invalid Amount
        try {
            atmService.cashWithdrawal(new BigDecimal("500"), card, "1234");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        atmService.fetchAvailableNotes();
    }
}
