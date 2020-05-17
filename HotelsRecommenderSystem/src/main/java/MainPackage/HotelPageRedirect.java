package MainPackage;

import Dao.Hotel_Dao;
import Model.Hotel;
import org.bson.types.ObjectId;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@WebServlet("/hotelPage")
public class HotelPageRedirect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            pythonCodeExecutor(request);
            request.getRequestDispatcher("hotelPage.jsp").forward(request, response);
        } catch (ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void pythonCodeExecutor(javax.servlet.http.HttpServletRequest request) throws ClassNotFoundException, IOException, InterruptedException {

        System.out.println("Python started");
        ObjectId hotelId = new ObjectId(request.getParameter("hotel_id").toString());
        Hotel hotel = new Hotel_Dao().getHotel(hotelId);
        request.setAttribute("Hotel", hotel);
        System.out.println("Hotel name : " + hotel.getName());
        String path = "/home/aym/Documents/gitHub/hotel-recommander-system/HotelsRecommenderSystem/src/main/webapp/hotel_data_cleaning_and_preprcessing.py";
        File file = new File(path);

        List<String> list = new ArrayList<String>();
        list.add("python");
        String absPath = file.getAbsolutePath();
        list.add(absPath);
        list.add(hotel.getName());
        System.out.print(list.toString());
        ProcessBuilder pb = new ProcessBuilder(list);
        Process process = pb.start();

        process.waitFor();
        InputStream stdout = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        String output = "";
        try{
            while((line = reader.readLine()) != null){
                output += line;
                System.out.println("stdout: "+ line);
            }
        }catch(IOException e){
            System.out.println("Exception in reading output python"+ e.toString());
        }
        System.out.println("Python finished and output : " + output);
    }
}
