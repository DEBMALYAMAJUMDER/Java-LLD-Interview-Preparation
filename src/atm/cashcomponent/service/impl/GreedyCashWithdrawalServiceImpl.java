package atm.cashcomponent.service.impl;

import atm.accountcomponent.entity.BankAccount;
import atm.cashcomponent.entity.CashWithdrawal;
import atm.cashcomponent.entity.TransactionState;
import atm.cashcomponent.service.CashWithdrawalService;
import atm.exception.UnableToDispenseCashException;
import vendingmachine.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GreedyCashWithdrawalServiceImpl implements CashWithdrawalService {

    @Override
    public void withdrawal(BigDecimal amount, BankAccount bankDetails, CashWithdrawal cashWithdrawal, ConcurrentHashMap<Integer, Integer> cashMap) {
        BigDecimal avlBal = bankDetails.getBalance().subtract(amount);
        System.out.println("AvlBal : " + avlBal);
        if (bankDetails.getBalance().compareTo(amount) >= 0) {
            var noteFiveHundred = Math.min(amount.divide(new BigDecimal(500), RoundingMode.FLOOR).intValue(), cashMap.getOrDefault(500, 0));
            amount = amount.subtract(new BigDecimal(500 * noteFiveHundred));
            System.out.println("Amount : " + amount);
            var noteTwoHundred = Math.min(amount.divide(new BigDecimal(200), RoundingMode.FLOOR).intValue(), cashMap.getOrDefault(200, 0));
            amount = amount.subtract(new BigDecimal(200 * noteTwoHundred));
            System.out.println("Amount : " + amount);
            var noteOneHundred = Math.min(amount.divide(new BigDecimal(100), RoundingMode.FLOOR).intValue(), cashMap.getOrDefault(100, 0));
            amount = amount.subtract(new BigDecimal(100 * noteOneHundred));
            System.out.println("Amount : " + amount);
            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                cashWithdrawal.setState(TransactionState.NOTES_UNAVAILABLE);
                throw new UnableToDispenseCashException("Required Notes are not available");
            } else {
                cashWithdrawal.setNotes(List.of(noteFiveHundred, noteTwoHundred, noteOneHundred));
                cashWithdrawal.setBalance(avlBal);
                bankDetails.setBalance(avlBal);
                cashWithdrawal.setState(TransactionState.WITHDRAWAL);
            }
        } else {
            cashWithdrawal.setState(TransactionState.INSUFFICIENT_BALANCE);
            throw new InsufficientBalanceException("Balance is insufficient");
        }
    }
}
