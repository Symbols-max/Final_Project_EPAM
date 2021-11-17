package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import org.epam.final_project.service.ServiceFaculty;
import org.epam.final_project.service.ServiceFacultyImpl;
import org.epam.final_project.util.Subjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/pages/admin/changeSubjectForFacultyServlet")
public class ChangeSubjectForFacultyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Integer> map=new LinkedHashMap<>();
        Subjects[] subjects=Subjects.values();
        for (Subjects s : subjects) {
            if (!(request.getParameter(s.toString()).equals(""))) {
                map.put(s.value(), Integer.parseInt(request.getParameter(s.toString())));
            }
        }

        HttpSession session=request.getSession();
        ServiceFaculty serviceFaculty=new ServiceFacultyImpl();

        long id_faculty= (long) session.getAttribute("oldFaculty_id");

        boolean check=serviceFaculty.checkSubjectById(id_faculty);
        if(check==false){
            boolean b1 = serviceFaculty.addSubjectById(map,id_faculty);

            if (b1 == true) {
                response.sendRedirect("/pages/admin/adminkaServlet?sort=sortByName");
            } else {
                String message = "Предметы не были изменены";
                session.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/pages/admin/changeSubjectForFaculty.jsp").forward(request, response);
            }
        }
        else {
            boolean b1 = serviceFaculty.addSubjectsByIdWithDeleting(map,id_faculty);

            if (b1 == true) {
                response.sendRedirect("/pages/admin/adminkaServlet?sort=sortByName");
            } else {
                String message = "Предметы не были изменены";
                session.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/pages/admin/changeSubjectForFaculty.jsp").forward(request, response);
            }
        }
    }
}
