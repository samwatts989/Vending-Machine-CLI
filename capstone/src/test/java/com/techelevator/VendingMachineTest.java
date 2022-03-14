package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineTest {
    @Test
    public void test_vending_machine_get_inventory_list() {
        File file = new File("ExampleFiles/VendingMachine.txt");
        VendingMachine vm = new VendingMachine();
        vm.setInventoryList(file);
        List<Inventory> expectedList = new ArrayList<>();
        expectedList.add(new Inventory("A1", "Potato Crisps", 3.05, "Chip"));
        expectedList.add(new Inventory("A2", "Stackers",1.45, "Chip"));
        expectedList.add(new Inventory("A3", "Grain Waves", 2.75, "Chip"));
        expectedList.add(new Inventory("A4", "Cloud Popcorn", 3.65, "Chip"));
        expectedList.add(new Inventory("B1", "Moonpie",1.80, "Candy"));
        expectedList.add(new Inventory("B2", "Cowtales", 1.50, "Candy"));
        expectedList.add(new Inventory("B3", "Wonka Bar",1.50, "Candy"));
        expectedList.add(new Inventory("B4", "Crunchie", 1.75, "Candy"));
        expectedList.add(new Inventory("C1", "Cola", 1.25, "Drink"));
        expectedList.add(new Inventory("C2", "Dr. Salt", 1.50, "Drink"));
        expectedList.add(new Inventory("C3", "Mountain Melter", 1.50, "Drink"));
        expectedList.add(new Inventory("C4", "Heavy", 1.50, "Drink"));
        expectedList.add(new Inventory("D1", "U-Chews", .85, "Gum"));
        expectedList.add(new Inventory("D2", "Little League Chew", .95, "Gum"));
        expectedList.add(new Inventory("D3", "Chiclets", .75, "Gum"));
        expectedList.add(new Inventory("D4", "Triplemint", .75, "Gum"));
        Assert.assertEquals(expectedList, vm.getInventoryList());
    }


}
