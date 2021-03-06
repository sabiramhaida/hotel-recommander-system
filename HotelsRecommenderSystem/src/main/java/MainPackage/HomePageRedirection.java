package MainPackage;

import Dao.Hotel_Dao;
import Model.Hotel;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/homepage")
public class HomePageRedirection extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        List<Hotel> hotels = null;
        try {
            hotels = new Hotel_Dao().getHotels();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("hotel size is : " + hotels.size());
        request.setAttribute("hotels",hotels);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
