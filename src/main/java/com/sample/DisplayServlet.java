package com.sample;

import com.sample.model.Display;

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
        name = "DisplayServlet",
        urlPatterns = "/Display"
)

public class DisplayServlet extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException
    {
       MySQLCon db;
        try {
            db = new MySQLCon();
            List<Display> display= db.getDisplay();

            System.out.println("Inside Display Servlet");
            for(int i=0;i<display.size();i++)
            {
                System.out.println(display.get(i));
            }

            req.setAttribute("display",display);
            RequestDispatcher view = req.getRequestDispatcher("display.jsp");
            view.forward(req, resp);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
