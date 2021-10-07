package com.techelevator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineStuff {

    private FileImporter vendingMachineFile;

    public VendingMachineStuff() {
        this.vendingMachineFile = new FileImporter();
    }

    public List<String> getInventoryListAsString() {
        LinkedHashMap<String, VendingMachineItem> mapCopy = vendingMachineFile.createInventoryMap();

        List<String> inventory = new ArrayList<String>();

        for (Map.Entry<String, VendingMachineItem> entry : mapCopy.entrySet()) {
            String formattedInventory = String.format("%s %s %s %s", entry.getKey(), entry.getValue().getName(), entry.getValue().getCost(), entry.getValue().getStockLevel());
            inventory.add(formattedInventory);
        }
        return inventory;
    }
}
