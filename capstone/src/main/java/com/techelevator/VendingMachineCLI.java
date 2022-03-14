package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_REPORT};

	private Menu menu;
	private VendingMachine vm = new VendingMachine();

	Scanner scanner = new Scanner(System.in);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vm.displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				boolean finishTransaction = false;
				while(finishTransaction == false) {
					//prompt user for deposit amount
					System.out.println("How much do you want to deposit?");
					int depositAmount = Integer.parseInt(scanner.nextLine());
					//ensure deposit amount isn't negative
					if (depositAmount>= 0){
						vm.depositMoney(depositAmount);
					}
					System.out.println(vm.getBalance());
					System.out.println(purchaseItems());

					//check to see whether the customer is complete with the transaction
					System.out.println("Are you finished with the transaction? Y/N");
					if (scanner.nextLine().equalsIgnoreCase("Y")){
						finishTransaction = true;
						System.out.println(vm.getChange());
					}
				}
			}else if (choice.equals(MAIN_MENU_OPTION_REPORT)){
				vm.printReport();
			}
		}
	}

	/**
	 * Method to allow customer to try purchasing an item from the vending machine
	 * @return output a string letting the user know whether the selection was invalid or display whether the customer can enjoy their treat
	 */
	public String purchaseItems() {
		if (vm.getBalance() == 0) {
			return "Must make a deposit before making a selection";
		} else {
			System.out.println("Please select an option");
			String selection = scanner.nextLine();
			return (vm.doPurchase(selection));
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}



//keep print statements in CLI
//DateTime only saves at the start of the application
//auditfile restarts at false everytime
//inventory parent class with child classes for noise ei: "getnoise()"
