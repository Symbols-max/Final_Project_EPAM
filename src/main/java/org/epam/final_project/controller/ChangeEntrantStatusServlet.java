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

@WebServlet("/pages/admin/changeStatusServlet")
public class ChangeEntrantStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session=httpServletRequest.getSession();
        long id= (long) session.getAttribute("id_entrant");
        String status=httpServletRequest.getParameter("btnStatus");
        ServiceEntrant serviceEntrant=new ServiceEntrantImpl();
        if(status.equals("active")){
            boolean b=serviceEntrant.changeStatus("blocked",id);
            if(b==false) {
                String message="Поизошла ошибка.Статус не изменен.";
                httpServletRequest.setAttribute("message",message);
            }
                getServletContext().getRequestDispatcher("/pages/admin/infoAboutEntrantServlet?id=" + id).forward(httpServletRequest, httpServletResponse);
        }
        else{
            boolean b=serviceEntrant.changeStatus("active",id);
            if(b==false) {
                String message="Поизошла ошибка.Статус не изменен.";
                httpServletRequest.setAttribute("message",message);
            }
            getServletContext().getRequestDispatcher("/pages/admin/infoAboutEntrantServlet?id=" + id).forward(httpServletRequest, httpServletResponse);
        }
    }
}
