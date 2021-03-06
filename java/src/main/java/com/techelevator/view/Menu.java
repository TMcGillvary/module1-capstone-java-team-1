package com.techelevator.view;

import com.techelevator.FileImporter;
import com.techelevator.VendingMachineException;
import com.techelevator.VendingMachineStuff;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;
    private FileImporter vendingMachineFile = new FileImporter();
    private VendingMachineStuff vendingMachine = new VendingMachineStuff(vendingMachineFile);

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {

        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            String hiddenOption = String.valueOf(options[i]);
            if (hiddenOption.endsWith("1")) {
                continue;
            }
            // if option contains,ends etc *, continue
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    /**
     * Create Purchase Options Menu Stuff
     */

    public Object getChoiceFromPurchaseOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayPurchaseMenuOptions(options);
            choice = getPurchaseChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getPurchaseChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayPurchaseMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.println();
        out.println("Current Money Provided: " + vendingMachine.displayCurrentBalance());
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }


    // print out with each entry on a line rather than one giant block list
    public void displayInventoryFileCorrectly() {

        for (String eachLine : vendingMachine.getInventoryListAsString()) {
            System.out.println(eachLine);
        }
        System.out.println("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
    }


    /**
     * Option 1: Feed Money Into Machine
     */

    // get user input to feed money into vending machine
    public void addMoneyToVendingMachine() {

        try {
            int moneyInserted = Integer.parseInt(in.nextLine());

            if (moneyInserted == 0) {
                throw new VendingMachineException("You didn't enter any money!");
            } else {
                vendingMachine.feedMoney(new BigDecimal((moneyInserted)));
                System.out.println("???????????????????????? Money Successfully Inserted! ????????????????????????");
                System.out.println();
            }

        } catch (NumberFormatException e) {
            System.out.println("Not a whole dollar amount, please try again");
        } catch (VendingMachineException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Option 2: Select Item from list
     */

    public void getUserInputForProductSelection() {
        String selectedSlotId = in.nextLine().toUpperCase();
        try {
            System.out.println("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
            String purchasedItem = vendingMachine.purchaseTheSnack(selectedSlotId);
            System.out.println(purchasedItem);
            System.out.println();
        } catch (VendingMachineException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Option 3: Finish Transaction
     */
    public void finishTransaction() {
        System.out.println(vendingMachine.returnChangeFromPiggyBank());
    }

    public void createSalesReport() {
        vendingMachine.createSalesReport();
    }
}
