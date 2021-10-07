package com.techelevator.inventory;

import java.math.BigDecimal;

public class Drink extends VendingMachineItem{

    //Constructor
    public Drink(String name, BigDecimal cost) {
        super(name, cost);
    }

    // dispensed message
    @Override
    public String getDispensedMessage() {
        return "Glug Glug, Yum!";
    }

}
