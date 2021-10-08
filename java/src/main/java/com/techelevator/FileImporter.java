package com.techelevator;

import com.techelevator.inventory.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class FileImporter {

    private File file = new File("vendingmachine.csv");

    private List<String> createFileList() {
        List<String> listFromFile = new ArrayList<String>();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                listFromFile.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, could not load inventory!");
        }
        return listFromFile;
    }

    public Map<String, VendingMachineItem> createInventoryMap() {
        Map<String, VendingMachineItem> inventoryMap = new LinkedHashMap<String, VendingMachineItem>();

        for (String currentLine : createFileList()) {
            String[] snackPiece = currentLine.split("\\|");

            String slotID = snackPiece[0];
            String name = snackPiece[1];
            BigDecimal cost = new BigDecimal(snackPiece[2]);
            String itemType = snackPiece[3];

            switch (itemType) {
                case "Chips":
                    Chips newChip = new Chips(name, cost);
                    inventoryMap.put(slotID, newChip);
                    break;
                case "Candy":
                    Candy newCandy = new Candy(name, cost);
                    inventoryMap.put(slotID, newCandy);
                    break;
                case "Drink":
                    Drink newDrink = new Drink(name, cost);
                    inventoryMap.put(slotID, newDrink);
                    break;
                case "Gum":
                    Gum newGum = new Gum(name, cost);
                    inventoryMap.put(slotID, newGum);
                    break;
            }

        }
        return inventoryMap;
    }

}
