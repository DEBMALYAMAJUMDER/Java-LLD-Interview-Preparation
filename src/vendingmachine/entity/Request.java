package vendingmachine.entity;

import java.util.Objects;

public class Request {
    private String product;
    private Double amount;
    private ProductState state;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Request{" +
                "product='" + product + '\'' +
                ", amount=" + amount +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Request request)) return false;
        return Objects.equals(product, request.product) && Objects.equals(amount, request.amount) && state == request.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount, state);
    }
}
