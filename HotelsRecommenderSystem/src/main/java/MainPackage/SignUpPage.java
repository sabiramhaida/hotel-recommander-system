package MainPackage;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.User_DAO;
import Dao.Hotel_Dao;
import Model.Hotel;
import Model.User;
import org.bson.Document;
import java.io.IOException;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import Dao.User_DAO;

@MultipartConfig
    @WebServlet("/signUpPage")
public class SignUpPage extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        System.out.println(username + password + city +country + fullname);
        if(username != null && password != null && fullname!=null && country!=null && city!=null) {
            try {
                int check = new User_DAO().isRegistred(username);
                if(check==0) {
                    new User_DAO().insertUser(username, country, city, fullname, password);
                    request.getRequestDispatcher("/signin.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}