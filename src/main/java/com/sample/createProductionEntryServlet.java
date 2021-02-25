package com.sample;

import com.sample.model.LiquorType;
import com.sample.model.ProductionEntry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;



@WebServlet(
        name = "createProductionEntryservlet",
        urlPatterns = "/createProductionEntry"
)
public class createProductionEntryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductionEntry productionEntry= new ProductionEntry();

        productionEntry.setGST(Float.valueOf(req.getParameter("GST")));
        productionEntry.setProcessingRate(Float.valueOf(req.getParameter("processingRate")));
       // productionEntry.setUdCakeQty(Float.valueOf(req.getParameter("udCake")));
        productionEntry.setStartDate(Date.valueOf(req.getParameter("startDate")));
        productionEntry.setEndDate(Date.valueOf(req.getParameter("endDate")));

        productionEntry.setConsumptionQties(req.getParameterValues("consumptionQty"));
        productionEntry.setItemNames(req.getParameterValues("itemName"));
        productionEntry.setStock(req.getParameterValues("stock"));

        productionEntry.setSalesConsumptionQties(req.getParameterValues("salesConsumptionQty"));
        productionEntry.setSalesItemNames(req.getParameterValues("salesItemNames"));
        productionEntry.setSalesStock(req.getParameterValues("salesStock"));
        System.out.println(productionEntry.toString());

        // Inserting Production entry into Database
        int id=0;
        try {
            MySQLCon db = new MySQLCon();
            db.insertProductionEntry(productionEntry);
            id= db.insertRecords(productionEntry);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("id",id);
        RequestDispatcher view = req.getRequestDispatcher("Success.jsp");
        view.forward(req, resp);
    }
}







/*public class SelectLiquorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String liquorType = req.getParameter("Type");
        System.out.println("Liquor Type= "+ liquorType);
        LiquorService liquorService = new LiquorService();
        LiquorType l = LiquorType.valueOf(liquorType);

        List liquorBrands = liquorService.getAvailableBrands(l);

        req.setAttribute("brands", liquorBrands);
        RequestDispatcher view = req.getRequestDispatcher("Result.jsp");
        view.forward(req, resp);

    }
}
*/

