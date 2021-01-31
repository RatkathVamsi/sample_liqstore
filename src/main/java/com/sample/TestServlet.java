package com.sample;

import com.sample.model.Purchases;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(
        name = "TestServlet",
        urlPatterns = "/Test"
)
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MySQLCon db = new MySQLCon();
        try {

            List<Purchases> purchases = db.getPurchases();
            for (Purchases purchase : purchases) {
                System.out.println(purchase.toString());
            }

            req.setAttribute("purchases", purchases);
            RequestDispatcher view = req.getRequestDispatcher("NewProductionEntry.html");
            view.forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

