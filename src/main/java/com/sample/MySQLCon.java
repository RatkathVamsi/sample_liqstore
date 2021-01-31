package com.sample;

import com.sample.model.Purchases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class MySQLCon{
    private Connection createConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/default","root","root");
            System.out.println("SQL connection created");
        return con;
    }
    public List<Purchases> getPurchases() throws SQLException, ClassNotFoundException {
        List<Purchases> purchases = new ArrayList<Purchases>();

        Statement stmt=createConnection().createStatement();
        ResultSet rs=stmt.executeQuery("SELECT ItemName,sum(Qty),sum(Amount) FROM purchases group by ItemName;");
        while(rs.next()) {
            //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            Purchases purchase = new Purchases();
            //   purchase.setDepartmentName(rs.getString(1));
            //purchase.setDate(rs.getDate(2));
            purchase.setAmount(rs.getInt(3));
            purchase.setItemName(rs.getString(1));
            purchase.setQty(rs.getInt(2));
            purchases.add(purchase);
        }

        for (Purchases purchase : purchases) {
            System.out.println(purchase.toString());
        }

        return purchases;

    }
}
