package vendingmachine.entity;

import java.time.LocalDateTime;

public class PurchaseResult {
    private String productId;
    private Double returnAmt;
    private ProductState state;
    private LocalDateTime timeStamp;

    public PurchaseResult(String productId, Double returnAmt, ProductState state, LocalDateTime timeStamp) {
        this.productId = productId;
        this.returnAmt = returnAmt;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "PurchaseResult{" +
                "productId='" + productId + '\'' +
                ", returnAmt=" + returnAmt +
                ", state=" + state +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
