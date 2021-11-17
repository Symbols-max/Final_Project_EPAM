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

@WebServlet("/signInServlet")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("adminEmail");
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        boolean b=serviceEntrant.checkEntrantByEmail(email);
        if(b==true){

            String status=serviceEntrant.checkStatusByEmail(email);
            if(status.equals("active")) {
                HttpSession session = request.getSession();
                session.setAttribute("role", "entrant");
                session.setAttribute("email", email);
                response.sendRedirect("/pages/user/homePageServlet");
            }
            else{
                String message="Вы не можете войти.Пользователь заблокирован";
                request.setAttribute("message",message);
                getServletContext().getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
            }
        }
        else{
            String message="Такого пользователя нет";
            request.setAttribute("message",message);
            getServletContext().getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
