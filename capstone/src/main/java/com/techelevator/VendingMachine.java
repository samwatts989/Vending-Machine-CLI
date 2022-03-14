package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {

    private List<Inventory> inventoryList = new ArrayList<>();
    private LocalDateTime dateTime = LocalDateTime.now();
    private Audit fileAudit = new Audit();
    private Map<String, Integer> inventoryProduct = new HashMap<>();
    private double balance = 0;
    private double totalSales;

    public VendingMachine(){
        setInventoryList(new File("ExampleFiles/VendingMach ine.txt"));
        setMap();
    }

    public String getDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm:ss a");
        return dateTime.format(formatter);
    }

    /**
     * Method to return a list of the inventory list
     * @return a list of inventory items
     */
    public List<Inventory> getInventoryList(){
        return inventoryList;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(int amount) { balance += amount;
    }
    public double endBalance(double price){
        return balance -= price;
    }

    /**
     * Method to calculate the total change to give to the customer
     * @return the amount of change to give the customer
     */
    public String getChange(){
        int countNickels = 0;
        int countDimes = 0;
        int countQuarters = 0;

        fileAudit.auditFile(getDateTime() + " GIVE CHANGE: " + balance + " $0.00");

        while (balance >= .25){
            countQuarters++;
            balance -= .25;
        }

        while (balance >= .10){
            countDimes++;
            balance -= .10;
        }

        while (balance == .05){
            countNickels++;
            balance -= .05;
        }
        return "Quarters: " + countQuarters + " Dimes: " + countDimes + " Nickels: " + countNickels;
    }

    /**
     * Method to set the inventory list
     * @param file accepts a file to read for the inventory instantiation
     */
    public void setInventoryList (File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] lineSplit = line.split("\\|");
                inventoryList.add(new Inventory(lineSplit[0], lineSplit[1], Double.parseDouble(lineSplit[2]), lineSplit[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //creates map of inventory product to use by printReport
    //make private
    public void setMap() {
        for (int i = 0; i < getInventoryList().size(); i++) {
            inventoryProduct.put(getInventoryList().get(i).getName(), 0);
        }
    }

    public Map<String, Integer> getInventoryProduct() {
        return inventoryProduct;
    }

    //Creates SalesReport file for vending machine owner
    public void printReport () {
        boolean newFile = false;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("salesreport.txt", newFile))){
            newFile = true;
            for(Map.Entry<String, Integer> printMap : inventoryProduct.entrySet()) {
                pw.println(printMap.getKey() + " | " + printMap.getValue());
            }
            pw.println();
            pw.println("**TOTAL SALES** " + totalSales);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to print the list of inventory items
     */
    public void displayItems() {
        for (Inventory item : getInventoryList()) {
            System.out.println(item);
        }
    }

    /**
     * Method to allow customer to try purchasing an item from the vending machine
     * @param selection the slot number that is selected by the user
     * @return a String whether the selection was made or not
     */
    public String doPurchase(String selection){
        for (int i = 0; i < getInventoryList().size(); i++){
            //check to see whether item is in stock
            if((getInventoryList().get(i).getInventoryCount() < 1)){
                return "Sorry, item is sold out";
            }
            //check to see whether selection is valid
            if(selection.equalsIgnoreCase(getInventoryList().get(i).getIdentifierSlot())){
                //check to see whether there is sufficient funds
                if(getBalance() >= getInventoryList().get(i).getPrice()){
                    //deduct one from the inventory
                    getInventoryList().get(i).deductInventory();
                    //deduct total balance
                    //add to total sales
                    totalSales += getInventoryList().get(i).getPrice();
                    endBalance(getInventoryList().get(i).getPrice());

                    //alters inventoryProductMap to track inventory for sold out and auditFile
                    for (String key: inventoryProduct.keySet()){
                        if(key.equals(getInventoryList().get(i).getName())){
                            inventoryProduct.put(key, inventoryProduct.get(key) + 1);
                        }
                    }
                    //tells auditFile what to print
                    fileAudit.auditFile(getDateTime() + " " + getInventoryList().get(i).getName() + " " + getInventoryList().get(i).getIdentifierSlot() + " " + getInventoryList().get(i).getPrice() + " " + balance);

                    if(getInventoryList().get(i).getSort().equals("Chip")){
                        return "Crunch Crunch, Yum!";
                    }else if(getInventoryList().get(i).getSort().equals("Candy")){
                        return "Munch Munch, Yum!";
                    }else if(getInventoryList().get(i).getSort().equals("Gum")){
                        return "Chew Chew, Yum!";
                    }else if(getInventoryList().get(i).getSort().equals("Drink")){
                        return "Glug Glug, Yum!";
                    }
                }
            }
        }
        return "Invalid Selection";
    }

    /**
     * Method to deposit money to balance
     * @param amount accepts the amount to deposit
     */
    public void depositMoney(int amount) {
        setBalance(amount);
        fileAudit.auditFile(getDateTime() + " FEED MONEY: " + amount + " " + balance);
    }
}
