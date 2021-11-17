package org.epam.final_project.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;


@WebServlet("/")
public class PreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        if(session.getAttribute("bundle")==null) {
        ResourceBundle bundle = ResourceBundle.getBundle("language");
        session.setAttribute("bundle", bundle);
        }
        String role=String.valueOf(session.getAttribute("role"));

        if(role.equals("entrant")) {
                getServletContext().getRequestDispatcher("/pages/user/homePageServlet").forward(request,response);
        }
        else if (role.equals("admin")){
                getServletContext().getRequestDispatcher("/pages/admin/adminkaServlet").forward(request,response);
        }
        else {
            getServletContext().getRequestDispatcher("/mainServlet").forward(request, response);
        }

    }

}