package com.techelevator.inventory;

import java.math.BigDecimal;

public class Chips extends VendingMachineItem {

    //Constructor
    public Chips(String name, BigDecimal cost) {
        super(name, cost);
    }

    // dispensed message
    @Override
    public String getDispensedMessage() {
        return "Crunch Crunch, Yum!";
    }

}
