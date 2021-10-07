package com.techelevator.inventory;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem{

    //Constructor
    public Gum(String name, BigDecimal cost) {
        super(name, cost);
    }

    // dispensed message
    @Override
    public String getDispensedMessage() {
        return "Chew Chew, Yum!";
    }

}
