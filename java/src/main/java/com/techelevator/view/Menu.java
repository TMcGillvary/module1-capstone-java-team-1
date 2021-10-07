package com.techelevator.view;

import com.techelevator.FileImporter;
import com.techelevator.PiggyBank;
import com.techelevator.VendingMachineStuff;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private PiggyBank piggyBank = new PiggyBank();
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
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	/**
	 * Create Purchase Options Menu Stuff
	 * @param options
	 * @return
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
		out.println("Current Money Provided: " + piggyBank.getBalance());
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}



	// print out with each entry on a line rather than one giant block list
	public void displayInventoryFileCorrectly() {
		for (String eachLine : vendingMachine.getInventoryListAsString()) {
			System.out.println(eachLine);
		}
	}


	/**
	 * Option 1: Feed Money Into Machine
	 */

	// get user input to feed money into vending machine
	public void addMoneyToVendingMachine() {
		System.out.println("Please insert the money in $1, $2, $5, or $10 increments");
		try {
			int moneyInserted = in.nextInt();
			in.nextLine();
			boolean isWholeDollarAmount = (moneyInserted == 1 || moneyInserted == 2 || moneyInserted == 5 || moneyInserted == 10); // Change

			if (isWholeDollarAmount) {
				piggyBank.feedMoney(new BigDecimal(moneyInserted));
				System.out.println("Thanks, money inserted!");
				System.out.println("===============================");
				// add log thing here
			} else if (moneyInserted == 0) {
				System.out.println("You didn't enter any money!");
			} else {
				System.out.println("Not a whole dollar amount, please try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("Please Insert Valid Currency");
		}
	}


	/**
	 * Option 2: Select Item from list
	 */

	public void getUserInputForProductSelection() {
		System.out.println("Please select from the above list");
		String selectedSlotId = in.nextLine();
		String purchasedItem = vendingMachine.purchaseTheSnack(selectedSlotId);
		System.out.println(purchasedItem);
	}
}
