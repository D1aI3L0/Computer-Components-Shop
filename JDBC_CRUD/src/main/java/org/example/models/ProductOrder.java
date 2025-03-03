package org.example.models;

public class ProductOrder {
    private int id;
    private int orderId;
    private int productId;

    public ProductOrder(int id, int orderId, int productId) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
    }

    public ProductOrder(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
