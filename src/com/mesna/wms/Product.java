package com.mesna.wms;

public class Product {

    private String name;
    private Integer quantity;
    private Integer destination;

    public Product(String name, Integer quantity, Integer destination){
        this.name = name;
        this.quantity = quantity;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getDestination() {
        return destination;
    }
}
