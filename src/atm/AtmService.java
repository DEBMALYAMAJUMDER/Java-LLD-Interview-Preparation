package atm;

import atm.accountcomponent.entity.BankAccount;
import atm.accountcomponent.service.AccountService;
import atm.cardcomponent.entity.Card;
import atm.cashcomponent.entity.CashWithdrawal;
import atm.cashcomponent.entity.TransactionState;
import atm.exception.BankAccountNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public abstract class AtmService {
    protected AccountService accountService;

    public AtmService(AccountService accountService) {
        this.accountService = accountService;
    }

    public final void cashWithdrawal(BigDecimal amount, Card card, String pin) {
        CashWithdrawal cashWithdrawal = new CashWithdrawal();
        /*
        Initiate Transaction
        */
        cashWithdrawal.setCard(card);
        cashWithdrawal.setState(TransactionState.INITIATE);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Authentication
        */
        authenticate(card, pin, cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Validate Amount
        */
        validateAmount(amount, cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Fetch Account Details
        */
        BankAccount bankAccount = new BankAccount();
        fetchBankDetails(card.getCardNo(), bankAccount, cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Cash Withdrawal
        */
        atmCashWithdrawal(amount, bankAccount, cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Update Cash
         */
        updatedCash(cashWithdrawal.getNotes(), cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
        /*
        Ejected
         */
        ejected(cashWithdrawal);
        System.out.println("CashWithdrawal : " + cashWithdrawal);
    }

    public abstract void fetchAvailableNotes();

    public abstract void updatedCash(List<Integer> notes, CashWithdrawal cashWithdrawal);

    public abstract void authenticate(Card card, String pin, CashWithdrawal cashWithdrawal);

    public abstract void atmCashWithdrawal(BigDecimal amount, BankAccount bankAccount, CashWithdrawal cashWithdrawal);

    public void fetchBankDetails(String cardNo, BankAccount bankAccount, CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.AMOUNT_VALIDATED) {
            return;
        }
        BankAccount account = accountService.fetchAccountDetails(cardNo);
        System.out.println("Bank Account : " + account);
        if (account != null) {
            //Later will Use Model Mapper
            bankAccount.setBalance(account.getBalance());
            bankAccount.setAccntNo(account.getAccntNo());
            bankAccount.setCards(account.getCards());
            bankAccount.setCustName(account.getCustName());
            cashWithdrawal.setAccountNo(bankAccount);
            cashWithdrawal.setState(TransactionState.BALANCE_FETCH);
        } else {
            cashWithdrawal.setState(TransactionState.UNABLE_TO_FIND_ACCOUNT);
            throw new BankAccountNotFoundException("Bank Account Not Found");
        }
    }

    public void ejected(CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.SUCCESSFUL) {
            return;
        }
        cashWithdrawal.setState(TransactionState.EJECTED);
    }

    public void createBankAccount(BankAccount bankAccount) {
        accountService.addAccount(bankAccount);
    }

    public void linkCardToBankAccount(Card card, String acctNo) {
        accountService.addCard(card, acctNo);
    }

    private void validateAmount(BigDecimal amount, CashWithdrawal cashWithdrawal) {
        if (cashWithdrawal.getState() != TransactionState.AUTHENTICATE) {
            return;
        }
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            cashWithdrawal.setState(TransactionState.AMOUNT_VALIDATED);
        } else {
            cashWithdrawal.setState(TransactionState.INVALID_AMOUNT);
            throw new IllegalArgumentException("Amount is Illegal");
        }
    }

}
