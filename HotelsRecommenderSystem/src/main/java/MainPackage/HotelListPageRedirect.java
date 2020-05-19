package MainPackage;

import Dao.Hotel_Dao;
import Model.Hotel;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hotelsList")
public class HotelListPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String filter = request.getParameter("filter");
        if(filter.equals("all")){
            try {
                List<Hotel> hotels = new Hotel_Dao().getHotels();
                request.setAttribute("hotels", hotels);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(filter.equals("name")){
            try {
                List<String> hotelNames = new ArrayList<String>();
                hotelNames.add(request.getParameter("name"));
                List<Hotel> hotels = new Hotel_Dao().getHotelsByName(hotelNames);
                if(hotels == null) hotels = new ArrayList<Hotel>();
                request.setAttribute("hotels", hotels);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(filter.equals("country")){
            try {
                String country = request.getParameter("name");
                List<Hotel> hotels = new Hotel_Dao().getHotelsByCountry(country);
                if(hotels == null) hotels = new ArrayList<Hotel>();
                request.setAttribute("hotels", hotels);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(filter.equals("price")){
            try {
                Double min = Double.valueOf(request.getParameter("min").toString());
                Double max = Double.valueOf(request.getParameter("max").toString());
                List<Hotel> hotels = new Hotel_Dao().getHotelsByPrice(min,max);
                if(hotels == null) hotels = new ArrayList<Hotel>();
                request.setAttribute("hotels", hotels);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(filter.equals("class")){
            try {
                Double min = Double.valueOf(request.getParameter("min").toString());
                Double max = Double.valueOf(request.getParameter("max").toString());
                List<Hotel> hotels = new Hotel_Dao().getHotelsByClass(min,max);
                if(hotels == null) hotels = new ArrayList<Hotel>();
                request.setAttribute("hotels", hotels);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        request.getRequestDispatcher("hotelsList.jsp").forward(request, response);
    }
}
