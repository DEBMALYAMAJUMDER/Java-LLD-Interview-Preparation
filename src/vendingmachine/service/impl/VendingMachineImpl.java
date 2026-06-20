package vendingmachine.service.impl;

import vendingmachine.entity.Product;
import vendingmachine.entity.ProductState;
import vendingmachine.entity.PurchaseResult;
import vendingmachine.entity.Request;
import vendingmachine.exception.InsufficientBalanceException;
import vendingmachine.exception.ProductNotFoundException;
import vendingmachine.repository.InMemoryRepository;
import vendingmachine.repository.impl.ProductRepository;
import vendingmachine.service.VendingMachine;

import java.time.LocalDateTime;

public class VendingMachineImpl implements VendingMachine {
    private InMemoryRepository<String> inMemoryRepository = new ProductRepository();
    private Request request = new Request();

    @Override
    public void addProduct(Product p, Integer quantity) {
        inMemoryRepository.addProductPrice(p.getProductCode(), p.getPrice());
        inMemoryRepository.addProductQuantity(p.getProductCode(), quantity);
    }

    @Override
    public void selectProduct(String productId) {
        request.setProduct(productId);
        request.setState(ProductState.PRODUCT_REQUESTED);
        System.out.println("Setting product id in request : " + request.getProduct() + " State : " + request.getState());
    }

    @Override
    public void insertMoney(Double money) {
        Double amount = request.getAmount();
        if (amount == null) {
            amount = 0d;
        }
        request.setAmount(amount + money);
        request.setState(ProductState.PAYMENT_RECEIVED);
        System.out.println("Updated Amount is : " + request.getAmount() + " State : " + request.getState());
    }

    @Override
    public PurchaseResult purchase() {
        if (request.getState() != ProductState.PAYMENT_RECEIVED && request.getState() != ProductState.PRODUCT_REQUESTED) {
            throw new IllegalStateException("State is Illegal " + request.getState().getClass());
        }
        if (request.getProduct() == null) {
            throw new ProductNotFoundException("No Product is selected");
        }

        if (request.getAmount() == null || !inMemoryRepository.isBalanceSufficient(request.getProduct(), request.getAmount())) {
            throw new InsufficientBalanceException("Balance is not available to purchase this product");
        }
        // For now it's hard coded to 1, in future we can put a number in purchase can take
        inMemoryRepository.deductProduct(request.getProduct(), 1);
        double price = inMemoryRepository.getPrice(request.getProduct());
        PurchaseResult result = new PurchaseResult(request.getProduct(), request.getAmount() - price, ProductState.COMPLETED, LocalDateTime.now());
        request = new Request();
        return result;
    }

    @Override
    public double cancel() {
        if (request.getState() == ProductState.COMPLETED) {
            throw new IllegalStateException("State is Illegal");
        }
        double refund = request.getAmount();
        request = new Request();
        return refund;
    }
}
