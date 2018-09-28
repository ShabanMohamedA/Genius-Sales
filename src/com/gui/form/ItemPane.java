package com.gui.form;

import com.gui.dbs.DBS;
import com.gui.dbs.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ItemPane extends BorderPane {

    private final Button addItemButton;
    private final Button editItemButton;

    private final Button deleteItemButton;
    private final Button moveItemButton;
    private final TextField searshTextField;

    private final TableView itemTableView;

    public ItemPane() {

        addItemButton = new Button("اضافة");
        addItemButton.setOnAction(e -> addItemButtonOnAction());

        editItemButton = new Button("تحرير");
        editItemButton.setOnAction(e -> edditItemButtonOnAction());

        deleteItemButton = new Button("حذف");
        deleteItemButton.setOnAction(e -> deleteItemButtonOnAction());

        moveItemButton = new Button("حركة الصنف");
        moveItemButton.setOnAction(e -> moveItemButtonOnAction());

        searshTextField = new TextField();
        searshTextField.setId("searshTextField");

        HBox topPane = new HBox(addItemButton, editItemButton,
                deleteItemButton, moveItemButton, searshTextField);
        topPane.setId("topPane");
        setTop(topPane);

        itemTableView = new TableView();
        itemTableView.setId("itemTableView");
        setCenter(itemTableView);

        fillItemTableView();

        this.getStylesheets().add(getClass().getResource("itemPaneStyleSheet.css").toExternalForm());

    }

    private void addItemButtonOnAction() {
        new ItemDialog(newID()).showAndWait();
        fillItemTableView();
    }

    private void edditItemButtonOnAction() {
        Item item = (Item) itemTableView.getSelectionModel().getSelectedItem();
        if (item == null) {
            System.out.println("com.gui.form.ItemPane.edditItemButtonOnAction()");
            return;
        }
        new ItemDialog(item).showAndWait();
        fillItemTableView();
    }

    private void deleteItemButtonOnAction() {
    
    }

    private void moveItemButtonOnAction() {
    }

    private void fillItemTableView() {
        try {
            itemTableView.getColumns().clear();
            String sql = "SELECT itemID , itemName , ItemPrice,TotalCount FROM itemWithCountQuery";
            PreparedStatement ps = DBS.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            TableColumn itemIdCol = new TableColumn("كود الصنف");
            itemIdCol.setCellValueFactory(new PropertyValueFactory<>("itemID"));

            TableColumn itemNameCol = new TableColumn("اسم الصنف");
            itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

            TableColumn itemPriceCol = new TableColumn("السعف");
            itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

            TableColumn TotalCountCol = new TableColumn("الكمية");
            TotalCountCol.setCellValueFactory(new PropertyValueFactory<>("itemCount"));

            itemTableView.getColumns().addAll(itemIdCol, itemNameCol, itemPriceCol, TotalCountCol);

            ObservableList<Item> data = FXCollections.observableArrayList();

            while (rs.next()) {

                data.add(new Item(rs.getInt(1), rs.getString(2),rs.getBigDecimal(3,2), rs.getInt(4)));
               

            }
            itemTableView.setItems(data);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int newID() {
        try {
            String sql = "SELECT MAX( itemID) FROM itemTable";
            PreparedStatement ps = DBS.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1)+1;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
        }
        return 1;
    }
}
