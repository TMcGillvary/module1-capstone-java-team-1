package com.techelevator.inventory;

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

    @Override
    public void subtract1FromInventory() {
        this.stockLevel = this.stockLevel - 1;
    }

    // Dispensed Message goes here? inherited from sub classes
    public abstract String getDispensedMessage();

}
