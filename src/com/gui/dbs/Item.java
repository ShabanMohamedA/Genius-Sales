/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.dbs;

import java.math.BigDecimal;

/**
 *
 * @author shaban
 */
public class Item {

    private final int itemID;
    private final String itemName;
    private final BigDecimal itemPrice;
    private final int itemCount;

    public Item(int itemID, String itemName, BigDecimal itemPrice, int itemCount) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public String toString() {
        return itemName;
    }

}
