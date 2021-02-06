package com.sample;

import com.sample.model.Purchases;
import com.sample.model.Sales;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


class MySQLCon {
    MySQLCon() throws SQLException, ClassNotFoundException {
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/default", "root", "root");
        System.out.println("SQL connection created");
        return con;
    }

    public List<Purchases> getPurchases() throws SQLException, ClassNotFoundException {
        List<Purchases> purchases = new ArrayList<Purchases>();

        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ItemName,sum(Qty),sum(Amount) FROM purchases group by ItemName;");
        while (rs.next()) {
            //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            Purchases purchase = new Purchases();
           // purchase.setDepartmentName(rs.getString(1));
            //purchase.setDate(String.valueOf(rs.getDate(2)));
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

    public void insertPurchases(List<Purchases> purchasesList) throws SQLException, ClassNotFoundException, ParseException {
        Connection con=  createConnection();
        for (int i = 0; i < purchasesList.size(); i++) {
            Purchases purchase = purchasesList.get(i);


            String query = "INSERT INTO purchases VALUES (?,?,?,?,?)";

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date myDate = formatter.parse(purchase.getDate());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

            // create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, purchase.getDepartmentName());
            preparedStmt.setDate(2, sqlDate);
            preparedStmt.setFloat(3, purchase.getAmount());
            preparedStmt.setString(4, purchase.getItemName());
            preparedStmt.setFloat(5, purchase.getQty());

            // execute the PreparedStatement
            preparedStmt.execute();
        }

    }

    public void insertSales(List<Sales> salesList) throws SQLException, ClassNotFoundException, ParseException {
        Connection con=  createConnection();
        for (int i = 0; i < salesList.size(); i++) {
            Sales sale = salesList.get(i);


            String query = "INSERT INTO sales VALUES (?,?,?,?,?,?)";

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date myDate = formatter.parse(sale.getDate());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

            // create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, sale.getDepartmentName());
            preparedStmt.setDate(2, sqlDate);
            preparedStmt.setInt(3,sale.getNo());
            preparedStmt.setFloat(4, sale.getAmount());
            preparedStmt.setString(5, sale.getItemName());
            preparedStmt.setFloat(6, sale.getQty());

            // execute the PreparedStatement
            preparedStmt.execute();
        }

    }
}
