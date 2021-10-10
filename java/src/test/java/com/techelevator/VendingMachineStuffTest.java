package com.techelevator;

import com.techelevator.inventory.Chips;
import com.techelevator.inventory.VendingMachineItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class VendingMachineStuffTest {

    FileImporter vendingMachineFile = new FileImporter();
    VendingMachineStuff vendingMachine = new VendingMachineStuff(vendingMachineFile);

    @Test
    public void sold_out_inventory_displays_correctly() {

        // Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));
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
        vendingMachine.feedMoney(new BigDecimal("20"));

        // Act
        String actual = String.valueOf(vendingMachine.displayCurrentBalance());
        String expected = "20.00";

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = VendingMachineException.class)
    public void snack_purchase_fails_if_no_money() {

        vendingMachine.purchaseTheSnack("A1");

    }

    @Test (expected = VendingMachineException.class)
    public void snack_purchase_fails_if_OOS() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");
        vendingMachine.purchaseTheSnack("A1");

        //Act
        vendingMachine.purchaseTheSnack("A1");


        //Assert


    }

    @Test (expected = VendingMachineException.class)
    public void snack_purchase_fails_if_not_valid_product_id() {
        // Act
        vendingMachine.purchaseTheSnack("A11");

        //Assert

    }

    @Test
    public void snack_purchased_successfully() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));

        //Act
        String actual = vendingMachine.purchaseTheSnack("A1");
        String expected = "Crunch Crunch, Yum!\n" +
                "You selected Potato Crisps for $3.05. Your remaining balance is 16.95";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void snack_Candy_purchased_successfully() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));

        //Act
        String actual = vendingMachine.purchaseTheSnack("B1");
        String expected = "Munch Munch, Yum!\n" +
                "You selected Moonpie for $1.80. Your remaining balance is 18.20";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void snack_Drink_purchased_successfully() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));

        //Act
        String actual = vendingMachine.purchaseTheSnack("C3");
        String expected = "Glug Glug, Yum!\n" +
                "You selected Mountain Melter for $1.50. Your remaining balance is 18.50";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void snack_Gum_purchased_successfully() {
        //Arrange
        vendingMachine.feedMoney(new BigDecimal("20"));

        //Act
        String actual = vendingMachine.purchaseTheSnack("D2");
        String expected = "Chew Chew, Yum!\n" +
                "You selected Little League Chew for $0.95. Your remaining balance is 19.05";

        // Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void change_returns_correctly() {
        // Arrange
        vendingMachine.feedMoney(new BigDecimal("1"));

        // Act
        String actual = vendingMachine.returnChangeFromPiggyBank();
        String expected = "Your change is 4 quarters, 0 dimes, & 0 nickels.";

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
