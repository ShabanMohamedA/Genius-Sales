/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui.form;

import com.gui.dbs.DBS;
import com.gui.dbs.Item;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author shaban
 */
public class ItemDialog extends Dialog<Object> {

    private int itemID;
    private String itemName;
    private BigDecimal itemPrice;
    private boolean isNew = false;

    private TextField itemIDField, itemNameField, itemPriceField;

    public ItemDialog(Item item) {
        this.itemID = item.getItemID();
        this.itemName = item.getItemName();
        this.itemPrice = item.getItemPrice();

        setTitle("الاصناف");
        setHeaderText("تحرير الاصناف");
        getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        GridPane grid = new GridPane();
        itemIDField = new TextField(String.valueOf(itemID));
        itemIDField.setEditable(false);
        itemNameField = new TextField(String.valueOf(itemName));
        itemPriceField = new TextField(String.valueOf(itemPrice));

        Label itemIDLabel = new Label("كود الصنف");
        Label itemNameLabel = new Label("اسم السنف");
        Label itemPriceLabel = new Label("السعر");

        grid.add(itemIDLabel, 0, 0);
        grid.add(itemIDField, 1, 0);
        grid.add(itemNameLabel, 0, 1);
        grid.add(itemNameField, 1, 1);
        grid.add(itemPriceLabel, 0, 2);
        grid.add(itemPriceField, 1, 2);

        getDialogPane().setContent(grid);

        ButtonType okButtonType = new ButtonType("حفظ", ButtonBar.ButtonData.OK_DONE);
        ButtonType canceButtonType = new ButtonType("الغاء", ButtonBar.ButtonData.CANCEL_CLOSE);

        getDialogPane().getButtonTypes().addAll(okButtonType, canceButtonType);

        setResultConverter((ButtonType param) -> {
            if (param == okButtonType) {
                if (isNew) {
                    try {
                        String sql = "INSERT INTO itemTable Values (?,?,?,?,NOW());";
                        PreparedStatement ps = DBS.conn().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(itemIDField.getText()));
                        ps.setString(2, itemNameField.getText());
                        ps.setDouble(3, Double.parseDouble(itemPriceField.getText()));
                        ps.setString(4, "admin");
                        //  ps.setDate(5,LocalDateTime.now());

                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println(ex.getLocalizedMessage());
                    }

                } else {

                    try {
                        if (!itemNameField.getText().equals(itemName) || new BigDecimal(itemPriceField.getText()) != itemPrice) {
                            String sql = "UPDATE  itemTable SET  itemName=? , itemPrice=?, userId=? , enDate=NOW() WHERE itemID=?;";
                            PreparedStatement ps = DBS.conn().prepareStatement(sql);

                            ps.setString(1, itemNameField.getText());
                            ps.setDouble(2, Double.parseDouble(itemPriceField.getText()));
                            ps.setString(3, "admin");
                            ps.setInt(4, Integer.parseInt(itemIDField.getText()));
                            //  ps.setDate(5,LocalDateTime.now());

                            ps.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println(ex.getLocalizedMessage());
                    }

                }
                return null;
            }
            return null;
        });

    }

    public ItemDialog(int newID) {
        this(new Item(newID, "",new BigDecimal("0") , 0));
        isNew = true;
    }

}
