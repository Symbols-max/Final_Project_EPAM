package org.epam.final_project.controller;


import org.epam.final_project.model.Entrant;
import org.epam.final_project.model.Subject;
import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pages/user/profileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        String email=String.valueOf(session.getAttribute("email"));
        Entrant entrant=serviceEntrant.findEntrantByEmail(email);
        List<Subject> subjectList=serviceEntrant.findSubjectById(entrant.getId());
        request.setAttribute("entrant",entrant);
        request.setAttribute("subjects",subjectList);

        long id_entrant= serviceEntrant.findIdByEmail(email);
        List<Long> id_faculties=serviceEntrant.findFacultyById(id_entrant);
        request.setAttribute("id_faculties",id_faculties);
        getServletContext().getRequestDispatcher("/pages/user/profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      super.doPost(request,response);
    }
}