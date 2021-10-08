package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PiggyBankTest {

    PiggyBank piggyBank = new PiggyBank();

    @Test
    public void balance_is_always_zero_to_start() {

        // Act
        String actual = String.valueOf(piggyBank.getBalance());
        String expected = "0.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void feed_money_adds_money_as_intended() {

        // Arrange
        piggyBank.feedMoney(new BigDecimal("5.00"));

        // Act
        String actual = String.valueOf(piggyBank.getBalance());
        String expected = "5.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void subtract_money_subtracts_money_as_intended() {

        // Arrange
        piggyBank.feedMoney(new BigDecimal("5.00"));
        piggyBank.subtractMoney(new BigDecimal("2.00"));

        // Act
        String actual = String.valueOf(piggyBank.getBalance());
        String expected = "3.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void give_change_resets_balance_to_zero_as_intended() {
        // Arrange
        piggyBank.feedMoney(new BigDecimal("15.00"));
        String result = piggyBank.giveChange(piggyBank.getBalance());

        // Act
        String actual = String.valueOf(piggyBank.getBalance());
        String expected = "0.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void give_change_gives_back_nickels_as_intended() {
        // Arrange
        piggyBank.feedMoney(new BigDecimal("0.05"));

        // Act
        String actual = piggyBank.giveChange(piggyBank.getBalance());
        String expected = "Your change is 0 quarters, 0 dimes, & 1 nickels.";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void give_change_gives_back_dimes_as_intended() {
        // Arrange
        piggyBank.feedMoney(new BigDecimal("0.20"));

        // Act
        String actual = piggyBank.giveChange(piggyBank.getBalance());
        String expected = "Your change is 0 quarters, 2 dimes, & 0 nickels.";

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
