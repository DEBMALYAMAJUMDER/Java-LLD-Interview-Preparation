package vendingmachine.entity;

public class Product {
    private String productCode;
    private String productName;
    private Double price;

    public Product(String productCode, String productName, Double price) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
