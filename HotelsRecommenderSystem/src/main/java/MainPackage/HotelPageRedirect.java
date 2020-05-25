package MainPackage;

import Dao.Hotel_Dao;
import Dao.UserActivity_Dao;
import Model.Hotel;
import Model.UserActivity;
import org.bson.types.ObjectId;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@WebServlet("/hotelPage")
public class HotelPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            //--------------------- saving user Activity (viewing) ----------------------------
            ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());
            Hotel hotel = new Hotel_Dao().getHotel(hotelId);
            String hotelName = hotel.getName(); //hotel name
            HttpSession session = request.getSession(false);

            if(session != null) {
                String userName =  session.getAttribute("username").toString(); //user name
                String activityOfuser = "view"; // user activity = viewing
                Date activityDate = new Date();
                new UserActivity_Dao().insertUserActivity(userName, hotelName, activityOfuser,activityDate );
            }
            //----------------------------------------------------------------------------------------

            pythonCodeExecutor(request);
            request.getRequestDispatcher("hotelPage.jsp").forward(request, response);
        } catch (ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pythonCodeExecutor(javax.servlet.http.HttpServletRequest request) throws ClassNotFoundException, IOException, InterruptedException {

        //
        System.out.println("Python started");

        ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());
        Hotel hotel = new Hotel_Dao().getHotel(hotelId);
        request.setAttribute("hotel", hotel);
        System.out.println("Hotel name : " + hotel.getName());
        String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main//webapp/hotel_data_cleaning_and_preprcessing.py";
        System.out.println(path);
        File file = new File(path);

        List<String> list = new ArrayList<String>();
        list.add("python3.6");
        String absPath = file.getAbsolutePath();
        list.add(absPath);
        list.add(hotel.getName());
        list.add(hotel.getCountry());
        ProcessBuilder pb = new ProcessBuilder(list);
        Process process = pb.start();

        process.waitFor();
        InputStream stdout = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        List<String> hotelNames = new ArrayList<String>();
        try{
            while((line = reader.readLine()) != null){
                hotelNames.add(line);
            }
            List<Hotel> hotelsRecommendedList = new Hotel_Dao().getHotelsByName(hotelNames);
            request.setAttribute("recommended_hotels", hotelsRecommendedList);
        }catch(IOException e){
            System.out.println("Exception in reading output python"+ e.toString());
        }
    }
}
