package MainPackage;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/hotelPage")
public class HotelPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("hotelPage.jsp").forward(request, response);
    }
}
