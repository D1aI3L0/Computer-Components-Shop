package org.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

    private int id;
    private double totalPrice;
    private String orderDate;
    private String status;
    private String paymentMethod;
    private int clientId;

    public Order() {
    }

    public Order(double totalPrice, String orderDate, String status, String paymentMethod, int clientId) {
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.clientId = clientId;
    }

    public Order(int id, double totalPrice, String orderDate, String status, String paymentMethod, int clientId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String  getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
