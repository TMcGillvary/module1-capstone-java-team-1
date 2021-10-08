package com.techelevator;

import com.techelevator.inventory.Chips;
import com.techelevator.inventory.VendingMachineItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineStuffTest {

    FileImporter vendingMachineFile = new FileImporter();
    VendingMachineStuff vendingMachine = new VendingMachineStuff(vendingMachineFile);

    @Test
    public void sold_out_inventory_displays_correctly() {

        // Arrange
        vendingMachine.feedMoney(new BigDecimal("20.00"));
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");

        // Act
        String actual = vendingMachine.getInventoryListAsString().get(0);
        String expected = "A1 | Potato Crisps      | $3.05 | Stock: SOLD OUT";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void display_current_balance_displays_correctly() {
        // Arrange
        vendingMachine.feedMoney(new BigDecimal("20.00"));

        // Act
        String actual = String.valueOf(vendingMachine.displayCurrentBalance());
        String expected = "20.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void snack_purchase_fails_if_no_money() {

        //Act
        String actual = vendingMachine.purchaseTheSnack("A1");
        String expected = "That was not a successful purchase, please try again.";

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void snack_purchase_fails_if_OOS() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20.00"));
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");

        //Act
        String actual = vendingMachine.purchaseTheSnack("A1");
        String expected = "That was not a successful purchase, please try again.";

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void snack_purchase_fails_if_not_valid_product_id() {
        // Act
        String actual = vendingMachine.purchaseTheSnack("A11");
        String expected = "That was not a successful purchase, please try again.";

        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void snack_purchased_successfully() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20.00"));

        //Act
        String actual = vendingMachine.purchaseTheSnack("A1");
        String expected = "Crunch Crunch, Yum!\n" +
                "You selected Potato Crisps for $3.05. Your remaining balance is 20.00";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void change_returns_correctly() {
        // Arrange
        vendingMachine.feedMoney(new BigDecimal("1.00"));

        // Act
        String actual = vendingMachine.returnChangeFromPiggyBank();
        String expected = "Your change is 4 quarters, 0 dimes, & 0 nickels.";

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
