package com.CamelRaceTrack.Models;

import com.CamelRaceTrack.Common.Constants;
import java.text.MessageFormat;
import java.util.ArrayList;

public class Inventory {
    private int Amount;
    private int Count;

    public static void DisplayAllInventory(ArrayList<Inventory> inventories) {
        System.out.println("Inventory:");
        for(int i = inventories.size() - 1; i>=0 ; i--) {
            System.out.println(MessageFormat.format("{0}{1},{2}",Constants.DOLLAR_SIGN,inventories.get(i).Amount,inventories.get(i).Count));
        }
    }

    public static boolean CheckInventory(ArrayList<Inventory> inventories, int amount) {
        int totalAmount = Inventory.GetTotalAmount(inventories);
        return totalAmount >= amount;
    }

    private static int GetTotalAmount(ArrayList<Inventory> inventories) {
        int totalInventoryValue = 0;
        for (Inventory i: inventories) {
            totalInventoryValue += (i.getCount() * i.getAmount());
        }
        return totalInventoryValue;
    }

    public static int[] GetAllAmounts(ArrayList<Inventory> inventories) {
        int[] notes = new int[inventories.size()];
        for (int i=0 ; i< inventories.size(); i++) {
            notes[i] = inventories.get(i).getAmount();
        }
        return notes;
    }

    public int getAmount() {
        return Amount;
    }

    public int getCount() {
        return Count;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Inventory(Integer amount, Integer count){
        this.Amount = amount;
        this.Count = count;
    }
}
