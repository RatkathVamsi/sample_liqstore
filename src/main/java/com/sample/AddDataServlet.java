package com.sample;

import com.opencsv.CSVReader;
import com.sample.model.Purchases;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.util.*;

@WebServlet(
        name = "AddDataServlet",
        urlPatterns = "/AddData"
)
@MultipartConfig
public class AddDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            System.out.println("Inside add data servlet");

            RequestDispatcher view = req.getRequestDispatcher("AddData.html");
            view.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        try {
        Part filePart = req.getPart("purchases"); // Retrieves <input type="file" name="file">
       // String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        System.out.println("Inside dopost method");

        Reader in = new InputStreamReader(fileContent);

        CSVReader reader;
        Iterator<String[]> iterator;



            reader = new CSVReader(new InputStreamReader(fileContent));
            iterator = reader.iterator();

            List<Purchases> purchasesList= new ArrayList<Purchases>();
            iterator.next();
            while(iterator.hasNext())
            {
                //System.out.println(Arrays.toString(iterator.next()));
                Purchases purchases= new Purchases();
                String[] row= iterator.next();
                purchases.setDepartmentName(row[0]);
               // purchases.setDate(Date.valueOf(row[1]));
                purchases.setQty(Float.parseFloat(row[4]));
                purchases.setItemName(row[3]);
                purchases.setAmount(Float.parseFloat(row[2]));

               purchasesList.add(purchases);
            }
            System.out.println("inserting into database");
            MySQLCon db = new MySQLCon();
            db.insertPurchases(purchasesList);
            in.close();

        }catch(Exception e) {System.out.println(e);}

    }

}
