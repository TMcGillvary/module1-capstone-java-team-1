package com.techelevator;

import com.techelevator.inventory.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class FileImporterTest {

    @Test
    public void inventoryMapCreatesWithCorrectSize() {

        //Arrange
        FileImporter fileImporter = new FileImporter();

        // Act
        String actual = String.valueOf(fileImporter.createInventoryMap().size());

        // Assert
        Assert.assertEquals("16", actual);
    }

    @Test
    public void item_name_of_A1_is_correct_name() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        String actual = String.valueOf(mapCopy.get("A1").getName());

        VendingMachineItem expected = new Chips("Potato Crisps", new BigDecimal("3.05"));
        String expected2 = expected.getName();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void cost_of_A1_is_correct() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        BigDecimal actual = mapCopy.get("A1").getCost();

        VendingMachineItem expected = new Chips("Potato Crisps", new BigDecimal("3.05"));
        BigDecimal expected2 = expected.getCost();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void item_name_of_B1_is_correct_name() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        String actual = String.valueOf(mapCopy.get("B1").getName());

        VendingMachineItem expected = new Candy("Moonpie", new BigDecimal("1.80"));
        String expected2 = expected.getName();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void cost_of_B1_is_correct() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        BigDecimal actual = mapCopy.get("B1").getCost();

        VendingMachineItem expected = new Candy("Moonpie", new BigDecimal("1.80"));
        BigDecimal expected2 = expected.getCost();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void item_name_of_C1_is_correct_name() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        String actual = String.valueOf(mapCopy.get("C1").getName());

        VendingMachineItem expected = new Drink("Cola", new BigDecimal("1.25"));
        String expected2 = expected.getName();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void cost_of_C1_is_correct() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        BigDecimal actual = mapCopy.get("C1").getCost();

        VendingMachineItem expected = new Drink("Cola", new BigDecimal("1.25"));
        BigDecimal expected2 = expected.getCost();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void item_name_of_D1_is_correct_name() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        String actual = String.valueOf(mapCopy.get("D1").getName());

        VendingMachineItem expected = new Gum("U-Chews", new BigDecimal("0.85"));
        String expected2 = expected.getName();

        // Assert
        Assert.assertEquals(expected2, actual);
    }

    @Test
    public void cost_of_D1_is_correct() {
        // Arrange
        FileImporter fileImporter = new FileImporter();
        Map<String, VendingMachineItem> mapCopy = fileImporter.createInventoryMap();

        // Act

        BigDecimal actual = mapCopy.get("D1").getCost();

        VendingMachineItem expected = new Gum("U-Chews", new BigDecimal("0.85"));
        BigDecimal expected2 = expected.getCost();

        // Assert
        Assert.assertEquals(expected2, actual);
    }
}
