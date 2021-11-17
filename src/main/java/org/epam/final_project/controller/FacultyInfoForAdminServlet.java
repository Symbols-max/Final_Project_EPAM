package org.epam.final_project.controller;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Subject;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pages/admin/facultyInfoForAdminServlet")
public class FacultyInfoForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id=Long.parseLong(request.getParameter("id"));
        ServiceFaculty serviceFaculty=new ServiceFacultyImpl();
        List<Subject> subjectList=serviceFaculty.findSubjectById(id);
        request.setAttribute("subjects",subjectList);
        Faculty faculty=serviceFaculty.findFacultyById(id);
        request.setAttribute("faculty",faculty);
        int numberEntrant=serviceFaculty.numberOfStudentsOnFaculty(id);
        request.setAttribute("numberEntrant",numberEntrant);
        HttpSession session=request.getSession();
        session.setAttribute("oldFaculty_id",id);
        getServletContext().getRequestDispatcher("/pages/admin/facultyInfoForAdmin.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}