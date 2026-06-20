package vendingmachine.service;

import vendingmachine.entity.Product;
import vendingmachine.entity.PurchaseResult;

public interface VendingMachine {
    void addProduct(Product p, Integer quantity);

    void selectProduct(String productId);

    void insertMoney(Double money);

    PurchaseResult purchase();

    double cancel();
}
