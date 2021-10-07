package com.techelevator;

import com.techelevator.inventory.Chips;
import com.techelevator.inventory.VendingMachineItem;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineStuffTest {

    @Test
    public void sold_out_inventory_displays_correctly() {

        // Arrange
        FileImporter vendingMachineFile = new FileImporter();
        VendingMachineStuff vendingMachine = new VendingMachineStuff(vendingMachineFile);
        VendingMachineItem chips = new Chips("Doritos", new BigDecimal("1.00"));

        // Act
        vendingMachine.getInventoryListAsString();

        // Assert

    }
}
