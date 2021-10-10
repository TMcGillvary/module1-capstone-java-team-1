package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AuditLog {

    //date time format
    //what you're doing
    //before and after transaction

    private String fileName = "Log.txt";
    private File auditLogFile = new File(fileName);

    public AuditLog() {
        createNewAuditLog();
    }

    //helper methods
    private void createNewAuditLog() {
        try {
            auditLogFile.createNewFile();
        }
        catch (IOException e) {
            throw new VendingMachineException("Unable to create new file, please try again");
        }
    }

    private String setDateTimeFormat() {
        String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
        return date;
    }

    public void addToAuditLog(String event, BigDecimal beforeTransaction, BigDecimal afterTransaction) {
        String beforeTransactionString = String.valueOf(beforeTransaction);
        // feedMoney not formatting correctly, how to fix??
        String afterTransactionString = String.valueOf(afterTransaction);
        String printString = String.format("%s %-18s $%-6s $%-6s", setDateTimeFormat(), event, beforeTransactionString, afterTransactionString);

        try (PrintWriter auditLogWriter = new PrintWriter(new FileWriter(auditLogFile, true))) {
            auditLogWriter.println(printString);
        }
        catch (IOException e) {
            throw new VendingMachineException("Unable to add to file, please try again");
        }

    }
}
