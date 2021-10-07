package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItem implements Stockable {

    private String name;
    private BigDecimal cost;
    private int stockLevel;

    //Constructor
    public VendingMachineItem(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
        this.stockLevel = 5;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public int getStockLevel() {
        return stockLevel;
    }

}
