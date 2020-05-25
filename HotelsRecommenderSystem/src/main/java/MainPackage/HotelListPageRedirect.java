package MainPackage;

import Dao.Hotel_Dao;
import Model.Hotel;
import org.bson.types.ObjectId;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hotelsList")
public class HotelListPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String filter = request.getParameter("filter");
        HttpSession session = request.getSession(false);
        if (session != null ) {
            String username = session.getAttribute("username").toString();
            if (filter.equals("all")) {
                try {
                    //

                    //
                    //ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());


                    String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main/webapp/knnFCUser.py";
                    File file = new File(path);

                    List<String> list = new ArrayList<String>();
                    list.add("python3.6");
                    String absPath = file.getAbsolutePath();
                    list.add(absPath);
                    list.add("session");
                    list.add(username);
                    list.add("all");
                    ProcessBuilder pb = new ProcessBuilder(list);
                    Process process = pb.start();

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    InputStream stdout = process.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
                    String line;
                    List<String> hotelNames = new ArrayList<String>();

                    while ((line = reader.readLine()) != null) {
                        hotelNames.add(line);
                        System.out.print(line);

                    }
                    List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
                    request.setAttribute("recommended_hotels", hotelsRecommendedList);

                    request.setAttribute("hotels", hotelsRecommendedList);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            } else if (filter.equals("name")) {

                try {
                    List<String> hotelNames = new ArrayList<String>();
                    hotelNames.add(request.getParameter("name"));
                    List<Hotel> hotels = new Hotel_Dao().getHotelsByName(hotelNames);
                    if (hotels == null) hotels = new ArrayList<Hotel>();
                    request.setAttribute("hotels", hotels);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (filter.equals("country")) {
                try {
                    String country = request.getParameter("name");

                    //
                    //
                    //ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());


                    String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main/webapp/knnFCUser.py";
                    File file = new File(path);

                    List<String> list = new ArrayList<String>();
                    list.add("python3.6");
                    String absPath = file.getAbsolutePath();
                    list.add(absPath);
                    list.add("session");
                    list.add(username);
                    list.add(filter);
                    list.add(country);
                    ProcessBuilder pb = new ProcessBuilder(list);
                    Process process = pb.start();

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    InputStream stdout = process.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
                    String line;
                    List<String> hotelNames = new ArrayList<String>();

                    while ((line = reader.readLine()) != null) {
                        hotelNames.add(line);
                        System.out.print(line);

                    }
                    List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
                    request.setAttribute("recommended_hotels", hotelsRecommendedList);

                    request.setAttribute("hotels", hotelsRecommendedList);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

            } else if (filter.equals("price")) {
                try {
                    Double min = Double.valueOf(request.getParameter("min").toString());
                    Double max = Double.valueOf(request.getParameter("max").toString());

                    //
                    //
                    //ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());


                    String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main/webapp/knnFCUser.py";
                    File file = new File(path);

                    List<String> list = new ArrayList<String>();
                    list.add("python3.6");
                    String absPath = file.getAbsolutePath();
                    list.add(absPath);
                    list.add("session");
                    list.add(username);
                    list.add(filter);
                    list.add(String.valueOf(min));
                    list.add(String.valueOf(max));
                    ProcessBuilder pb = new ProcessBuilder(list);
                    Process process = pb.start();

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    InputStream stdout = process.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
                    String line;
                    List<String> hotelNames = new ArrayList<String>();

                    while ((line = reader.readLine()) != null) {
                        hotelNames.add(line);
                        System.out.print(line);

                    }
                    List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
                    request.setAttribute("recommended_hotels", hotelsRecommendedList);

                    request.setAttribute("hotels", hotelsRecommendedList);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

            } else if (filter.equals("class")) {
                try {
                    Double min = Double.valueOf(request.getParameter("min").toString());
                    Double max = Double.valueOf(request.getParameter("max").toString());

                    //
                    //
                    //ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());


                    String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main/webapp/knnFCUser.py";
                    File file = new File(path);

                    List<String> list = new ArrayList<String>();
                    list.add("python3.6");
                    String absPath = file.getAbsolutePath();
                    list.add(absPath);
                    list.add("session");
                    list.add(username);
                    list.add(filter);
                    list.add(String.valueOf(min));
                    list.add(String.valueOf(max));
                    ProcessBuilder pb = new ProcessBuilder(list);
                    Process process = pb.start();

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    InputStream stdout = process.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
                    String line;
                    List<String> hotelNames = new ArrayList<String>();

                    while ((line = reader.readLine()) != null) {
                        hotelNames.add(line);
                        System.out.print(line);

                    }
                    List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
                    request.setAttribute("recommended_hotels", hotelsRecommendedList);

                    request.setAttribute("hotels", hotelsRecommendedList);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }


            }
            request.getRequestDispatcher("hotelsList.jsp").forward(request, response);
        }
        else {
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
}
