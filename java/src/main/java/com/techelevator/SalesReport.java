package com.techelevator;

import com.techelevator.inventory.VendingMachineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReport {

    // instance variables
    private String fileName = setDateTimeFormat() + ".txt";

    private File salesReportFile = new File(fileName);
    private int totalItemSales = 0;
    private BigDecimal totalGrossSales = new BigDecimal("0.00");
    private Map<String, VendingMachineItem> mapCopy;
    private Map<String, Integer> salesReportMap = new LinkedHashMap<>();

    public SalesReport(Map<String, VendingMachineItem> mapCopy) {
        for (Map.Entry<String, VendingMachineItem> entry : mapCopy.entrySet()) {
            VendingMachineItem snackInstance = mapCopy.get(entry.getKey());
            for (int i = 0; i < mapCopy.size(); i++) {
                salesReportMap.put(snackInstance.getName(), 0);
            }
        }
        createNewSalesLog();
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

    private String setDateTimeFormat() {
        String date = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss a").format(new Date());
        return date;
    }

    private void createNewSalesLog() {
        try {
            salesReportFile.createNewFile();
        }
        catch (IOException e) {
            throw new VendingMachineException("Unable to create new file, please try again");
        }
    }

    public void incrementTotalItemSales(String itemName) {

        if (salesReportMap.containsKey(itemName)) {
            totalItemSales++;
            salesReportMap.put(itemName, totalItemSales);
        }

    }

    public void incrementTotalGrossSales(BigDecimal itemCost) {
       totalGrossSales = totalGrossSales.add(itemCost);
    }

    public void writeSaleReportFile() {

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

    public int getTotalItemSales() {
        return totalItemSales;
    }
}
