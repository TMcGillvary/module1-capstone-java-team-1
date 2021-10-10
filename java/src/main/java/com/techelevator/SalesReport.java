package com.techelevator;

import com.techelevator.inventory.VendingMachineItem;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class SalesReport {

    // instance variables
    private BigDecimal totalGrossSales = new BigDecimal("0.00");
    private Map<String, Integer> salesReportMap = new LinkedHashMap<>();

    // constructor
    public SalesReport(Map<String, VendingMachineItem> mapCopy) {
        for (Map.Entry<String, VendingMachineItem> entry : mapCopy.entrySet()) {
            VendingMachineItem snackInstance = mapCopy.get(entry.getKey());
            for (int i = 0; i < mapCopy.size(); i++) {
                salesReportMap.put(snackInstance.getName(), 0);
            }
        }

    }

    // helper Methods

    public List<String> getSalesReportListAsString() {

        List<String> salesReportInventory = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : salesReportMap.entrySet()) {
            // format the displayInventory
            String formattedInventory = String.format("%-18s | %d", entry.getKey(), entry.getValue());
            // add to output list
            salesReportInventory.add(formattedInventory);
        }
        return salesReportInventory;
    }

    private String getCurrentTime() {
        String date = new SimpleDateFormat("MM-dd-yyyy hh-mm-ss a").format(new Date(System.currentTimeMillis()));
        return date;
    }

    public void incrementTotalItemSales(String itemName) {

        if (salesReportMap.containsKey(itemName)) {
            int totalItemSales = salesReportMap.get(itemName);
            salesReportMap.put(itemName, totalItemSales + 1);
        }
    }

    public void incrementTotalGrossSales(BigDecimal itemCost) {
        totalGrossSales = totalGrossSales.add(itemCost);
    }

    public void writeSaleReportFile() {

        String fileName = getCurrentTime() + ".txt";

        File salesReportFile = new File(fileName);

        try (PrintWriter salesReportWriter = new PrintWriter(salesReportFile)) {

            for (String line : getSalesReportListAsString()) {
                salesReportWriter.println(line);
            }

            salesReportWriter.println();
            salesReportWriter.println("Total Sales (in dollars): $" + totalGrossSales);

        } catch (FileNotFoundException e) {
            throw new VendingMachineException("Unable to add to file, please try again");
        }
    }
}
