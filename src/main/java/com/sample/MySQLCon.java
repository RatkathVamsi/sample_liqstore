package com.sample;

import com.sample.model.Purchases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class MySQLCon{
    public static void main(String args[]){
        //Purchases purchases = new Purchases();
        List<Purchases> purchases = new ArrayList<Purchases>();


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/default","root","root");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from purchases");
            while(rs.next()) {
                //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                Purchases purchase = new Purchases();
                purchase.setDepartmentName(rs.getString(1));
                purchase.setDate(rs.getDate(2));
                purchase.setAmount(rs.getInt(3));
                purchase.setItemName(rs.getString(4));
                purchase.setQty(rs.getInt(5));
                purchases.add(purchase);
            }

            for (Purchases purchase : purchases) {
                System.out.println(purchase.toString());
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}