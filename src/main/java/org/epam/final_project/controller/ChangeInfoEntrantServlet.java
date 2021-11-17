package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/pages/user/changeInfoEntrantServlet")
@MultipartConfig
public class ChangeInfoEntrantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("newName");
        if(name.equals("")){
            name=null;
        }
        String surname=request.getParameter("newSurname");
        if(surname.equals("")){
            surname=null;
        }
        String middleName=request.getParameter("newMiddleName");
        if(middleName.equals("")){
            middleName=null;
        }
        String email=request.getParameter("newEmail");
        if(email.equals("")){
            email=null;
        }
        String city=request.getParameter("newCity");
        if(city.equals("")){
            city=null;
        }
        String region=request.getParameter("newRegion");
        if(region.equals("")){
            region=null;
        }
        String placeEducation=request.getParameter("newPlaceEducation");
        if(placeEducation.equals("")){
            placeEducation=null;
        }
        Part filePart=request.getPart("newDiplom");
        InputStream diplom = filePart.getInputStream();
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        HttpSession session=request.getSession();
        String oldEmail=String.valueOf(session.getAttribute("email"));

        boolean b2=serviceEntrant.updateEntrantInfoByEmail(name,surname,middleName,email,city,region,placeEducation,diplom,oldEmail);
        if(b2==true) {
            session.setAttribute("email",email);
            response.sendRedirect("/pages/user/profileServlet");
        }
        else{
            String message="Пользователь не был изменен";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/user/changeInfoEntrant.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }
}
