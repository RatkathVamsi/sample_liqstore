package com.sample;

import com.sample.model.Purchases;
import com.sample.model.Sales;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


@WebServlet(
        name = "TestServlet",
        urlPatterns = "/Test"
)
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MySQLCon db;
        try {
            db = new MySQLCon();

            List<Purchases> purchases = db.getPurchases();
            List<Sales> sales = db.getSales();
            Date startDate = db.getStartDate();
            Date endDate = db.getEndDate();

            req.setAttribute("purchases", purchases);
            req.setAttribute("sales",sales);
            req.setAttribute("startDate",String.valueOf(startDate));
            req.setAttribute("endDate", String.valueOf(endDate));

            RequestDispatcher view = req.getRequestDispatcher("NewProductionEntry.jsp");
            view.forward(req, resp);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

