package com.gui.form;

import com.gui.dbs.DBS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginFrom extends Stage {

    private final Text mainText;
    private final Label userNameLabel, passwordlLabel;
    private final TextField userNameField;
    private final PasswordField passField;
    private final Button loginButton;

    public LoginFrom() {

        Font f = Font.loadFont(getClass().getResourceAsStream("fonts/arial.ttf"), 14);

        mainText = new Text("تسجيل الدخول");
        mainText.setFont(Font.loadFont(getClass().getResourceAsStream("fonts/andlso.ttf"), 25));

        userNameLabel = new Label("اسم المستخدم");
        userNameLabel.setFont(f);;

        passwordlLabel = new Label("كلمة السر");
        passwordlLabel.setFont(f);

        userNameField = new TextField();
        userNameField.setFont(f);

        passField = new PasswordField();
        passField.setFont(f);

        loginButton = new Button("دخول");
        loginButton.setFont(f);
        loginButton.setOnAction(e -> loginButtonOnAction());

        HBox hb = new HBox(loginButton);
        hb.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);

        grid.add(mainText, 0, 0, 2, 1);
        grid.add(userNameLabel, 0, 1);
        grid.add(userNameField, 1, 1);
        grid.add(passwordlLabel, 0, 2);
        grid.add(passField, 1, 2);
        grid.add(hb, 1, 3);

        Scene scene = new Scene(grid);
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        this.setTitle("تسجيل الدخول");
        this.setScene(scene);
        this.setResizable(false);

    }

    private void loginButtonOnAction() {

        try {
            if(userNameField.getText().isEmpty() || passField.getText().isEmpty()  ){
             System.out.println("error");
             return ;               
      
            }
            PreparedStatement ps = DBS.conn().prepareStatement(
                    "SELECT * FROM userTable WHERE userID=? AND userPass=?");
            ps.setString(1, userNameField.getText());
            ps.setString(2, passField.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                new MainFrom().show();
                this.close();
            }else{
                  System.out.println("erro");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
