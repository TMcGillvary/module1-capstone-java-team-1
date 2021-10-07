package com.techelevator.inventory;

import java.math.BigDecimal;


public class Candy extends VendingMachineItem {

    //Constructor
    public Candy(String name, BigDecimal cost) {
        super(name, cost);
    }

    // dispensed message
    @Override
    public String getDispensedMessage() {
        return "Munch Munch, Yum!";
    }
}
