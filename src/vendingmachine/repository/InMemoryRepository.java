package vendingmachine.repository;

public interface InMemoryRepository<T> {
    void addProductPrice(T id, Double price);

    void addProductQuantity(T id, Integer quantity);

    Integer deductProduct(T id, Integer count);

    boolean isBalanceSufficient(String id, double amount);
    double getPrice(String productId);
}
