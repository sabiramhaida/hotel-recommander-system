package MainPackage;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/hotelsList")
public class HotelListPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("hotelsList.jsp").forward(request, response);
    }
}
