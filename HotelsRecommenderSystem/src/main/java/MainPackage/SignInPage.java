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
@MultipartConfig
@WebServlet("/signInPage")
public class SignInPage extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> UserInfo ;
        if(username != null && password != null) {
            try {
                UserInfo = new User_DAO().getUser(username, password);
                System.out.println(UserInfo);
                if (UserInfo != null) {

                    User user = UserInfo.get(0);
                    System.out.println(UserInfo);
                    HttpSession session = request.getSession();
                    session.setAttribute("userCountry", user.getCountry());
                    session.setAttribute("userCity",user.getCity());
                    session.setAttribute("fullName",user.getFullName());
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("userId", user.get_id());
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            }
        }
            else {
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}