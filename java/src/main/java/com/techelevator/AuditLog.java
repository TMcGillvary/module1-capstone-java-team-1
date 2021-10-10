package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {

    // instance variables
    private String fileName = "Log.txt";
    // boolean doesFileExist = new File(fileName).delete();
    private File auditLogFile = new File(fileName);

    // this code is commented out atm because this code basically makes it so it overwrites the current log file with a new one each time
    // the vending machine is turned on, meaning the log will only ever be a log of purchases since the machine is turned on. I don't
    // know if that's the intention of the log file now that I'm getting ready to do the final git push, so... I'm going to take
    // it out but leave it here so we can talk about it during code review.
//    public AuditLog() {
//        createNewAuditLog();
//    }
//
//
//    private void createNewAuditLog() {
//        try {
//            auditLogFile.createNewFile();
//        } catch (IOException e) {
//            System.out.println("Unable to create new file, please try again");
//        }
//    }

    //helper methods
    
    private String setDateTimeFormat() {
        String date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
        return date;
    }

    public void addToAuditLog(String event, BigDecimal beforeTransaction, BigDecimal afterTransaction) {
        String beforeTransactionString = String.valueOf(beforeTransaction);
        String afterTransactionString = String.valueOf(afterTransaction);
        String printString = String.format("%s %22s $%-6s $%-6s", setDateTimeFormat(), event, beforeTransactionString, afterTransactionString);

        try (PrintWriter auditLogWriter = new PrintWriter(new FileWriter(auditLogFile, true))) {
            auditLogWriter.println(printString);
        } catch (IOException e) {
            System.out.println("Unable to add to file, please try again");
        }

    }
}
