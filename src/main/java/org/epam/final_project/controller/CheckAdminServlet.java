package org.epam.final_project.controller;

import org.epam.final_project.service.ServiceAdmin;
import org.epam.final_project.service.ServiceAdminImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkAdminServlet")
public class CheckAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceAdmin serviceAdmin=new ServiceAdminImpl();
        String login = request.getParameter("adminLogin");
        String password = request.getParameter("adminPassword");
        boolean b=serviceAdmin.checkAdmin(login, password);
        if (b==true) {
            HttpSession session=request.getSession();
            session.setAttribute("role","admin");
            response.sendRedirect("/pages/admin/adminkaServlet");
        }
        else {
            String message="Неправильные данные";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/checkAdmin.jsp").forward(request, response);
        }
    }
}
