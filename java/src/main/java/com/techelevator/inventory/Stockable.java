package com.techelevator.inventory;

public interface Stockable {

    int getStockLevel();

    void subtract1FromInventory();
}
