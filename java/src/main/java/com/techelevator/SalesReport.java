package com.techelevator;

import com.techelevator.inventory.VendingMachineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalesReport {

    // instance variables
    private String fileName = setDateTimeFormat();
    private File salesReportFile = new File(fileName);
    private int totalSales = 0;
    private LinkedHashMap<String, VendingMachineItem> mapCopy;
    private Map<String, Integer> salesReportMap = new HashMap<String, Integer>();

    public SalesReport(LinkedHashMap<String, VendingMachineItem> mapCopy) {
        createNewSalesReport();
    }

    // helper Methods

    private String setDateTimeFormat() {
        String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
        return date;
    }

    public void createNewSalesReport() {
        for (Map.Entry<String, VendingMachineItem> entry : mapCopy.entrySet()) {
            VendingMachineItem snackInstance = mapCopy.get(entry.getKey());
            for (int i = 0; i < mapCopy.size(); i++) {
                salesReportMap.put(snackInstance.getName(), 0);
            }
        }
    }

    public void incrementTotalSales(String itemName) {

        if (salesReportMap.containsKey(itemName)) {
            totalSales++;
            salesReportMap.put(itemName, totalSales);
        }

    }

    public void writeSaleReportFile() {
        try (PrintWriter salesReportWriter = new PrintWriter(salesReportFile)) {


            salesReportWriter.println("something in here"); // something in here
        } catch (FileNotFoundException e) {
            // here
        }
    }

    public int getTotalSales() {
        return totalSales;
    }
}
