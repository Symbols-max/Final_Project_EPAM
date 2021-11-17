package org.epam.final_project.controller;

import org.epam.final_project.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pages/admin/deleteFacultyServlet")
public class DeleteFacultyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        long id_faculty=Long.parseLong(httpServletRequest.getParameter("btnDelete"));
        ServiceFaculty serviceEntrant=new ServiceFacultyImpl();
        HttpSession session=httpServletRequest.getSession();
        boolean b=serviceEntrant.deleteFaculty(id_faculty);
        if(b==false){
            String message="Удаление не прошло";
            httpServletRequest.setAttribute("message",message);
        }
        httpServletResponse.sendRedirect("/pages/admin/adminkaServlet?sort=sortByName");
    }
}
