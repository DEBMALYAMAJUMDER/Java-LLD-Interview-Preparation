package vendingmachine.repository.impl;

import vendingmachine.exception.InsufficientProductException;
import vendingmachine.exception.ProductNotFoundException;
import vendingmachine.repository.InMemoryRepository;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository implements InMemoryRepository<String> {
    HashMap<String, Double> priceMap = new HashMap<>();
    ConcurrentHashMap<String, Integer> quantityMap = new ConcurrentHashMap<>();

    @Override
    public void addProductPrice(String id, Double price) {
        priceMap.put(id, price);
        System.out.println("Price : " + price + " Id : " + id);
    }

    @Override
    public void addProductQuantity(String id, Integer quantity) {
        quantityMap.put(id, quantity);
        System.out.println("Quantity : " + quantity + " Id : " + id);
     }

    @Override
    public Integer deductProduct(String id, Integer count) {
        if (!quantityMap.containsKey(id)) {
            throw new ProductNotFoundException("Product is Not Available");
        }
        quantityMap.compute(id, (key, value) -> {
            if (value < count) {
                throw new InsufficientProductException("Available Product is less than Demand");
            }
            return value - count;
        });
        return quantityMap.get(id);
    }

    @Override
    public boolean isBalanceSufficient(String id, double amount) {
        return !priceMap.isEmpty() && priceMap.containsKey(id) && priceMap.get(id) <= amount;
    }

    @Override
    public double getPrice(String productId) {
        return priceMap.get(productId);
    }
}
