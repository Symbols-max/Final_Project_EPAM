package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/registrationServlet")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        if(name.equals("")){
            name=null;
        }
        String surname=request.getParameter("surname");
        if(surname.equals("")){
            surname=null;
        }
        String middleName=request.getParameter("middleName");
        if(middleName.equals("")){
            middleName=null;
        }
        String email=request.getParameter("email");
        if(email.equals("")){
            email=null;
        }
        String city=request.getParameter("city");
        if(city.equals("")){
            city=null;
        }
        String region=request.getParameter("region");
        if(region.equals("")){
            region=null;
        }
        String placeEducation=request.getParameter("placeEducation");
        if(placeEducation.equals("")){
            placeEducation=null;
        }
        Part filePart=request.getPart("diplom");
        InputStream diplom = filePart.getInputStream();
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        boolean b=serviceEntrant.checkEntrantByEmail(email);
        if(b==false){
            boolean b2=serviceEntrant.addEntrant(name,surname,middleName,email,city,region,placeEducation,diplom);
            if(b2==true) {
                HttpSession session=request.getSession();
                session.setAttribute("role","entrant");
                session.setAttribute("email",email);
                response.sendRedirect("/pages/user/homePageServlet");
            }
            else{
                String message="Пользователь не был зарегистрирован";
                request.setAttribute("message",message);
                getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            }
        }
        else{
            String message="Такой пользователь уже есть";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }
}
