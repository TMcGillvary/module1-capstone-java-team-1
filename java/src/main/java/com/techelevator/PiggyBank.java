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

    public String giveChange(BigDecimal balance) {
        BigDecimal counter = balance;
        int totalQuartersToReturn = 0;
        int totalDimesToReturn = 0;
        int totalNickelsToReturn = 0;

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");

        while (counter.compareTo(BigDecimal.ZERO) > 0) {
            if (counter.compareTo(quarter) >= 0) {
                totalQuartersToReturn++;
                counter = counter.subtract(quarter);
            } else if (counter.compareTo(dime) >= 0) {
                totalDimesToReturn++;
                counter = counter.subtract(dime);
            } else if (counter.compareTo(nickel) >= 0) {
                totalNickelsToReturn++;
                counter = counter.subtract(nickel);
            }
        }
        this.balance = new BigDecimal("0.00");
        String returnMessage = "Your change is " + totalQuartersToReturn + " quarters, " + totalDimesToReturn + " dimes, & " + totalNickelsToReturn + " nickels.";
        return returnMessage;
    }
}
