package org.epam.final_project.controller;

import org.epam.final_project.model.Faculty;
import org.epam.final_project.model.Subject;
import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/applyServlet")
public class ApplyServlet extends HttpServlet {
    private static final Logger log=Logger.getLogger(ApplyServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String email=String.valueOf(session.getAttribute("email"));
        String role=String.valueOf(session.getAttribute("role"));
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        String status=serviceEntrant.checkStatusByEmail(email);

        if(!email.equals(null) && role.equals("entrant") && !status.equals("blocked")){

            ServiceFaculty serviceFaculty=new ServiceFacultyImpl();
            serviceEntrant=new ServiceEntrantImpl();

            long entrant_id=serviceEntrant.findIdByEmail(email);
            List<Subject> entrantSubjects=serviceEntrant.findSubjectById(entrant_id);

            long faculty_id=Long.parseLong(request.getParameter("id_faculty"));
            List<Subject> facultySubjects=serviceFaculty.findSubjectById(faculty_id);
            boolean b=checkSubjects(entrantSubjects,facultySubjects);
            if(b==true) {
                    Faculty faculty=serviceFaculty.findFacultyById(faculty_id);
                    if(faculty.getRecruitment().equals("finished")){
                        String message="Заявка не прошла.Набор на факультет закрыт";
                        session.setAttribute("message_home", message);
                        try {
                            getServletContext().getRequestDispatcher("/pages/user/homePageServlet").forward(request, response);
                        } catch (ServletException | IOException e) {
                            log.log(Level.WARNING,e.getMessage(),e);
                            response.sendRedirect("/pages/errorPage.jsp");
                        }
                    }
                    else {
                        boolean b1=serviceEntrant.addEntrantFaculty(entrant_id,faculty_id);
                        if(b1==true){
                        String message = "Подача заявки прошла успешно";
                        session.setAttribute("message_home", message);
                            try {
                                getServletContext().getRequestDispatcher("/pages/user/homePageServlet").forward(request, response);
                            } catch (ServletException | IOException e) {
                                log.log(Level.WARNING,e.getMessage(),e);
                                response.sendRedirect("/pages/errorPage.jsp");
                            }
                        }
                        else {
                            String message = "Подача заявки не прошла. Что-то пошло не так или Вы уже подали заявку";
                            session.setAttribute("message_home", message);
                            try {
                                getServletContext().getRequestDispatcher("/pages/user/homePageServlet").forward(request, response);
                            } catch (ServletException | IOException e) {
                                log.log(Level.WARNING,e.getMessage(),e);
                                response.sendRedirect("/pages/errorPage.jsp");
                            }
                        }
                    }
            }
            else
            {
                String message = "Подача заявки не прошла. Вы не сдали нужного предмета или у Вас недостаточно балов";
                session.setAttribute("message_home", message);
                try {
                    getServletContext().getRequestDispatcher("/pages/user/homePageServlet").forward(request, response);
                } catch (ServletException | IOException e) {
                    log.log(Level.WARNING,e.getMessage(),e);
                    response.sendRedirect("/pages/errorPage.jsp");
                }
            }
        }
        else {
            String message="Заявка не прошла.Сначала войдите в учетную запись или зарегистрируйтесь или пользователь заблокирован";
            session.setAttribute("message_index",message);
            try {
                getServletContext().getRequestDispatcher("/mainServlet").forward(request,response);
            } catch (ServletException | IOException e) {
                log.log(Level.WARNING,e.getMessage(),e);
                response.sendRedirect("/pages/errorPage.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private boolean checkSubjects(List<Subject> entrantSubjects, List<Subject> facultySubjects){
        boolean[] booleans=new boolean[facultySubjects.size()];
        int f=0;
        for (Subject facultySubject:facultySubjects){
            for (Subject entrantSubject:entrantSubjects){
                if(facultySubject.getNameSubject().equals(entrantSubject.getNameSubject())==true){
                    if(facultySubject.getGrade()<=entrantSubject.getGrade()){
                        booleans[f]=true;
                        f++;
                    }
                }
            }
        }

        for (int i = 0; i < booleans.length; i++) {
            if(booleans[i]!=true){
               return false;
            }
        }
        return true;
    }
}
