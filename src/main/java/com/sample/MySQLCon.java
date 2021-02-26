package com.sample;

import com.sample.model.Display;
import com.sample.model.ProductionEntry;
import com.sample.model.Purchases;
import com.sample.model.Sales;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MySQLCon {
    Connection con= null;
    MySQLCon() throws SQLException, ClassNotFoundException {
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(con==null){
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/default", "root", "root");
            System.out.println("SQL connection created");
        }
        return con;
    }

    public List <Display> getDisplay() throws SQLException, ClassNotFoundException {
        List <Display> displayList = new ArrayList<Display>();
        Connection con = createConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT id,startDate,endDate FROM `default`.transaction;");

        while(rs.next())
        {
           int id=rs.getInt(1);
           String startDate= rs.getString(2);
           String endDate= rs.getString(3);

           Display display = new Display();
           display.setTransactionId(id);
           display.setStartDate(startDate);
           display.setEndDate(endDate);

           displayList.add(display);

        }
        return displayList;
    }

    public List<Purchases> getPurchases() throws SQLException, ClassNotFoundException {
        List<Purchases> purchases = new ArrayList<Purchases>();
        String purchasesFilter[] = {"UD Cotton Seed",
                "UD Hulls",
                "Rice Brawn",
                "Rice Powder",
                "Shell powder",
                "PDL Powder",
                "UD Gunny Bags",
                "Colour"
        };
        Statement stmt = createConnection().createStatement();
        Date startDate = getStartDate();

        ResultSet rs = stmt.executeQuery("SELECT ItemName,sum(Qty),sum(Amount) FROM `default`.purchases  where Date >=" + startDate + " group by ItemName ;");

        while (rs.next()) {
            String itemName = rs.getString(1);
            //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            if(Arrays.stream(purchasesFilter).filter(x->x.equalsIgnoreCase(itemName)).count() >0)
            //if(Arrays.asList(purchasesFilter).contains(rs.getString(1)))
            {

                Purchases purchase = new Purchases();

                // purchase.setDepartmentName(rs.getString(1));
                //purchase.setDate(rs.getDate(4));
                purchase.setAmount(rs.getFloat(3));
                purchase.setItemName(rs.getString(1).toLowerCase().replace(" ",""));
                purchase.setQty(rs.getFloat(2));


                purchases.add(purchase);
            }
        }
        for (Purchases purchase : purchases) {
           // System.out.println(purchase.toString());
        }

        return purchases;

    }

    public Date getStartDate() throws SQLException, ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        PreparedStatement t_pstmt = createConnection().prepareStatement("SELECT endDate FROM `default`.transaction order by endDate;");
        ResultSet t_resultset = t_pstmt.executeQuery();


        //ResultSet temp = stmt.executeQuery("SELECT last_insert_id() FROM `default`.transaction;");
        Date prevEndDate = Date.valueOf("1950-06-13");


        Date startDate = Date.valueOf(prevEndDate.toLocalDate().plusDays(1));
        Date endDate = startDate;
       /* while( t_resultset.next()){

            prevEndDate = t_resultset.getDate(1);

        }
        System.out.println(prevEndDate);*/
        return startDate;
    }

    public List<Sales> getSales() throws SQLException, ClassNotFoundException {
        List<Sales> sales = new ArrayList<Sales>();

        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ItemName,sum(Qty),sum(Amount) FROM sales where ItemName='UD Cake' group by ItemName;");
        while (rs.next()) {
            //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            Sales sale = new Sales();
            // purchase.setDepartmentName(rs.getString(1));
            //purchase.setDate(String.valueOf(rs.getDate(2)));
            sale.setAmount(rs.getFloat(3));
            sale.setItemName(rs.getString(1).toLowerCase().replace(" ",""));
            sale.setQty(rs.getFloat(2));
            sales.add(sale);
        }

        for (Sales sale : sales) {
            System.out.println(sale.toString());
        }

        return sales;

    }

    public void insertPurchases(List<Purchases> purchasesList) throws SQLException, ClassNotFoundException, ParseException {
        Connection con=  createConnection();
        for (int i = 0; i < purchasesList.size(); i++) {
            Purchases purchase = purchasesList.get(i);


            String query = "INSERT INTO purchases VALUES (?,?,?,?,?)";

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date myDate = formatter.parse(String.valueOf(purchase.getDate()));
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

            // create the mysql insert PreparedStatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, purchase.getDepartmentName());
            preparedStmt.setDate(2, sqlDate);
            preparedStmt.setFloat(3, Float.parseFloat(purchase.getAmount()));
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
            preparedStmt.setFloat(4, Float.parseFloat(sale.getAmount()));
            preparedStmt.setString(5, sale.getItemName());
            preparedStmt.setFloat(6, sale.getQty());

            // execute the PreparedStatement
            preparedStmt.execute();
        }

    }


    public Date getEndDate() throws SQLException, ClassNotFoundException {
        Statement stmt = createConnection().createStatement();
        PreparedStatement t_pstmt = createConnection().prepareStatement("SELECT Date FROM `default`.purchases order by Date;");
        ResultSet t_resultset = t_pstmt.executeQuery();

        Date endDate = null;
       while( t_resultset.next()){

            endDate = t_resultset.getDate(1);

        }
        System.out.println(endDate);

        return endDate;
    }

    public void insertProductionEntry(ProductionEntry productionEntry) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        String query = "INSERT INTO transaction(gst,processingRate,udCakeQty,startDate,endDate) VALUES (?,?,?,?,?)";

        // create the mysql insert PreparedStatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setFloat(1, productionEntry.getGST());
        preparedStmt.setFloat(2,productionEntry.getProcessingRate());
        preparedStmt.setFloat(3,0);
        preparedStmt.setDate(4,productionEntry.getStartDate());
        preparedStmt.setDate(5,productionEntry.getEndDate());

        preparedStmt.execute();
    }



    public int insertRecords(ProductionEntry productionEntry) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        String query1= "select max(id) as id From `default`.transaction ;";
        Statement stmt = createConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query1);
        int id=0;
        while(rs.next())
        { id = rs.getInt(1);  }

        for(int i=0;i<productionEntry.getItemNames().length;i++)
        {
            String query = "INSERT INTO records VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,id);
            preparedStmt.setString(2,productionEntry.getItemNames()[i]);
            preparedStmt.setFloat(3, Float.parseFloat(productionEntry.getConsumptionQties()[i]));
            preparedStmt.setFloat(4, Float.parseFloat(productionEntry.getStock()[i]));

            preparedStmt.execute();
        }

        for(int i=0;i<productionEntry.getSalesItemNames().length;i++)
        {
            String query = "INSERT INTO records VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,id);
            preparedStmt.setString(2,productionEntry.getSalesItemNames()[i]);
            preparedStmt.setFloat(3, Float.parseFloat(productionEntry.getSalesConsumptionQties()[i]));
            preparedStmt.setFloat(4, Float.parseFloat(productionEntry.getSalesStock()[i]));

            preparedStmt.execute();
        }


        return id;
    }
}
