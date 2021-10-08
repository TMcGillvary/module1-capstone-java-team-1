package com.techelevator;

import com.techelevator.inventory.Chips;
import com.techelevator.inventory.VendingMachineItem;
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
}
