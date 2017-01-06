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

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

}
