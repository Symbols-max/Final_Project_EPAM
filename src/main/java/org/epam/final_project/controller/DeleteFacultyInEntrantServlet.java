package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pages/user/deleteFacultyInEntrantServlet")
public class DeleteFacultyInEntrantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
       long id_faculty=Long.parseLong(httpServletRequest.getParameter("btnDelete"));
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        HttpSession session=httpServletRequest.getSession();
        String email=String.valueOf(session.getAttribute("email"));
        long id_entrant=serviceEntrant.findIdByEmail(email);
        boolean b=serviceEntrant.deleteFacultyInEntrant(id_faculty,id_entrant);
        if(b==false){
            String message="Удаление не прошло";
            httpServletRequest.setAttribute("message",message);
        }
        httpServletResponse.sendRedirect("/pages/user/profileServlet");
    }
}
