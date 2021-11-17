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

@WebServlet("/pages/admin/infoAboutEntrantServlet")
public class InfoAboutEntrantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id_entrant=Long.parseLong(request.getParameter("id"));
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        HttpSession session=request.getSession();
        session.setAttribute("id_entrant",id_entrant);

        Entrant entrant=serviceEntrant.findEntrantById(id_entrant);
        session.setAttribute("email",entrant.getEmail());
        request.setAttribute("entrant",entrant);

        List<Subject> subjectList=serviceEntrant.findSubjectById(entrant.getId());
        request.setAttribute("subjects",subjectList);

        List<Long> id_faculties=serviceEntrant.findFacultyById(id_entrant);
        request.setAttribute("id_faculties",id_faculties);

        getServletContext().getRequestDispatcher("/pages/admin/infoAboutEntrant.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }
}