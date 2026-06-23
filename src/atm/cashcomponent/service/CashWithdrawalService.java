package atm.cashcomponent.service;

import atm.accountcomponent.entity.BankAccount;
import atm.cashcomponent.entity.CashWithdrawal;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public interface CashWithdrawalService {
    void withdrawal(BigDecimal amount, BankAccount bankAccount, CashWithdrawal cashWithdrawal, ConcurrentHashMap<Integer,Integer> cashMap);
}
