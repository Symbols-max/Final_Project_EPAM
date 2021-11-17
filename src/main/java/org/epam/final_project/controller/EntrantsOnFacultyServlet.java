package org.epam.final_project.controller;

import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Faculty;
import org.epam.final_project.service.Service;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pages/admin/entrantsOnFacultyServlet")
public class EntrantsOnFacultyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id_faculty=Long.parseLong(request.getParameter("btnList"));
        ServiceFaculty service=new ServiceFacultyImpl();

        List<Entrant> entrantList=service.entrantsOnFaculty(id_faculty);
        request.setAttribute("entrants",entrantList);

        Faculty faculty=service.findFacultyById(id_faculty);
        request.setAttribute("faculty",faculty);


        getServletContext().getRequestDispatcher("/pages/admin/entrantsOnFaculty.jsp").forward(request,response);
    }
}
