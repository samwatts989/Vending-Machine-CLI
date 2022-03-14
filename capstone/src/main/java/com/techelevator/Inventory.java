package com.techelevator;

import java.math.BigDecimal;

public class Inventory {
    private String name;
    private double price;
    private String identifierSlot;
    private String sort;
    private int inventoryCount = 5;


    /**
     * Constructor
     * @param identifierSlot The slot in the vending machine where the inventory is held
     * @param name The name of the inventory
     * @param price The price of the inventory
     * @param sort The type of inventory
     */
    public Inventory(String identifierSlot, String name, double price, String sort){
        this.identifierSlot = identifierSlot;
        this.name = name;
        this.price = price;
        this.sort = sort;
    }

    //getters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getIdentifierSlot() {
        return identifierSlot;
    }
    public int getInventoryCount() {
        return inventoryCount;
    }
    public String getSort() {
        return sort;
    }

    @Override
    public String toString(){
        if (inventoryCount < 1){
            return "Name: " + name + " SOLD OUT";
        }
        return "Identifier Slot: " + identifierSlot + " Name: " + name + " Price: " + price;
    }

    /**
     * Method to deduct inventory by 1
     */
    public void deductInventory(){
        inventoryCount--;
    }
}
