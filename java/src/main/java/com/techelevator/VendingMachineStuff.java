package com.techelevator;

import com.techelevator.inventory.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineStuff {

    private PiggyBank piggyBank;
    private Map<String, VendingMachineItem> mapCopy;
    private AuditLog auditLog;
    private SalesReport salesReport;

    public VendingMachineStuff(FileImporter vendingMachineFile) {
        piggyBank = new PiggyBank();
        mapCopy = vendingMachineFile.createInventoryMap();
        auditLog = new AuditLog();
        salesReport = new SalesReport(mapCopy);
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

    public void feedMoney(BigDecimal moneyInserted) {
        piggyBank.feedMoney(moneyInserted);
        String moneyInsertedString = moneyInserted + ".00";
        BigDecimal fixedMoney = new BigDecimal(moneyInsertedString);
        auditLog.addToAuditLog("FEED MONEY:", fixedMoney, piggyBank.getBalance());
    }

    public String purchaseTheSnack(String selectedSlotId) {

        try {
            VendingMachineItem snackInstance = mapCopy.get(selectedSlotId);
            BigDecimal currentBalance = piggyBank.getBalance();

            if (snackInstance.getStockLevel() == 0) {
                throw new VendingMachineException("That's out of stock! Please choose an in stock item.");
            } else if (currentBalance.subtract(snackInstance.getCost()).compareTo(BigDecimal.ZERO) < 0) {
                throw new VendingMachineException("That costs more money than what you have! Please enter more money and try again.");
            } else {
                BigDecimal balanceBeforePurchase = piggyBank.getBalance();
                snackInstance.subtract1FromInventory();
                piggyBank.subtractMoney(snackInstance.getCost());
                BigDecimal balanceAfterPurchase = piggyBank.getBalance();

                auditLog.addToAuditLog(snackInstance.getName() + " " + selectedSlotId, balanceBeforePurchase, balanceAfterPurchase);

                salesReport.incrementTotalItemSales(snackInstance.getName());
                salesReport.incrementTotalGrossSales(snackInstance.getCost());

                return "You selected " + snackInstance.getName() + " for $" + snackInstance.getCost() + "."
                        + "\n" + snackInstance.getDispensedMessage()
                        + "\nYour remaining balance is $" + balanceAfterPurchase + ".";
            }
        } catch (NullPointerException e) {
            throw new VendingMachineException("That's not a product! Please enter the Slot ID.");
        }
    }


    public String returnChangeFromPiggyBank() {
        BigDecimal balanceBeforePurchase = piggyBank.getBalance();
        String finishedPurchase = piggyBank.giveChange(piggyBank.getBalance());
        auditLog.addToAuditLog("GIVE CHANGE:", balanceBeforePurchase, new BigDecimal("0.00"));
        return finishedPurchase;
    }

    public void createSalesReport() {
        salesReport.writeSaleReportFile();
    }
}
