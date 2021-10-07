package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

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

    public LinkedHashMap<String, VendingMachineItem> createInventoryMap() {
        LinkedHashMap<String, VendingMachineItem> inventoryMap = new LinkedHashMap<String, VendingMachineItem>();

        for (String currentLine : createFileList()) {
            String[] snackPiece = currentLine.split("\\|");

            String slotID = snackPiece[0];
            String name = snackPiece[1];
            BigDecimal cost = new BigDecimal(snackPiece[2]);
            String itemType = snackPiece[3];

            switch (itemType) {
                case "chips":
                    Chips newChip = new Chips(name, cost);
                    inventoryMap.put(slotID, newChip);
                case "candy":
                    Candy newCandy = new Candy(name, cost);
                    inventoryMap.put(slotID, newCandy);
                case "drink":
                    Drink newDrink = new Drink(name, cost);
                    inventoryMap.put(slotID, newDrink);
                case "gum":
                    Gum newGum = new Gum(name, cost);
                    inventoryMap.put(slotID, newGum);
            }

        }
        return inventoryMap;
    }

}