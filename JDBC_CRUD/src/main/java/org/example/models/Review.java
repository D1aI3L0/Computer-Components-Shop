package org.example.models;

public class Review {
    private int id;
    private int clientId;
    private int productId;
    private String review;

    public Review() {
    }

    public Review(int clientId, int productId, String review) {
        this.clientId = clientId;
        this.productId = productId;
        this.review = review;
    }

    public Review(int id, int clientId, int productId, String review) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
