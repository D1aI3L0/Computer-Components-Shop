package org.example.models;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String manufacturer;
    private String type;
    private double price;
    private int processorId;
    private int motherboardId;
    private int graphicCardId;

    public Product() {}

    public Product(String name, String manufacturer, String type, double price, int processorId, int motherboardId, int graphicCardId) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.processorId = processorId;
        this.motherboardId = motherboardId;
        this.graphicCardId = graphicCardId;
    }

    public Product(int id, String name, String manufacturer, String type, double price, int processorId, int motherboardId, int graphicCardId) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.processorId = processorId;
        this.motherboardId = motherboardId;
        this.graphicCardId = graphicCardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    public int getGraphicCardId() {
        return graphicCardId;
    }

    public void setGraphicCardId(int graphicCardId) {
        this.graphicCardId = graphicCardId;
    }
}