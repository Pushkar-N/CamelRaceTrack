package com.CamelRaceTrack;

import java.util.ArrayList;

public class Inventory {
    public int Amount;
    public int Count;

    public static void DisplayAllInventory(ArrayList<Inventory> inventories) {
        System.out.println("Inventory:");
        for(int i = inventories.size() - 1; i>=0 ; i--) {
            System.out.println(new StringBuilder().append(Constants.DOLLAR_SIGN).append(inventories.get(i).Amount).append(",").append(inventories.get(i).Count).toString());
        }
//        for (Inventory i:inventories
//             ) {
//            System.out.println(Constants.DOLLAR_SIGN + i.getAmount()+","+i.getCount());
//        }
    }

    public static boolean CheckInventory(ArrayList<Inventory> inventories, int amount) {
        int totalAmount = Inventory.GetTotalAmount(inventories);
        if(totalAmount < amount){
            return false;
        }
        else
            return true;
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
