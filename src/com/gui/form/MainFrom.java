
package com.gui.form;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainFrom extends Stage {

    private String nameFormString = "mainForm";
    private final Button mainButton, SalesBillButton, itemButton, supplierBillButton, supplierButton;
    private final Button reportButton, userButton, logoutButton, closeButton;
    private Node pane;
    private final BorderPane mainPane;

    public MainFrom() {


        mainButton = new Button("الرئيسية");
        mainButton.setOnAction(e -> mainButtonOnAction());

        itemButton = new Button("الاصناف");
        itemButton.setOnAction(e -> itemButtonOnAction());

        SalesBillButton = new Button("المبيعات");
        SalesBillButton.setOnAction(e -> SalesBillButtonOnAction());

        supplierBillButton = new Button("المشتريات");
        supplierBillButton.setOnAction(e -> supplierBillButtonOnAction());

        supplierButton = new Button("الموردين");
        supplierButton.setOnAction(e -> supplierButtonOnAction());

        reportButton = new Button("التقارير");
        reportButton.setOnAction(e -> reportButtonOnAction());

        userButton = new Button("المستخدمين");
        userButton.setOnAction(e -> userButtonOnAction());

        logoutButton = new Button("تسجيل خروج");
        logoutButton.setOnAction(e -> logoutButtonOnAction());

        closeButton = new Button("اغلق");
        closeButton.setOnAction(e -> closeButtonOnAction());

        VBox sidBox = new VBox(mainButton, itemButton, SalesBillButton, supplierBillButton, supplierButton,
                reportButton, userButton, logoutButton, closeButton);
        sidBox.setPrefHeight(Double.MAX_VALUE);
        sidBox.setId("sidBox");

        mainPane = new BorderPane();
        mainPane.setLeft(sidBox);

        pane = new StackPane();
        pane.setId("mainPane");
        pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        mainPane.setCenter(pane);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("MainFromStyleSheet.css").toExternalForm());
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        this.setTitle("Genius Sales Appliction");
        this.setScene(scene);
        this.setMaximized(true);
        this.setMinWidth(500);
        this.setMinHeight(400);

    }

    private void mainButtonOnAction() {
        if (!nameFormString.equals("mainForm")) {
            nameFormString = "mainForm";
            pane = new StackPane();
            pane.setId("mainPane");
            pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            mainPane.setCenter(pane);

        }
    }

    private void SalesBillButtonOnAction() {
        if (!nameFormString.equals("SalesBillForm")) {
            nameFormString = "SalesBillForm";
            mainPane.setCenter(null);
        }

    }

    private void itemButtonOnAction() {
        if (!nameFormString.equals("SalesBillForm")) {
            nameFormString = "SalesBillForm";
            mainPane.setCenter(new ItemPane());
        }
    }

    private void supplierBillButtonOnAction() {

    }

    private void supplierButtonOnAction() {

    }

    private void reportButtonOnAction() {

    }

    private void userButtonOnAction() {

    }

    private void logoutButtonOnAction() {
        new LoginFrom().show();
        this.close();
    }

    private void closeButtonOnAction() {
        this.close();
    }

}
