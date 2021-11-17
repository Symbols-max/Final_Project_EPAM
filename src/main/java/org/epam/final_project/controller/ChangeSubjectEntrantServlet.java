package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceEntrant;
import org.epam.final_project.service.ServiceEntrantImpl;
import org.epam.final_project.util.Subjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/pages/user/changeSubjectEntrantServlet")
public class ChangeSubjectEntrantServlet extends HttpServlet {
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
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        String email=String.valueOf(session.getAttribute("email"));
        long id=serviceEntrant.findIdByEmail(email);
        boolean check=serviceEntrant.checkSubjectById(id);
        if(check==false){
            boolean b1 = serviceEntrant.addSubjectsById(map, id);

            if (b1 == true) {
                response.sendRedirect("/pages/user/profileServlet");
            } else {
                System.out.println("sd");
                String message = "Предметы не были изменены";
                session.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/pages/user/preChangeSubjectEntrantServlet").forward(request, response);
            }
        }
        else {
            boolean b1 = serviceEntrant.addSubjectsByIdWithDeleting(map, id);

            if (b1 == true) {
                response.sendRedirect("/pages/user/profileServlet");
            } else {
                System.out.println("sdsd");
                String message = "Предметы не были изменены";
                session.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/pages/user/preChangeSubjectEntrantServlet").forward(request, response);
            }
        }
    }
}
