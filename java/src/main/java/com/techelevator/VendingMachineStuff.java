package com.techelevator;

import com.techelevator.inventory.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineStuff {

    private FileImporter vendingMachineFile;
    private PiggyBank piggyBank;
    private LinkedHashMap<String, VendingMachineItem> mapCopy;
    private  AuditLog auditLog;

    public VendingMachineStuff(FileImporter vendingMachineFile) {
        this.vendingMachineFile = vendingMachineFile;
        piggyBank = new PiggyBank();
        mapCopy = vendingMachineFile.createInventoryMap();
        auditLog = new AuditLog();
    }

    // make copy of inventory list from File Importer and convert to String for display
    public List<String> getInventoryListAsString() {

        List<String> inventory = new ArrayList<String>();

        for (Map.Entry<String, VendingMachineItem> entry : mapCopy.entrySet()) {
            // to display inventory that is sold out:
            String stockLevelAsString = String.valueOf(entry.getValue().getStockLevel());
            if (stockLevelAsString.contentEquals("0")) {
                stockLevelAsString = "SOLD OUT";
            }
            // format the displayInventory
            String formattedInventory = String.format("%s | %-18s | $%s | Stock: %s", entry.getKey(), entry.getValue().getName(), entry.getValue().getCost(), stockLevelAsString);
            // add to output list
            inventory.add(formattedInventory);
        }
        return inventory;
    }

    public BigDecimal displayCurrentBalance() {
        return piggyBank.getBalance();
    }

    public void feedMoney(int moneyInserted) {
        piggyBank.feedMoney(new BigDecimal(moneyInserted));
        auditLog.addToAuditLog("FEED MONEY:", new BigDecimal(moneyInserted), piggyBank.getBalance());
    }

    public String purchaseTheSnack(String selectedSlotId) {

        try {
            VendingMachineItem snackInstance = mapCopy.get(selectedSlotId);

            if (snackInstance.getStockLevel() == 0) {
                System.out.println("Sorry, that's sold out. Try again.");
            } else {
                BigDecimal balanceBeforePurchase = piggyBank.getBalance();
                snackInstance.subtract1FromInventory();
                piggyBank.subtractMoney(snackInstance.getCost());
                auditLog.addToAuditLog(snackInstance.getName() + " " + selectedSlotId, balanceBeforePurchase, piggyBank.getBalance());
                return snackInstance.getDispensedMessage() + "\nYou selected " + snackInstance.getName() + " for $" + snackInstance.getCost() +
                        ". Your remaining balance is " + piggyBank.getBalance();
            }
        }
        catch (NullPointerException e) {
            System.out.println("that's not a product");
        }
        return "That was not a successful purchase, please try again";
    }
    public String returnChangeFromPiggyBank() {
        String finishedPurchase = piggyBank.giveChange(piggyBank.getBalance());
        auditLog.addToAuditLog("GIVE CHANGE:", piggyBank.getBalance(), new BigDecimal("0.00"));
        return finishedPurchase;
    }
}
