package com.techelevator;

import java.math.BigDecimal;

public class PiggyBank {

    // instance variables
    private BigDecimal balance;

    // constructors
    public PiggyBank() {
        balance = new BigDecimal("0.00");
    }

    // getters & setters

    public BigDecimal getBalance() {
        return balance;
    }


    // helper Methods

    public void feedMoney(BigDecimal amountToFeed) {
        balance = balance.add(amountToFeed);
    }

    public void subtractMoney(BigDecimal amountToRemove) {
        balance = balance.subtract(amountToRemove);
    }


}
