package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/pages/admin/changeFacultyServlet")
public class ChangeFacultyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        long id = Long.parseLong(request.getParameter("id"));
        int allPlaces = Integer.parseInt(request.getParameter("allPlaces"));
        int fundedPlaces = Integer.parseInt(request.getParameter("fundedPlaces"));

        if(fundedPlaces>allPlaces){
            String message="Не удалось изменить факультет.Количество бюджетных мест не должно превышать общее количество";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/admin/changeFaculty.jsp").forward(request, response);
        }
        String description=request.getParameter("description");

        HttpSession session=request.getSession();
        long oldFaculty_id= (long) session.getAttribute("oldFaculty_id");
        ServiceFaculty serviceFaculty=new ServiceFacultyImpl();
        boolean b=serviceFaculty.changeFaculty(id,name,allPlaces,fundedPlaces,description,oldFaculty_id);
        if(b!=false){
            response.sendRedirect("/pages/admin/adminkaServlet?sort=sortByName");
        }
        else{
            String message="Не удалось изменить факультет";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/admin/changeFaculty.jsp").forward(request, response);
        }
    }
}
