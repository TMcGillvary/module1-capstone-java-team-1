package com.techelevator;

import com.techelevator.view.Menu;

import java.io.IOException;

public class VendingMachineCLI {

    // Menu Choices
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String EXIT_PROGRAM = "Exit";
    private static final String HIDDEN_SALES_REPORT = "Secret Sales Report 1";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_PROGRAM, HIDDEN_SALES_REPORT};


    //Purchase Menu Choices
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    // instance variables for VendingMachineCLI in order to run
    private Menu menu;


    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

                productMenuDisplayBanner();
                menu.displayInventoryFileCorrectly();

            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

                menuLoop:
                while (true) {
                    printPurchaseMenuOptionsBanner();
                    // do purchase
                    String purchaseChoice = (String) menu.getChoiceFromPurchaseOptions(PURCHASE_MENU_OPTIONS);

                    switch (purchaseChoice) {
                        case PURCHASE_MENU_OPTION_FEED_MONEY:
                            feedMoneyDisplayBanner();
                            menu.addMoneyToVendingMachine();
                            break;
                        case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
                            productMenuDisplayBanner();
                            menu.displayInventoryFileCorrectly();
                            System.out.println();
                            System.out.print("Enter Selection >>> ");
                            menu.getUserInputForProductSelection();
                            break;
                        case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
                            finishTransactionDisplayBanner();
                            menu.finishTransaction();
                            break menuLoop;
                    }
                }

            } else if (choice.equals(EXIT_PROGRAM)) {
                printExitBanner();
                return;
            } else if (choice.equals(HIDDEN_SALES_REPORT)) {
                menu.createSalesReport();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // added
        printApplicationBanner();

        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }


    // for fun
    private static void printApplicationBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("                   WELCOME                  ");
        System.out.println("                   to the                   ");
        System.out.println("               VENDO-MATIC 800              ");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void printExitBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("            Thanks for playing!             ");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void productMenuDisplayBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("               Product Display              ");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void printPurchaseMenuOptionsBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("                PURCHASE MENU               ");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void feedMoneyDisplayBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("             Feed Money Selected!           ");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("Please insert money in whole dollar amounts >>> ");
    }

    private static void finishTransactionDisplayBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("           Completing Transaction...        ");
        System.out.println("╚══════════════════════════════════════════╝");
    }


}
