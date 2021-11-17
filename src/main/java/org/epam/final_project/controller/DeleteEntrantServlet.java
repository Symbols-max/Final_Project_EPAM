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

@WebServlet("/pages/admin/deleteEntrantServlet")
public class DeleteEntrantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession();
        long id_entrant= (long) session.getAttribute("id_entrant");

        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        boolean b=serviceEntrant.deleteEntrantById(id_entrant);
        if(b==true){
            httpServletResponse.sendRedirect("/pages/admin/adminkaEntrantsServlet");
        }
        else {
            String message="Произошла ошибка.Абитуриент не был удален";
            httpServletRequest.setAttribute("msgDelete",message);
            httpServletResponse.sendRedirect("/pages/admin/adminkaEntrantsServlet");
        }
    }
}
