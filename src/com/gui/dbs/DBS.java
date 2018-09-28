package com.gui.dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBS {

    public static Connection conn() {

        Connection con = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  
            con = DriverManager.getConnection("jdbc:ucanaccess://GeniusSales.mdb", "", "gs20102008");
           
        } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
        }

        return con;
    }

}
