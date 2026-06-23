package atm.cashcomponent.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AtmRepository {
    private final ConcurrentHashMap<Integer, Integer> cashMap = new ConcurrentHashMap<>();

    public void updateCash(List<Integer> cash) {
        cashMap.put(500, cashMap.getOrDefault(500, 0) + cash.get(0));
        cashMap.put(200, cashMap.getOrDefault(200, 0) + cash.get(1));
        cashMap.put(100, cashMap.getOrDefault(100, 0) + cash.get(2));
    }

    public ConcurrentHashMap<Integer, Integer> getCashMap() {
        return cashMap;
    }

}
