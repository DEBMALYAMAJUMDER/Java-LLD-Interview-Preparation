package atm;

import atm.accountcomponent.entity.BankAccount;
import atm.accountcomponent.service.AccountService;
import atm.accountcomponent.service.impl.AccountServiceImpl;
import atm.cardcomponent.entity.Card;
import atm.cardcomponent.service.CardService;
import atm.cardcomponent.service.impl.CardServiceImpl;
import atm.cashcomponent.entity.CashWithdrawal;
import atm.cashcomponent.entity.TransactionState;
import atm.cashcomponent.repository.AtmRepository;
import atm.cashcomponent.service.CashWithdrawalService;
import atm.cashcomponent.service.impl.GreedyCashWithdrawalServiceImpl;
import atm.exception.CardAuthenticationException;

import java.math.BigDecimal;
import java.util.List;

public class AtmServiceImpl extends AtmService {
    private CashWithdrawalService cashWithdrawalService = new GreedyCashWithdrawalServiceImpl();
    private AtmRepository atmRepository;
    private CardService cardService;

    public AtmServiceImpl(AccountService accountService, CardService cardService, AtmRepository atmRepository) {
        super(accountService);
        this.atmRepository = atmRepository;
        this.cardService = cardService;
    }

    @Override
    public void updatedCash(List<Integer> notes, CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.WITHDRAWAL) {
            return;
        }
        atmRepository.updateCash(notes.stream().map(elm -> -elm).toList());
        cashWithdrawal.setState(TransactionState.SUCCESSFUL);
    }

    @Override
    public void fetchAvailableNotes() {
        atmRepository.getCashMap().forEach((key, value) -> System.out.println("No of Notes of : " + key + " -> " + value));
    }

    @Override
    public void authenticate(Card card, String pin, CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.INITIATE) {
            return;
        }
        if (cardService.authenticate(card, pin)) {
            cashWithdrawal.setState(TransactionState.AUTHENTICATE);
        } else {
            cashWithdrawal.setState(TransactionState.AUTHENTICATION_FAILED);
            throw new CardAuthenticationException("Card is Not Authorized");
        }
    }

    @Override
    public void atmCashWithdrawal(BigDecimal amount, BankAccount bankAccount, CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.BALANCE_FETCH) {
            return;
        }
        var notes = atmRepository.getCashMap();
        cashWithdrawalService.withdrawal(amount, bankAccount, cashWithdrawal, notes);
        accountService.addAccount(bankAccount);
    }
}
